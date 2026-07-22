import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MainHeaderComponent } from '../../../shared/header/main-header/main-header.component';

@Component({
  selector: 'app-layout-page',
  imports: [RouterOutlet, MainHeaderComponent],
  templateUrl: './layout-page.component.html',
  // styleUrl: './layout-page.component.css',
})
export class LayoutPageComponent {}
