import { Component, EventEmitter, Input, Output } from '@angular/core';
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
  @Output() clicked = new EventEmitter<Event>();
  @Input() type: string = buttonType.button;
  @Input() disabled: boolean = true;
  @Input() loadingSpinner = true;
  @Input() label: string = '';
  clickEvent(event: Event) {
    this.clicked.emit(event);
  }
}
