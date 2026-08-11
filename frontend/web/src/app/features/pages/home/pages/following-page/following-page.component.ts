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
      .getFollowingBlog()
      .pipe(
        finalize(() => {
          this.loadingSpinner = false;
          this.skeletonLoading = false;
          console.log(this.followingUserDataWithBlog);
        })
      )
      .subscribe({
        next: (response) => {
          this.notificaiotn.setNotification(response.status, response.message);
          console.log(response.data);
          if (response.data) {
            console.log(response.data[0].userPublicImageUrl);
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
