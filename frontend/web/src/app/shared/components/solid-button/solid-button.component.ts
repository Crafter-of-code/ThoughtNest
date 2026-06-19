import { Component, Input } from '@angular/core';
enum buttonType {
  button = 'button',
  submit = 'submit',
}
@Component({
  selector: 'app-solid-button',
  imports: [],
  templateUrl: './solid-button.component.html',
  styleUrl: './solid-button.component.css',
})
export class SolidButtonComponent {
  @Input() type: string = buttonType.button;
  @Input() title: string = '';
}
