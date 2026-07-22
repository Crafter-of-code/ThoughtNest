import { Component, Input } from '@angular/core';
import { HamburgerComponent } from '../components/hamburger/hamburger.component';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { GlassBackgroundComponent } from '../../components/glass-background/glass-background.component';
type navigationDataType = {
  pageName: string;
  pageUrl: string;
};
@Component({
  selector: 'app-main-header',
  imports: [
    HamburgerComponent,
    RouterLink,
    CommonModule,
    GlassBackgroundComponent,
    RouterLinkActive,
  ],
  templateUrl: './main-header.component.html',
  styleUrl: './main-header.component.css',
})
export class MainHeaderComponent {
  navigationData: navigationDataType[] = [
    {
      pageName: 'Home',
      pageUrl: '/home',
    },
    {
      pageName: 'Setting',
      pageUrl: '/setting',
    },
  ];
  showMobileMenue: boolean = false;

  hamburgerClicked(event: Event) {
    this.showMobileMenue = !this.showMobileMenue;
  }
}
