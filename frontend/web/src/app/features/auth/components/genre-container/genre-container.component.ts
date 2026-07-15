import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-genre-container',
  imports: [],
  templateUrl: './genre-container.component.html',
  styleUrl: './genre-container.component.css',
})
export class GenreContainerComponent {
  @Output() clicked = new EventEmitter();
  @Input() genreId: string = '';
  buttonClicked() {
    this.clicked.emit(this.genreId);
  }
}
