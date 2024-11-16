import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  myForm: FormGroup;
  loginError: boolean = false;

  constructor(private router: Router, private formB: FormBuilder) {
    this.myForm = formB.group({
      EmailB: ['', [Validators.email, Validators.required]],
      Pass: ['', [Validators.minLength(8), Validators.required]]
    });
  }

  ngOnInit(): void {
    console.log('Form validity on init:', this.myForm.valid);
    this.myForm.statusChanges.subscribe(status => {
      console.log('Form status:', status); // This should log 'VALID' or 'INVALID'
    });
  }

  onSubmit() {
    console.log('Form Submitted');
    if (this.myForm.valid) {
      console.log('Form is valid:', this.myForm.value);
      // Implement your login logic here
    } else {
      console.log('Form is invalid');
    }
  }
}
