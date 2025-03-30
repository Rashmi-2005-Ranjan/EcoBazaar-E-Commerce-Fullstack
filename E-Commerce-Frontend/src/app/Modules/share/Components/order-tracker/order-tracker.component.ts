import { NgClass, NgFor, NgIf } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-order-tracker',
  imports: [NgClass, MatIconModule, NgFor, NgIf],
  templateUrl: './order-tracker.component.html',
  styleUrl: './order-tracker.component.css',
})
export class OrderTrackerComponent {
  @Input() activeStep: any;
  @Input() steps: any;
}
