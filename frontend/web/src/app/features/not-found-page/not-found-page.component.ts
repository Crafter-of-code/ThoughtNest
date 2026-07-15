import { Component } from '@angular/core';
import { LandingPageComponent } from '../welcome/components/landing-page/landing-page.component';
import { GlassBackgroundComponent } from '../../shared/components/glass-background/glass-background.component';
import { SolidButtonComponent } from '../../shared/components/solid-button/solid-button.component';
import { OutlineButtonComponent } from '../../shared/components/outline-button/outline-button.component';

@Component({
  selector: 'app-not-found-page',
  imports: [
    LandingPageComponent,
    GlassBackgroundComponent,
    SolidButtonComponent,
    OutlineButtonComponent,
  ],
  templateUrl: './not-found-page.component.html',
  styleUrl: './not-found-page.component.css',
})
export class NotFoundPageComponent {}
