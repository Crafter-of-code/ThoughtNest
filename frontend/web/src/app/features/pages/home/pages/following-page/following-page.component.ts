import { Component, OnInit } from '@angular/core';
import { BlogService } from '../../../../../core/services/blog/blog.service';
import { finalize } from 'rxjs';
import { CommonModule } from '@angular/common';
import {
  shortBlogDataType,
  shortBlogResponseDataType,
} from '../../../my-profile-page/type';
import {
  getFollowingTopThreeBlogResponseData,
  shortBlogResponseType,
} from '../../../../../types/BlogTypes';
import { ShortBlogContainerComponent } from '../../components/short-blog-container/short-blog-container.component';
import { NotificationService } from '../../../../../core/services/notification/notification.service';
import { GlassBackgroundComponent } from '../../../../../shared/components/glass-background/glass-background.component';
@Component({
  selector: 'app-following-page',
  imports: [
    CommonModule,
    ShortBlogContainerComponent,
    GlassBackgroundComponent,
  ],
  templateUrl: './following-page.component.html',
  styleUrl: './following-page.component.css',
})
export class FollowingPageComponent implements OnInit {
  constructor(
    private blogService: BlogService,
    private notificaiotn: NotificationService
  ) {}
  followingUserDataWithBlog: getFollowingTopThreeBlogResponseData = [];
  skeletonLoading: boolean = true;
  loadingSpinner: boolean = true;
  ngOnInit(): void {
    this.blogService
      .getFollowingBlogs()
      .pipe(
        finalize(() => {
          this.loadingSpinner = false;
          this.skeletonLoading = false;
        })
      )
      .subscribe({
        next: (response) => {
          this.notificaiotn.setNotification(response.status, response.message);
          if (response.data) {
            this.followingUserDataWithBlog = response.data;
          }
        },
        error: (err) => {
          this.notificaiotn.setNotification(
            err.error.status,
            err.error.message
          );
        },
      });
  }
}
