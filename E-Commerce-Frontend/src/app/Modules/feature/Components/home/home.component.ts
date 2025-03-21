import { Component } from '@angular/core';
import { MainCarouselComponent } from './main-carousel/main-carousel.component';
import { ProductSliderComponent } from './product-slider/product-slider.component';
import { gounsPage1 } from '../../../../../Data/Gouns/gouns';
import { lehngacholiPage2 } from '../../../../../Data/Saree/lenghaCholiPage2';
import { mens_kurta } from '../../../../../Data/Men/men_kurta';
import { mensShoesPage1 } from '../../../../../Data/shoes';
import { menJeans } from '../../../../../Data/Men/men_jeans';
@Component({
  selector: 'app-home',
  imports: [MainCarouselComponent, ProductSliderComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  menJeans: any;
  womenGouns: any;
  lehangaCholi: any;
  menKurtas: any;
  shoes: any;
  ngOnInit() {
    console.log(gounsPage1, lehngacholiPage2, mens_kurta, mensShoesPage1, menJeans);
    this.menJeans = menJeans.slice(0, 5);
    this.womenGouns = gounsPage1.slice(0, 5);
    this.lehangaCholi = lehngacholiPage2.slice(0, 5);
    this.menKurtas = mens_kurta.slice(0, 5);
    this.shoes = mensShoesPage1.slice(0, 5);
  }
  
}
