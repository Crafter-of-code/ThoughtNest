import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HomeSkeletonUiComponent } from '../../../components/home-skeleton-ui/home-skeleton-ui.component';
import { RouterLink } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';
import {
  getFollowingTopThreeBlogResponseData,
  shortBlogResponseType,
} from '../../../../../types/BlogTypes';
@Component({
  selector: 'app-short-blog-container',
  imports: [HomeSkeletonUiComponent, RouterLink, DatePipe, CommonModule],
  templateUrl: './short-blog-container.component.html',
  styleUrl: './short-blog-container.component.css',
})
export class ShortBlogContainerComponent implements OnInit {
  @Input() blogData: shortBlogResponseType = [];
  @Input() skeletonLoading: boolean = true;
  @Output() blogIdSender = new EventEmitter<string>();
  sendBlogId(blogId: string) {
    this.blogIdSender.emit(blogId);
  }
  ngOnInit(): void {}
}
