import { NgFor } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { navigation } from './nav-content';

@Component({
  selector: 'app-nav-content',
  imports: [NgFor],
  templateUrl: './nav-content.component.html',
  styleUrls: ['./nav-content.component.css'], // Corrected "styleUrls"
  standalone: true, // Add this if using standalone components
})
export class NavContentComponent {
  category:any;
  @Input() selectedSection: any;

  ngOnInit() {
    this.category = navigation;
    console.log(navigation)
    console.log(this.selectedSection);
  }
}
