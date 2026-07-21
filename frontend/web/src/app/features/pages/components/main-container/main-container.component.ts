import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-main-container',
  imports: [],
  templateUrl: './main-container.component.html',
  styleUrl: './main-container.component.css',
})
export class MainContainerComponent {
  // @Input() closeButtonClicked = true;
  @Output() closeButtonClicked = new EventEmitter<Event>();
  clicked(event: Event) {
    this.closeButtonClicked.emit(event);
  }
}
