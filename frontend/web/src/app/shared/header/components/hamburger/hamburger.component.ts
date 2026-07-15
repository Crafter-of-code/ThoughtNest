import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-hamburger',
  imports: [CommonModule],
  templateUrl: './hamburger.component.html',
  styleUrl: './hamburger.component.css',
})
export class HamburgerComponent {
  hamburgerActive: boolean = false;
  onBurgerClick() {
    console.log('hamburger clicked');
    this.hamburgerActive = !this.hamburgerActive;
  }
}
