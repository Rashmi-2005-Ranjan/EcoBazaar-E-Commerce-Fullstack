import { NgFor } from '@angular/common';
import { Component, inject, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-card',
  imports: [],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.css',
})
export class ProductCardComponent {
  @Input() product: any;
  private router = inject(Router);
  navigateToProductPage() {
    this.router.navigate([`/product-details/${this.product.id}}`]);
  }
}
