import { Component } from '@angular/core';
import { FeatureModule } from './Modules/feature/feature.module';
import { ShareModule } from './Modules/share/share.module';
import { HomeComponent } from './Modules/feature/Components/home/home.component';
import { NavbarComponent } from './Modules/share/Components/navbar/navbar.component';
import { FooterComponent } from './Modules/share/Components/footer/footer.component';
import { RouterOutlet } from '@angular/router';



@Component({
  selector: 'app-root',
  imports: [NavbarComponent,FooterComponent,FeatureModule,ShareModule,RouterOutlet],
  // imports: [HomeComponent, NavbarComponent,FooterComponent,FeatureModule,ShareModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
}
