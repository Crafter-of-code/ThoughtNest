import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  SimpleChanges,
} from '@angular/core';
import { LoadingSpinnerComponent } from '../loading-spinner/loading-spinner.component';
import { CommonModule } from '@angular/common';
enum buttonType {
  button = 'button',
  submit = 'submit',
}
@Component({
  selector: 'app-solid-button',
  imports: [LoadingSpinnerComponent, CommonModule],
  templateUrl: './solid-button.component.html',
  styleUrl: './solid-button.component.css',
})
export class SolidButtonComponent implements OnChanges {
  constructor() {}
  @Input() type: string = buttonType.button;
  @Input() title: string = '';
  @Output() clicked = new EventEmitter();
  @Input() disabled: boolean = false;
  @Input() loadingSpinner = false;
  @Input() label: string = '';
  onClick(event: Event) {
    // event.preventDefault();
    this.clicked.emit(event);
  }
  ngOnChanges(changes: SimpleChanges): void {}
}
