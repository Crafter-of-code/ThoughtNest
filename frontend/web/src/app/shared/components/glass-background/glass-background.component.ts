import { CommonModule } from '@angular/common';
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-glass-background',
  imports: [CommonModule],
  templateUrl: './glass-background.component.html',
  styleUrl: './glass-background.component.css',
})
export class GlassBackgroundComponent implements OnChanges {
  @Input() padding: string | undefined = 'p-8';
  @Input() rounded: string = 'rounded-4xl';
  // @Input()
  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.padding);
  }
}
