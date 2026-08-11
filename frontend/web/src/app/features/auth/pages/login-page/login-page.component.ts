import { Component } from '@angular/core';
import { SolidButtonComponent } from '../../../../shared/components/solid-button/solid-button.component';
import { GlobalInputComponent } from '../../../../shared/components/global-input/global-input.component';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { NotificationService } from '../../../../core/services/notification/notification.service';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [
    SolidButtonComponent,
    GlobalInputComponent,
    ReactiveFormsModule,
    RouterLink,
    CommonModule,
  ],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css',
})
export class LoginPageComponent {
  buttonDisabled: boolean = false;
  buttonLoadingSpinner: boolean = false;
  constructor(
    private auth: AuthService,
    private notification: NotificationService,
    private router: Router
  ) {}
  loginData = new FormGroup({
    userEmail: new FormControl('', [Validators.required]),
    userPassword: new FormControl('', [Validators.required]),
  });
  loginHandler() {
    this.buttonDisabled = true;
    this.buttonLoadingSpinner = true;
    if (this.loginData.valid) {
      const payload = {
        userEmail: (this.loginData.getRawValue().userEmail ?? '').toLowerCase(),
        userPassword: (
          this.loginData.getRawValue().userPassword ?? ''
        ).toLowerCase(),
      };
      this.auth
        .login(payload)
        .pipe(
          finalize(() => {
            this.buttonDisabled = false;
            this.buttonLoadingSpinner = false;
          })
        )
        .subscribe({
          next: (response) => {
            this.notification.setNotification(
              response.status,
              response.message
            );
            if (response.token && response.publicId && response.status) {
              localStorage.setItem('token', response.token);
              localStorage.setItem('publicId', response.publicId);
              this.router.navigate(['home']);
            }
          },
          error: (error) => {
            if (error.status == 401) {
              this.notification.setNotification(
                false,
                'You are not a memeber of \n throughtNest '
              );
              this.router.navigate(['/', 'signin']);
            } else if (error.message) {
              this.notification.setNotification(
                error.error.status,
                error.error.message
              );
            } else {
              this.notification.setNotification(
                false,
                'Unable to communicate to backend'
              );
            }
          },
        });
    } else {
      this.notification.setNotification(
        false,
        'Please check your detail carefully'
      );
    }
  }
}
