import { Component, OnInit } from '@angular/core';
import { LandingPageComponent } from '../../welcome/components/landing-page/landing-page.component';
import { MainHeaderComponent } from '../../../shared/header/main-header/main-header.component';
import { HomeSkeletonUiComponent } from '../components/home-skeleton-ui/home-skeleton-ui.component';
import { CommonModule } from '@angular/common';
import { AddBlogButtonComponent } from '../components/add-blog-button/add-blog-button.component';
import { UploadBlogComponent } from '../components/upload-blog/upload-blog.component';
import { BlogService } from '../../../core/services/blog/blog.service';
import { ShortBlogContainerComponent } from '../components/short-blog-container/short-blog-container.component';
import { BlogContainerComponent } from '../components/blog-container/blog-container.component';
type latestBlogDataType = {
  blogTitle: string;
  blogContent: string;
  userName: string;
  userId: number;
  userEmail: string;
  createdAt: Date;
  blogId: string;
};
type singleBlogDataType = {
  blogId: string;
  blogTitle: string;
  blogSummary: string;
  blogContent: string;
  coverImage: string;

  // Author
  userId: string;
  userName: string;

  // Statistics
  blogViews: number;
  blogLikes: number;
  blogComments: number;

  // Publish Date
  createdAt: Date;
};
type completePageDataType = {
  containerName: string;
  numberOfSkeletonRender: number[];
  skeletonLoading: boolean | undefined;
  blogData?: latestBlogDataType[] | undefined;
};
@Component({
  selector: 'app-home-page',
  imports: [
    MainHeaderComponent,
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
  singleBlogData: singleBlogDataType | undefined;
  completePageData: completePageDataType[] = [
    {
      containerName: 'Latest Blog',
      numberOfSkeletonRender: [],
      skeletonLoading: true,
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
    // this.skeletonRendering = Array.from({ length: numberOfCards }, (_, i) => i);
    this.getLatestBlog();
  }
  UploadBlogContainerShower(event: Event) {
    this.showUploadBlogContainer = !this.showUploadBlogContainer;
  }
  getLatestBlog() {
    this.blogSerivce.getLatestBlog().subscribe({
      next: (response) => {
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
      next: (Response) => {
        console.log(Response.data);
        this.singleBlogData = Response.data;
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
