import { Component, ElementRef, ViewChild } from '@angular/core';
import { SolidButtonComponent } from '../../../../shared/components/solid-button/solid-button.component';
import { OutlineButtonComponent } from '../../../../shared/components/outline-button/outline-button.component';
import { GlassBackgroundComponent } from '../../../../shared/components/glass-background/glass-background.component';
import { LandingPageComponent } from '../../components/landing-page/landing-page.component';
import { UspContainerComponent } from '../../components/usp-container/usp-container.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-welcome-page',
  imports: [
    SolidButtonComponent,
    OutlineButtonComponent,
    GlassBackgroundComponent,
    LandingPageComponent,
    UspContainerComponent,
    RouterLink,
  ],
  templateUrl: './welcome-page.component.html',
  styleUrl: './welcome-page.component.css',
})
export class WelcomePageComponent {
  @ViewChild('to_scroll_section') to_scroll_section!: ElementRef;
  clickScroll() {
    if (!this.to_scroll_section) return;
    console.log('hello world');
    this.to_scroll_section.nativeElement.scrollIntoView({
      behavior: 'smooth',
      block: 'start',
    });
  }
}
