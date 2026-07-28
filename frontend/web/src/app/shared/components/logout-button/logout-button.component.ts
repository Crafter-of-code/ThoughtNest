import { Component } from '@angular/core';
import { AuthService } from '../../../core/services/auth/auth.service';
import { NotificationService } from '../../../core/services/notification/notification.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout-button',
  imports: [],
  templateUrl: './logout-button.component.html',
  styleUrl: './logout-button.component.css',
})
export class LogoutButtonComponent {
  constructor(
    private auth: AuthService,
    private notification: NotificationService,
    private nav: Router
  ) {}
  logout() {
    console.log('button clicked');
    this.auth.logout().subscribe({
      next: (Response) => {
        this.notification.setNotification(true, 'You successfully logged out');
        localStorage.removeItem('token');
        this.nav.navigate(['/']);
      },
      error: (err) => {
        console.log(err);
        this.notification.setNotification(
          true,
          'We are unable to close your account right now'
        );
      },
    });
  }
}
