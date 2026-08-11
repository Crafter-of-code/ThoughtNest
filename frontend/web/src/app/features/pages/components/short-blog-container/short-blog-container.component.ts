import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  Signal,
  SimpleChanges,
} from '@angular/core';
import { HomeSkeletonUiComponent } from '../home-skeleton-ui/home-skeleton-ui.component';
import { CommonModule } from '@angular/common';
import { shortBlogResponseType } from '../../../../types/BlogTypes';
import { RouterLink } from '@angular/router';
type latestBlogDataType = {
  blogTitle: string;
  blogContent: string;
  userName: string;
  publicId: string;
  userEmail: string;
  createdAt: Date;
  blogId: string;
  userImageUrl: string;
};
//, RouterLink
@Component({
  selector: 'app-short-blog-container',
  imports: [HomeSkeletonUiComponent, CommonModule, RouterLink],
  templateUrl: './short-blog-container.component.html',
  styleUrl: './short-blog-container.component.css',
  standalone: true,
})
export class ShortBlogContainerComponent implements OnInit, OnChanges {
  @Input() containerHeading: string = '';
  @Input() skeletonRendering: number[] = [];
  @Input() blogData?: shortBlogResponseType | null = [];
  @Input() skeletonLoading: boolean | undefined = true;
  @Output() blogIdSender = new EventEmitter<string>();
  constructor() {}
  ngOnInit(): void {}
  ngOnChanges(changes: SimpleChanges): void {}
  sendBlogId(blogId: string) {
    this.blogIdSender.emit(blogId);
  }
}
