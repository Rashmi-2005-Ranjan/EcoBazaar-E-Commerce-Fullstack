import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { filters } from './Filter.data';
import { NgFor } from '@angular/common';
import { MatCheckboxModule } from '@angular/material/checkbox';

@Component({
  selector: 'app-products',
  imports: [MatMenuModule,MatButtonModule,MatDividerModule,MatIconModule,NgFor,MatCheckboxModule],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {
  filterData :any

  ngOnInit(){
    this.filterData=filters;
  }
}
