import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './Components/navbar/navbar.component';
import { FooterComponent } from './Components/footer/footer.component';
import { NavContentComponent } from './Components/navbar/nav-content/nav-content.component';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { ProductCardComponent } from './Components/product-card/product-card.component';



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
    ProductCardComponent
  ],
  exports:[
    NavbarComponent,FooterComponent,
    ProductCardComponent
  ]
})
export class ShareModule { }
