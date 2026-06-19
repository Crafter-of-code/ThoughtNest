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
@Component({
  selector: 'app-signin-page',
  imports: [
    SolidButtonComponent,
    ReactiveFormsModule,
    GlobalInputComponent,
    CommonModule,
  ],
  templateUrl: './signin-page.component.html',
  styleUrl: './signin-page.component.css',
})
export class SigninPageComponent {
  signinData = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    middleName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    userEmail: new FormControl('', [Validators.required, Validators.email]),
    userPassword: new FormControl('', [
      Validators.required,
      Validators.minLength(8),
    ]),
  });
  signinHandler() {
    console.log();
    if (this.signinData.invalid) {
      console.log(this.signinData.value);
    } else {
      console.log('form is not valid');
    }
  }
}
