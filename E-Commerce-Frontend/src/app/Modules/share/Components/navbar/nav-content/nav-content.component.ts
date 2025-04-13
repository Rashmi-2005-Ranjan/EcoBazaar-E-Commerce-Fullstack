import { NgFor } from '@angular/common';
import { Component, inject, Input, OnInit } from '@angular/core';
import { navigation } from './nav-content';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-content',
  imports: [NgFor],
  templateUrl: './nav-content.component.html',
  styleUrls: ['./nav-content.component.css'], // Corrected "styleUrls"
  standalone: true, // Add this if using standalone components
})
export class NavContentComponent {
  private router=inject(Router)
  category:any;
  @Input() selectedSection: any;

  ngOnInit() {
    this.category = navigation;
  }

  handleNavigate=(path:any)=>{
    this.router.navigate([path])
  }
}
