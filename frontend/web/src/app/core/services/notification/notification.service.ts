import { Injectable, signal, WritableSignal } from '@angular/core';
import { single } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  constructor() {}
  status: WritableSignal<boolean> = signal(false);
  message: WritableSignal<string> = signal('');
  setNotification(status: boolean, message: string) {
    this.status.set(status);
    this.message.set(message);
    setTimeout(() => {
      this.status.set(false);
      this.message.set('');
    }, 3000);
  }
}
