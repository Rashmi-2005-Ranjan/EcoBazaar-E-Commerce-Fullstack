import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { AuthComponent } from './auth.component';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    SigninComponent,
    SignupComponent,
    AuthComponent,
    CommonModule
  ]
})
export class AuthModule { }
