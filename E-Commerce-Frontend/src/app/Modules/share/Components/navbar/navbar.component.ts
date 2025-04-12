import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { Component, HostListener, inject, Injectable } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { MatMenuModule } from '@angular/material/menu';
import { NgIf } from '@angular/common';
import { NavContentComponent } from './nav-content/nav-content.component';
import { MatDialog } from '@angular/material/dialog';
import { AuthComponent } from '../../../auth/auth.component';
import { UserService } from '../../../../State/User/User Service/user.service';
import { select, Store } from '@ngrx/store';
import { AppState } from '../../../../Models/Appstate.interface';
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
  userProfile: any;
  private router = inject(Router);
  private userService = inject(UserService);
  constructor(private store: Store<AppState>) {}
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

  private dialogue = inject(MatDialog);
  handleOpenLoginModel = () => {
    this.dialogue.open(AuthComponent, {
      width: '500px',
      disableClose: false,
    });
  };


  handleLogout=()=>{
    this.userService.logout();
  }

  ngOnInit() {
    if (localStorage.getItem('jwt')) this.userService.getUserProfile();
    this.store.pipe(select((store) => store.user)).subscribe((user) => {
      this.userProfile = user.userProfile;
      if (user.userProfile) {
        this.dialogue.closeAll();
      }
    });
  }
}
