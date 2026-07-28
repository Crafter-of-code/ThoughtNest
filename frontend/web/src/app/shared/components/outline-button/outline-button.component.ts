import { Component, Input } from '@angular/core';
import { LoadingSpinnerComponent } from '../loading-spinner/loading-spinner.component';
enum buttonType {
  button = 'button',
  submit = 'submit',
}
@Component({
  selector: 'app-outline-button',
  imports: [LoadingSpinnerComponent],
  templateUrl: './outline-button.component.html',
  styleUrl: './outline-button.component.css',
})
export class OutlineButtonComponent {
  @Input() type: string = buttonType.button;
  @Input() disabled: boolean = true;
  @Input() loadingSpinner = true;
  @Input() label: string = '';
}
