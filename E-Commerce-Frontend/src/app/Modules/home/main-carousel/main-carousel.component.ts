import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { NgFor } from '@angular/common';
import { homeCarouselData } from '../../../../Data/mainCarousel';

@Component({
  selector: 'app-main-carousel',
  imports: [RouterLink, NgFor],
  templateUrl: './main-carousel.component.html',
  styleUrl: './main-carousel.component.css',
})
export class MainCarouselComponent {
  currentSlide = 0;
  interval: any;
  carouselData: any;
  ngOnInit() {
    this.carouselData = homeCarouselData;
    this.autoPlay();
  }
  autoPlay() {
    setInterval(() => {
      this.nextSlide();
    }, 2000);
  }

  nextSlide() {
    this.currentSlide = (this.currentSlide + 1) % this.carouselData.length;
  }
}
