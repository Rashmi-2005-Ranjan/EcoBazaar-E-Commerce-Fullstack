import { NgIf } from '@angular/common';
import { Component, inject, Input } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import {MatFormFieldModule} from '@angular/material/form-field';
import { Store } from '@ngrx/store';
import { StoreDevtools } from '@ngrx/store-devtools';
@Component({
  selector: 'app-signin',
  imports: [
    MatFormField,
    MatLabel,
    ReactiveFormsModule,
    MatFormFieldModule,
    NgIf,
  ],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css',
})
export class SigninComponent {
  private formBuilder = inject(FormBuilder);
  private store = inject(Store);
  @Input() changeTemplate: any;
  loginform: FormGroup = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]],
  });

  submitForm():void{
    if(this.loginform.valid){
      console.log('Login Request Data', this.loginform.value);
    }
  }
}
