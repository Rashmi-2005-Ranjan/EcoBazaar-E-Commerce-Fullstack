import { Component, inject } from '@angular/core';
import { AddressCardComponent } from '../../../../share/Components/address-card/address-card.component';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
@Component({
  selector: 'app-address-form',
  imports: [
    AddressCardComponent,
    NgIf,
    NgFor,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
  ],
  templateUrl: './address-form.component.html',
  styleUrl: './address-form.component.css',
})
export class AddressFormComponent {
  addresses = [1, 1, 1, 1];
  private formBuilder = inject(FormBuilder);

  myForm: FormGroup = this.formBuilder.group({
    firstName: ['', Validators.required],
    LastName: ['', Validators.required],
    streetAddress: ['', Validators.required],
    city: ['', Validators.required],
    state: ['', Validators.required],
    zipCode: ['', Validators.required],
    mobile: ['', Validators.required],
  });
  handleCreateOrder(item: any) {}
  handleSubmit() {
    const formValue = this.myForm.value;
    console.log('Form Data', formValue);
  }
}
