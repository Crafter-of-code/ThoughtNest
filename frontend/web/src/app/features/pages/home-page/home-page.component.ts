import { Component } from '@angular/core';
import { GlassBackgroundComponent } from '../../../shared/components/glass-background/glass-background.component';
import { LandingPageComponent } from '../../welcome/components/landing-page/landing-page.component';
import { MainHeaderComponent } from '../../../shared/header/main-header/main-header.component';
import { HamburgerComponent } from '../../../shared/header/components/hamburger/hamburger.component';

@Component({
  selector: 'app-home-page',
  imports: [
    GlassBackgroundComponent,
    LandingPageComponent,
    MainHeaderComponent,
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css',
})
export class HomePageComponent {}
