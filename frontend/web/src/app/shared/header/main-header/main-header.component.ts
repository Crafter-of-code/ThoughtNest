import { Component, Input } from '@angular/core';
import { HamburgerComponent } from '../components/hamburger/hamburger.component';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { GlassBackgroundComponent } from '../../components/glass-background/glass-background.component';
import { LogoutButtonComponent } from '../../components/logout-button/logout-button.component';
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
    LogoutButtonComponent,
    LogoutButtonComponent,
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
      pageName: 'Memebers',
      pageUrl: '/members',
    },
    // {
    //   pageName: 'Setting',
    //   pageUrl: '/setting',
    // },
    {
      pageName: 'My Profile',
      pageUrl: '/myprofile',
    },
  ];
  showMobileMenue: boolean = false;

  hamburgerClicked(event: Event) {
    this.showMobileMenue = !this.showMobileMenue;
  }
}
