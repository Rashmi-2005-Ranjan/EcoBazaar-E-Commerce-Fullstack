import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-card',
  imports: [],
  templateUrl: './order-card.component.html',
  styleUrl: './order-card.component.css',
})
export class OrderCardComponent {
  private router = inject(Router);
  navigateToOrderDetails = (id: number) => {
    this.router.navigate([`orders/${id}`]);
  };
}
