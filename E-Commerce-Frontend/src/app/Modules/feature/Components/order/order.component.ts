import { Component, inject } from '@angular/core';
import { MatCheckbox } from '@angular/material/checkbox';
import { OrderCardComponent } from './order-card/order-card.component';
import { NgFor } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-order',
  imports: [MatCheckbox, OrderCardComponent, NgFor],
  templateUrl: './order.component.html',
  styleUrl: './order.component.css',
})
export class OrderComponent {
  private router = inject(Router);
  orderFilter = [
    { value: 'on_the_way', label: 'On The Way' },
    { value: 'delivered', label: 'Delivered' },
    { value: 'cancelled', label: 'Cancelled' },
    { value: 'returned', label: 'Returned' },
  ];
  orders = [
    [1, 1],
    [1, 1, 1],
  ];

  navigateToOrderDetails = (id: number) => {
    this.router.navigate([`order/${id}`]);
  };
}
