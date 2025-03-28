import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {MatRadioModule} from '@angular/material/radio';
import { ProductReviewCardComponent } from './product-review-card/product-review-card.component';
import { NgFor } from '@angular/common';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { ProductCardComponent } from '../../../share/Components/product-card/product-card.component';
import { lehngacholiPage2 } from '../../../../../Data/Saree/lenghaCholiPage2';
import { StarRatingComponent } from '../../../share/Components/star-rating/star-rating.component';


@Component({
  selector: 'app-product-details',
  imports: [MatRadioModule,FormsModule,ProductReviewCardComponent,NgFor,MatProgressBarModule,ProductCardComponent,StarRatingComponent],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent {
  reviews=[1,1,1,1,1,1];
  relatableProducts:any;

  ngOnInit(){
    this.relatableProducts=lehngacholiPage2;
    console.log(this.relatableProducts);
  }
  selectedSize:any;
  handleAddToCart(){
    console.log("Selected Size: ", this.selectedSize);
  }
}
