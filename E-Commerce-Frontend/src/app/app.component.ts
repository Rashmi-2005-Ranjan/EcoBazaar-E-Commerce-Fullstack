import { Component } from '@angular/core';
import { HomeComponent } from './Components/home/home.component';
import { NavbarComponent } from './Components/share/navbar/navbar.component';
import { FooterComponent } from './Components/share/footer/footer.component';


@Component({
  selector: 'app-root',
  imports: [HomeComponent, NavbarComponent,FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'E-Commerce-Frontend';
}
