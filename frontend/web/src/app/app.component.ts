import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NotificationBarComponent } from './shared/components/notification-bar/notification-bar.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NotificationBarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'web';
}
