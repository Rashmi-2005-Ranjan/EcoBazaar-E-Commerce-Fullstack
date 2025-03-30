import { Component } from '@angular/core';
import { AddressCardComponent } from '../../../share/Components/address-card/address-card.component';
import { OrderCardComponent } from '../order/order-card/order-card.component';
import { NgFor } from '@angular/common';
import { ShareModule } from '../../../share/share.module';
import { OrderTrackerComponent } from '../../../share/Components/order-tracker/order-tracker.component';

@Component({
  selector: 'app-order-details',
  imports: [
    AddressCardComponent,
    OrderCardComponent,
    NgFor,
    OrderTrackerComponent,
  ],
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.css',
})
export class OrderDetailsComponent {
  orders = [1, 1, 1];

  steps = [
    { id: 0, title: 'PLACED', isCompleted: true },
    { id: 1, title: 'CONFIRM', isCompleted: true },
    { id: 2, title: 'SHIPPED', isCompleted: false },
    { id: 3, title: 'DELIVERED', isCompleted: false },
  ];
}
