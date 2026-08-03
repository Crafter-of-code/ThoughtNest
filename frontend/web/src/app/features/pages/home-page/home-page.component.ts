import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddBlogButtonComponent } from '../components/add-blog-button/add-blog-button.component';
import { UploadBlogComponent } from '../components/upload-blog/upload-blog.component';
import { BlogService } from '../../../core/services/blog/blog.service';
import { ShortBlogContainerComponent } from '../components/short-blog-container/short-blog-container.component';
import { BlogContainerComponent } from '../components/blog-container/blog-container.component';
import {
  shortBlogResponseType,
  singleBlogResponseType,
  universalResponseDataType,
} from '../../../types/BlogTypes';
type latestBlogDataType = {
  blogTitle: string;
  blogContent: string;
  userName: string;
  publicId: string;
  userEmail: string;
  userImageUrl: string;
  createdAt: Date;
  blogId: string;
};
type completePageDataType = {
  containerName: string;
  numberOfSkeletonRender: number[];
  skeletonLoading: boolean | undefined;
  blogData?: shortBlogResponseType | null;
};
@Component({
  selector: 'app-home-page',
  imports: [
    CommonModule,
    AddBlogButtonComponent,
    UploadBlogComponent,
    ShortBlogContainerComponent,
    BlogContainerComponent,
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css',
})
export class HomePageComponent implements OnInit {
  constructor(private blogSerivce: BlogService) {}
  showUploadBlogContainer: boolean = false;
  showBlogContainer: boolean = false;
  singleBlogData: universalResponseDataType<singleBlogResponseType> = {
    status: false,
    message: '',
    data: null,
  };
  completePageData: completePageDataType[] = [
    {
      containerName: 'Latest Blog',
      numberOfSkeletonRender: [],
      skeletonLoading: true,
      blogData: null,
    },
  ];
  ngOnInit(): void {
    const cardHeight = 130;
    const numberOfCards = Math.ceil(window.innerHeight / cardHeight);
    this.completePageData.forEach((item) => {
      item.numberOfSkeletonRender = Array.from(
        { length: numberOfCards },
        (_, i) => i
      );
    });
    this.getLatestBlog();
  }
  UploadBlogContainerShower(event: Event) {
    this.showUploadBlogContainer = !this.showUploadBlogContainer;
  }
  getLatestBlog() {
    this.blogSerivce.getLatestBlog().subscribe({
      next: (response: universalResponseDataType<shortBlogResponseType>) => {
        const blogData = response.data;
        const sectionData = this.completePageData.find(
          (item) => item.containerName == 'Latest Blog'
        );
        if (sectionData) {
          sectionData.skeletonLoading = false;
          sectionData.blogData = response.data;
        }
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  getBlogById(data: string) {
    this.blogSerivce.getSingleBlog(data).subscribe({
      next: (Response: universalResponseDataType<singleBlogResponseType>) => {
        console.log(Response.data);
        this.singleBlogData.data = Response.data;
        this.showBlogContainer = true;
        document.body.style.overflow = 'hidden';
        console.log(document.body.style.overflow);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  closeBlogContainer1(event: Event) {
    this.showBlogContainer = !this.showBlogContainer;
    document.body.style.overflow = 'auto';
  }
}
