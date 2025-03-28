import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {MatRadioModule} from '@angular/material/radio';
import { ProductReviewCardComponent } from './product-review-card/product-review-card.component';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-product-details',
  imports: [MatRadioModule,FormsModule,ProductReviewCardComponent,NgFor],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent {
  reviews=[1,1,1,1,1,1];

  selectedSize:any;
  handleAddToCart(){
    console.log("Selected Size: ", this.selectedSize);
  }
}
