import {
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { RouterLink } from '@angular/router';
import { Subject, interval, startWith, switchMap, takeUntil } from 'rxjs';

import { SolidButtonComponent } from '../../../../shared/components/solid-button/solid-button.component';
import { OutlineButtonComponent } from '../../../../shared/components/outline-button/outline-button.component';
import { GlassBackgroundComponent } from '../../../../shared/components/glass-background/glass-background.component';
import { LandingPageComponent } from '../../components/landing-page/landing-page.component';
import { UspContainerComponent } from '../../components/usp-container/usp-container.component';

import { ServerStatusService } from '../../../../core/services/server-status/server-status.service';
import { NotificationService } from '../../../../core/services/notification/notification.service';

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
export class WelcomePageComponent implements OnInit, OnDestroy {
  @ViewChild('to_scroll_section')
  toScrollSection!: ElementRef<HTMLElement>;

  buttonDisable = true;
  loadingSpinner = true;

  private readonly destroy$ = new Subject<void>();

  constructor(
    private readonly serverStatus: ServerStatusService,
    private readonly notification: NotificationService
  ) {}

  ngOnInit(): void {
    this.notification.setNotification(
      true,
      'Please wait while we connect you to our servers.'
    );

    interval(5000)
      .pipe(
        startWith(0), // First request immediately
        switchMap(() => this.serverStatus.getServerStatus()),
        takeUntil(this.destroy$)
      )
      .subscribe({
        next: (response) => {
          this.buttonDisable = false;
          this.loadingSpinner = false;

          this.notification.setNotification(true, 'Connected successfully.');

          // Stop polling after successful connection
          this.destroy$.next();
          this.destroy$.complete();
        },
        error: () => {
          console.log('Server is offline. Retrying in 5 seconds...');
        },
      });
  }

  clickScroll(): void {
    if (!this.toScrollSection) return;

    this.toScrollSection.nativeElement.scrollIntoView({
      behavior: 'smooth',
      block: 'start',
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
