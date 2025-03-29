import { NgFor, NgIf } from '@angular/common';
import { Component, inject } from '@angular/core';
import { CartItemComponent } from '../../../share/Components/cart-item/cart-item.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  imports: [NgIf,NgFor,CartItemComponent],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  cart=[1,1,1]

  private router=inject(Router)

  navigateToCheckOut(){
    this.router.navigate(['checkout']);
  }
}
