import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-hamburger',
  imports: [CommonModule],
  templateUrl: './hamburger.component.html',
  styleUrl: './hamburger.component.css',
})
export class HamburgerComponent {
  hamburgerActive: boolean = false;
  @Output() hamburgerClicked = new EventEmitter<Event>();
  onBurgerClick() {
    this.hamburgerActive = !this.hamburgerActive;
    this.hamburgerClicked.emit();
  }
}
