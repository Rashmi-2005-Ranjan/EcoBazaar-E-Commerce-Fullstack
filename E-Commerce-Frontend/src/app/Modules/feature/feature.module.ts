import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FeaturesComponent } from './Components/features.component';
import { HomeComponent } from './Components/home/home.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import{MatDividerModule} from '@angular/material/divider';
import { ProductsComponent } from './Components/products/products.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {MatRadioModule} from '@angular/material/radio';
import { ShareModule } from '../share/share.module';



@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    FeaturesComponent,
    HomeComponent,
    ProductsComponent,
    MatMenuModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    MatCheckboxModule,
    MatRadioModule,
    ShareModule
  ],
  exports:[
    FeaturesComponent,
    HomeComponent,
    ProductsComponent,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    MatCheckboxModule,
    ShareModule
  ]
})
export class FeatureModule { }
