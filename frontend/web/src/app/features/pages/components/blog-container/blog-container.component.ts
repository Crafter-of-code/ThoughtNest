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
import { singleBlogResponseType } from '../../../../types/BlogTypes';
@Component({
  selector: 'app-blog-container',
  imports: [MainContainerComponent, CommonModule],
  templateUrl: './blog-container.component.html',
  styleUrl: './blog-container.component.css',
})
export class BlogContainerComponent implements OnChanges {
  @Output() closeBlogContainer = new EventEmitter<Event>();
  @Input() singleBlogData: singleBlogResponseType | null = null;
  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.singleBlogData);
  }
  clicked(event: Event) {
    this.closeBlogContainer.emit();
  }
  async sendLikesToBlog(blogId: string) {
    let publicIdOfUser = await localStorage.getItem('publicId');
    console.log(publicIdOfUser);
    console.log(blogId);
  }
}
