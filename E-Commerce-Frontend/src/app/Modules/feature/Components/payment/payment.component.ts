import { Component } from '@angular/core';
import { AddressCardComponent } from '../../../share/Components/address-card/address-card.component';
import { CartItemComponent } from '../../../share/Components/cart-item/cart-item.component';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-payment',
  imports: [AddressCardComponent, CartItemComponent, NgIf, NgFor],
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css',
})
export class PaymentComponent {
  products = [1,1,1];
}
