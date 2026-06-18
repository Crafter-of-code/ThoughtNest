import { Component, Input } from '@angular/core';
enum buttonType {
  button = 'button',
  submit = 'submit',
}
@Component({
  selector: 'app-outline-button',
  imports: [],
  templateUrl: './outline-button.component.html',
  styleUrl: './outline-button.component.css',
})
export class OutlineButtonComponent {
  @Input() type: string = buttonType.button;
}
