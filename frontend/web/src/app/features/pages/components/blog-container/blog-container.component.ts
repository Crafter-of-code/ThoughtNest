import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
} from '@angular/core';
import { MainContainerComponent } from '../main-container/main-container.component';
import { CommonModule } from '@angular/common';
import { singleBlogResponseType } from '../../../../types/BlogTypes';
import { BlogService } from '../../../../core/services/blog/blog.service';
import { NotificationService } from '../../../../core/services/notification/notification.service';
import { SolidButtonComponent } from '../../../../shared/components/solid-button/solid-button.component';
@Component({
  selector: 'app-blog-container',
  imports: [MainContainerComponent, CommonModule],
  templateUrl: './blog-container.component.html',
  styleUrl: './blog-container.component.css',
})
export class BlogContainerComponent implements OnInit {
  constructor(
    private blogService: BlogService,
    private notification: NotificationService
  ) {}
  @Output() closeBlogContainer = new EventEmitter<Event>();
  @Input() singleBlogData: singleBlogResponseType | null = null;
  isLiked: boolean = false;
  clicked(event: Event) {
    this.closeBlogContainer.emit();
  }
  ngOnInit(): void {
    this.isLiked = this.singleBlogData?.blogLiked ?? false;
  }
  sendLikesToBlog(blogId: string) {
    this.blogService.likeBlog(blogId).subscribe({
      next: (response: any) => {
        this.notification.setNotification(response.status, response.message);
        this.isLiked = !this.isLiked;
      },
      error: (err) => {
        this.notification.setNotification(err.error.status, err.error.message);
      },
    });
  }
}
