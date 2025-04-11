import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { Component, HostListener, inject, Injectable } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { MatMenuModule } from '@angular/material/menu';
import { NgIf } from '@angular/common';
import { NavContentComponent } from './nav-content/nav-content.component';
import {MatDialog} from '@angular/material/dialog';
import { AuthComponent } from '../../../auth/auth.component';
@Component({
  selector: 'app-navbar',
  imports: [
    RouterLink,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    NgIf,
    NavContentComponent,
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent {
  currentSection: any;
  isNavbarContentOpen: any;

  private router=inject(Router);

  openNavbarContent(section: any) {
    this.isNavbarContentOpen = true;
    this.currentSection = section;
  }
  closeNavbarContent() {
    this.isNavbarContentOpen = false;
  }
  navigateTo(path: any) {
    this.router.navigate([path]);
  }
  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    const modalContainer = document.querySelector('.modal-container');
    const openButtons = document.querySelectorAll('.open-button');

    let clickInsideButton = false;

    openButtons.forEach((button: Element) => {
      if (button.contains(event.target as Node)) {
        clickInsideButton = true;
      }
    });

    if (modalContainer && !clickInsideButton && this.isNavbarContentOpen) {
      this.closeNavbarContent();
    }
  }

  private dialogue=inject(MatDialog);
  handleOpenLoginModel=()=>{
    this.dialogue.open(AuthComponent,{
      width:"400px",
      disableClose:false
    })
  }
}
