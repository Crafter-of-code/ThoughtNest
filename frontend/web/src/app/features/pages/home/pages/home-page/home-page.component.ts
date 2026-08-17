import { Component, OnInit } from '@angular/core';

import { AddBlogButtonComponent } from '../../../components/add-blog-button/add-blog-button.component';
import { UploadBlogComponent } from '../../../components/upload-blog/upload-blog.component';
import { BlogService } from '../../../../../core/services/blog/blog.service';
import { BlogContainerComponent } from '../../../components/blog-container/blog-container.component';
import {
  shortBlogResponseType,
  singleBlogResponseType,
  universalResponseDataType,
} from '../../../../../types/BlogTypes';
import { NotificationService } from '../../../../../core/services/notification/notification.service';
import { ShortBlogContainerComponent } from '../../components/short-blog-container/short-blog-container.component';
import { finalize } from 'rxjs';
@Component({
  selector: 'app-home-page',
  imports: [ShortBlogContainerComponent, BlogContainerComponent],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css',
})
export class HomePageComponent implements OnInit {
  constructor(
    private blogSerivce: BlogService,
    private notification: NotificationService
  ) {}

  showBlogContainer: boolean = false;
  blogData: shortBlogResponseType = [];
  skeletonLoading: boolean = true;
  singleBlogData: universalResponseDataType<singleBlogResponseType> = {
    status: false,
    message: '',
    data: null,
  };
  ngOnInit(): void {
    const cardHeight = 130;
    const numberOfCards = Math.ceil(window.innerHeight / cardHeight);
    // this.completePageData.forEach((item) => {
    //   item.numberOfSkeletonRender = Array.from(
    //     { length: numberOfCards },
    //     (_, i) => i
    //   );
    // });
    this.getLatestBlog();
  }

  getLatestBlog() {
    this.blogSerivce
      .getLatestBlogs()
      .pipe(
        finalize(() => {
          this.skeletonLoading = false;
        })
      )
      .subscribe({
        next: (response: universalResponseDataType<shortBlogResponseType>) => {
          if (response.data) {
            this.blogData = response.data;
          }
        },
        error: (err) => {
          this.notification.setNotification(
            err.error.status,
            err.error.message
          );
        },
      });
  }
  getBlogById(data: string) {
    this.blogSerivce.getSingleBlog(data).subscribe({
      next: (Response: universalResponseDataType<singleBlogResponseType>) => {
        this.singleBlogData.data = Response.data;
        this.showBlogContainer = true;
        document.body.style.overflow = 'hidden';
      },
      error: (err) => {
        this.notification.setNotification(err.error.status, err.error.message);
      },
    });
  }
  closeBlogContainer1(event: Event) {
    this.showBlogContainer = !this.showBlogContainer;
    document.body.style.overflow = 'auto';
  }
}
