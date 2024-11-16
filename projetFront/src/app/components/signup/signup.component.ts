import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
onSubmit() {
throw new Error('Method not implemented.');
}
  myform2: FormGroup;
  constructor(private formBuilder: FormBuilder) {
    this.myform2 = formBuilder.group({
      Fname: ['', Validators.required],
      Sname: ['', Validators.required],
      PhoneN: ['', [Validators.required]],
      EmailC: ['', [Validators.email, Validators.required]],
      PassR: ['', [Validators.minLength(8), Validators.required]],
    });
  }

}
