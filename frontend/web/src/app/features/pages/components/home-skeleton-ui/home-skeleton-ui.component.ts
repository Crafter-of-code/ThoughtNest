import { Component } from '@angular/core';
import { SkeletonUiComponent } from '../../../../shared/components/skeleton-ui/skeleton-ui.component';

@Component({
  selector: 'app-home-skeleton-ui',
  imports: [SkeletonUiComponent],
  templateUrl: './home-skeleton-ui.component.html',
  styleUrl: './home-skeleton-ui.component.css',
})
export class HomeSkeletonUiComponent {}
