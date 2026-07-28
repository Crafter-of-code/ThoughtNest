import { JsonPipe, NgClass } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { LoadingSpinnerComponent } from '../../../../shared/components/loading-spinner/loading-spinner.component';

@Component({
  selector: 'app-small-button-one',
  imports: [NgClass, LoadingSpinnerComponent],
  templateUrl: './small-button-one.component.html',
  styleUrl: './small-button-one.component.css',
})
export class SmallButtonOneComponent {
  @Output() getId = new EventEmitter<string | number>();
  @Input() id: string | number = 0;
  @Input() class: string = '';
  @Input() disabled: boolean = false;
  @Input() loadingSpinner = false;
  @Input() label: string = '';
  sendBlogId(id: string | number) {
    this.getId.emit(id);
  }
}
