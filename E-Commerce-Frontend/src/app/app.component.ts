import { Component } from '@angular/core';
import { HomeComponent } from './Modules/home/home.component';
import { NavbarComponent } from './Modules/share/navbar/navbar.component';
import { FooterComponent } from './Modules/share/footer/footer.component';



@Component({
  selector: 'app-root',
  imports: [HomeComponent, NavbarComponent,FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
}
