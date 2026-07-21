import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  SimpleChanges,
} from '@angular/core';
import { MainContainerComponent } from '../main-container/main-container.component';
import { CommonModule } from '@angular/common';
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
@Component({
  selector: 'app-blog-container',
  imports: [MainContainerComponent, CommonModule],
  templateUrl: './blog-container.component.html',
  styleUrl: './blog-container.component.css',
})
export class BlogContainerComponent implements OnChanges {
  @Output() closeBlogContainer = new EventEmitter<Event>();
  @Input() singleBlogData: singleBlogDataType | undefined;
  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.singleBlogData);
  }
  clicked(event: Event) {
    this.closeBlogContainer.emit();
  }
}
