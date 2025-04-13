import { NgIf } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CartService } from '../../../../State/Cart/Cart Service/cart.service';

@Component({
  selector: 'app-cart-item',
  imports: [MatButtonModule, MatIconModule, NgIf],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.css',
})
export class CartItemComponent {
  @Input() cartItem: any;
  @Input() showButton: any;

  constructor(private cartService: CartService) {}

  updateCartItem(num: number) {
    console.log(num);
    this.cartService.updateCartItem({
      cartItemId: this.cartItem.id,
      data: {
        quantity: num + this.cartItem.quantity,
      },
    });
  }
  removeCartItem() {
    console.log('Remove Cart Item');
    this.cartService.removeCartItem(this.cartItem.id);
  }
}
