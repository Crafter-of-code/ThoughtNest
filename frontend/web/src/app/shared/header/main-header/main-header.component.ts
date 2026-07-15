import { Component } from '@angular/core';
import { HamburgerComponent } from '../components/hamburger/hamburger.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-main-header',
  imports: [HamburgerComponent, RouterLink],
  templateUrl: './main-header.component.html',
  styleUrl: './main-header.component.css',
})
export class MainHeaderComponent {}
