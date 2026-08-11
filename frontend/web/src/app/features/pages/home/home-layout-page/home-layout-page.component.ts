import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { GlassBackgroundComponent } from '../../../../shared/components/glass-background/glass-background.component';
import { AddBlogButtonComponent } from '../../components/add-blog-button/add-blog-button.component';
import { UploadBlogComponent } from '../../components/upload-blog/upload-blog.component';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-home-layout-page',
  imports: [
    RouterOutlet,
    GlassBackgroundComponent,
    RouterLink,
    RouterLinkActive,
    AddBlogButtonComponent,
    UploadBlogComponent,
    NgIf,
  ],
  templateUrl: './home-layout-page.component.html',
  styleUrl: './home-layout-page.component.css',
})
export class HomeLayoutPageComponent {
  showUploadBlogContainer: boolean = false;
  UploadBlogContainerShower(event: Event) {
    this.showUploadBlogContainer = !this.showUploadBlogContainer;
  }
}
