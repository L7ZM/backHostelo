import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/authService/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  myform2: FormGroup;
  
  constructor(
    private formBuilder: FormBuilder,
    private signUpService: AuthService,
    private router: Router
  ) {
    this.myform2 = formBuilder.group({
      Fname: ['', Validators.required],
      Sname: ['', Validators.required],
      DateN: ['', Validators.required],
      PhoneN: ['', [Validators.required]],
      EmailC: ['', [Validators.email, Validators.required]],
      PassR: ['', [Validators.minLength(8), Validators.required]],
    });
  }

  onSubmit(): void {
    if (this.myform2.valid) {
      const formData = this.myform2.value;

      const userDTO = {
        nom: formData.Fname, // Changed to match backend field names
        prenom: formData.Sname,
        dateNaissance: formData.DateN,
        telephone: formData.PhoneN,
        email: formData.EmailC,
      };

      const password = formData.PassR;

      this.signUpService.registerUser(userDTO, password).subscribe(
        (response) => {
          console.log('User registered successfully:', response);
          this.router.navigate(['']);
        },
        (error: any) => {
          console.error('Error during registration:', error);
          // You could also display error messages here
        }
      );
    }
  }
}
