import { Component } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';


@Component({
  selector: 'app-cart-item',
  imports: [MatButtonModule,MatIconModule],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.css'
})
export class CartItemComponent {
  updateCartItem(num:number){
    console.log(num);
  }
  removeCartItem(){
    console.log("Remove Cart Item")
  }
}
