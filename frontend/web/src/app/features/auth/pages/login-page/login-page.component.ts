import { Component } from '@angular/core';
import { SolidButtonComponent } from '../../../../shared/components/solid-button/solid-button.component';
import { GlobalInputComponent } from '../../../../shared/components/global-input/global-input.component';
import {
  FormControl,
  FormControlName,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { NotificationService } from '../../../../core/services/notification/notification.service';

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
    if (this.loginData.valid) {
      console.log(this.loginData.getRawValue());
      this.auth.login(this.loginData.getRawValue()).subscribe({
        next: (response) => {
          console.log(response);
          this.notification.setNotification(response.status, response.message);
          console.log(response.token);
          localStorage.setItem('token', response.token);
          if (response.status) {
            this.router.navigate(['home']);
          }
        },
        error: (error) => {
          if (error.message) {
            this.notification.setNotification(
              error.error.status,
              error.error.message
            );
          } else {
            console.log(error);
          }
        },
      });
    } else {
      this.notification.setNotification(false, 'You input is not correct');
    }
  }
}
