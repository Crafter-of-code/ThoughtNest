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
  signinData = new FormGroup({
    userFirstName: new FormControl<string>('', {
      validators: [Validators.required],
      nonNullable: true,
    }),
    userMiddleName: new FormControl<string>('', {
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
    if (this.signinData.valid) {
      this.authService.signIn(this.signinData.getRawValue()).subscribe({
        next: (response) => {
          this.notification.status.set(response.status);
          this.notification.message.set(response.message);
          if (response.status) {
            this.route.navigate(['login']);
          }
        },
        error: (err) => {
          console.log(err);
          this.notification.status.set(false);
          this.notification.message.set(err.error.message);
        },
      });
    } else {
      this.notification.status.set(false);
      this.notification.message.set('Please check your detail correctly');
    }
  }
}
