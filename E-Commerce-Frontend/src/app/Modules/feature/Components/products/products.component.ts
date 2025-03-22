import { Component, inject } from '@angular/core';
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
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-products',
  imports: [
    MatMenuModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    NgFor,
    MatCheckboxModule,
    MatRadioModule,
    ProductCardComponent,
  ],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css',
})
export class ProductsComponent {
  filterData: any;
  singleFilterData: any;
  manPants: any;

  private activatedRoute = inject(ActivatedRoute);

  private router = inject(Router);
  ngOnInit() {
    this.filterData = filters;
    this.singleFilterData = singleFilter;
    this.manPants = mensPantsPage1;
  }

  handleMultileSelectFilter(value: string, sectionId: string) {
    const queryParams = { ...this.activatedRoute.snapshot.queryParams };
    console.log('Query Params', queryParams);
    const filterValues = queryParams[sectionId]
      ? queryParams[sectionId].split(',')
      : [];

    const valueIndex = filterValues.indexOf(value);

    if (valueIndex != -1) {
      filterValues.splice(valueIndex, 1);
    } else {
      filterValues.push(value);
    }

    if (filterValues.length > 0) {
      queryParams[sectionId] = filterValues.join(',');
    } else {
      delete queryParams[sectionId];
    }

    this.router.navigate([], { queryParams });
  }

  handleSingleSelectFilter(value:string,sectionId:string){
    const queryParams={...this.activatedRoute.snapshot.queryParams};
    queryParams[sectionId]=value;

    this.router.navigate([],{queryParams})
  }
}
