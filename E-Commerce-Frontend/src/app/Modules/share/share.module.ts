import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './Components/navbar/navbar.component';
import { FooterComponent } from './Components/footer/footer.component';
import { NavContentComponent } from './Components/navbar/nav-content/nav-content.component';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { ProductCardComponent } from './Components/product-card/product-card.component';
import { CartItemComponent } from './Components/cart-item/cart-item.component';



@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    FooterComponent,
    NavbarComponent,
    ProductCardComponent,
    CartItemComponent
  ],
  exports:[
    NavbarComponent,FooterComponent,
    ProductCardComponent,CartItemComponent
  ]
})
export class ShareModule { }
