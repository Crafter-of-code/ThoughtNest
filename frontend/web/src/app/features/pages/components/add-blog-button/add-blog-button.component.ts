import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-add-blog-button',
  imports: [],
  templateUrl: './add-blog-button.component.html',
  styleUrl: './add-blog-button.component.css',
})
export class AddBlogButtonComponent {
  @Output() clicked = new EventEmitter<MouseEvent>();
  onClick(event: MouseEvent) {
    this.clicked.emit(event);
  }
}
