import { Component } from '@angular/core';
import { SolidButtonComponent } from '../../../../shared/components/solid-button/solid-button.component';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validator,
  Validators,
} from '@angular/forms';
import { GlobalInputComponent } from '../../../../shared/components/global-input/global-input.component';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { NotificationService } from '../../../../core/services/notification/notification.service';
import { Router, RouterOutlet } from '@angular/router';
@Component({
  selector: 'app-signin-page',
  imports: [
    SolidButtonComponent,
    ReactiveFormsModule,
    GlobalInputComponent,
    CommonModule,
    RouterOutlet,
  ],
  templateUrl: './signin-page.component.html',
  styleUrl: './signin-page.component.css',
})
export class SigninPageComponent {
  constructor(
    private authService: AuthService,
    private notification: NotificationService,
    private route: Router
  ) {}
  disabled: boolean = false;
  loadingSpinner: boolean = false;
  signinData = new FormGroup({
    userFirstName: new FormControl<string>('', {
      validators: [Validators.required],
      nonNullable: true,
    }),
    userLastName: new FormControl<string>('', {
      validators: [Validators.required],
      nonNullable: true,
    }),
    userEmail: new FormControl<string>('', {
      validators: [Validators.required, Validators.email],
      nonNullable: true,
    }),
    userPassword: new FormControl<string>('', {
      validators: [Validators.required, Validators.minLength(8)],
      nonNullable: true,
    }),
  });
  signinHandler() {
    this.loadingSpinner = true;
    this.disabled = true;
    const payload = {
      userFirstName: (
        this.signinData.getRawValue().userFirstName ?? ''
      ).toLowerCase(),
      userLastName: (
        this.signinData.getRawValue().userLastName ?? ''
      ).toLowerCase(),
      userEmail: (this.signinData.getRawValue().userEmail ?? '').toLowerCase(),
      userPassword: (
        this.signinData.getRawValue().userPassword ?? ''
      ).toLowerCase(),
    };
    if (this.signinData.valid) {
      this.authService.signIn(this.signinData.getRawValue()).subscribe({
        next: (response) => {
          this.notification.status.set(response.status);
          this.notification.message.set(response.message);
          if (response.status) {
            this.route.navigate(['login']);
          }
          this.loadingSpinner = false;
          this.disabled = false;
        },
        error: (err) => {
          if ((err.status = 400)) {
            this.notification.status.set(false);
            this.notification.message.set(
              'We already found an account with this email address'
            );
          } else {
            this.notification.status.set(false);
            this.notification.message.set(err.error.message);
          }

          this.loadingSpinner = false;
          this.disabled = false;
        },
      });
    } else {
      this.notification.status.set(false);
      this.notification.message.set('Please check your detail correctly');
      this.loadingSpinner = false;
      this.disabled = false;
    }
  }
}
