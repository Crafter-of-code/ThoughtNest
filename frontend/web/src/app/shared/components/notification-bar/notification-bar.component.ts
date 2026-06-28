import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NotificationService } from '../../../core/services/notification/notification.service';

@Component({
  selector: 'app-notification-bar',
  imports: [CommonModule],
  templateUrl: './notification-bar.component.html',
  styleUrl: './notification-bar.component.css',
})
export class NotificationBarComponent {
  constructor(public notification: NotificationService) {}
}
