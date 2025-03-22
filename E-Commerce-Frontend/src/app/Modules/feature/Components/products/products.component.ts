import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { filters, singleFilter } from './Filter.data';
import { NgFor } from '@angular/common';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { mensPantsPage1 } from '../../../../../Data/pants/men_page1';
import { ProductCardComponent } from '../../../share/Components/product-card/product-card.component';

@Component({
  selector: 'app-products',
  imports: [MatMenuModule,MatButtonModule,MatDividerModule,MatIconModule,NgFor,MatCheckboxModule,MatRadioModule,ProductCardComponent],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {
  filterData :any;
  singleFilterData:any;
  manPants:any;
  ngOnInit(){
    this.filterData=filters;
    this.singleFilterData=singleFilter;
    this.manPants=mensPantsPage1;
  }
}
