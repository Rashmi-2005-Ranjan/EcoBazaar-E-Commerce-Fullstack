import { NgFor } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-nav-content',
  imports: [NgFor],
  templateUrl: './nav-content.component.html',
  styleUrl: './nav-content.component.css',
})
export class NavContentComponent {
  category: any;
  @Input() selectedSection: any;
}
