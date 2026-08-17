import { Component, OnInit } from '@angular/core';
import { BlogService } from '../../../../../core/services/blog/blog.service';
import { finalize } from 'rxjs';
import { NotificationService } from '../../../../../core/services/notification/notification.service';
import { shortBlogResponseDataType } from '../../../my-profile-page/type';
import { shortBlogResponseType } from '../../../../../types/BlogTypes';
// import { ShortBlogContainerComponent } from '../../components/short-blog-container/short-blog-container.component';\
import { ShortBlogContainerComponent } from '../../components/short-blog-container/short-blog-container.component';
@Component({
  selector: 'app-trending-page',
  imports: [ShortBlogContainerComponent],
  templateUrl: './trending-page.component.html',
  styleUrl: './trending-page.component.css',
})
export class TrendingPageComponent implements OnInit {
  constructor(
    private blogService: BlogService,
    private notification: NotificationService
  ) {}
  blogData: shortBlogResponseType = [];
  skeletonLoading: boolean = true;
  ngOnInit() {
    this.getTrendingBlog();
  }
  getTrendingBlog() {
    this.blogService
      .getTrendingBlogs()
      .pipe(
        finalize(() => {
          this.skeletonLoading = false;
        })
      )
      .subscribe({
        next: (response) => {
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
}
