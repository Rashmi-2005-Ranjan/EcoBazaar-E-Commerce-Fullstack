import { NgClass, NgFor } from '@angular/common';
import { Component } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';


@Component({
  selector: 'app-star-rating',
  imports: [NgFor,NgClass,MatIconModule],
  templateUrl: './star-rating.component.html',
  styleUrl: './star-rating.component.css',
})
export class StarRatingComponent {
  stars: any;
  maxRating = 5;
  initialRating = 3;
  currentRating = 0;

  constructor() {
    this.stars = Array(this.maxRating)
      .fill(0)
      .map((_, i) => i + 1);
    this.currentRating = this.initialRating;
  }

  rate(rating: number) {
    this.currentRating = rating;
  }
}
