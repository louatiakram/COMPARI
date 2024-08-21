// src/app/components/signup/signup.component.ts
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { AuthService } from '../../services/auth.service';
import { UserRequestDto } from 'src/app/modules/authmodule/user-request-dto/user-request-dto.module';
import { Router } from '@angular/router';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
  standalone: true,
  imports: [ReactiveFormsModule, HttpClientModule, CommonModule] // Add CommonModule here
})
export class SignupComponent {
  signupForm: FormGroup;

  constructor(
    private fb: FormBuilder, 
    private authService: AuthService,
    private router: Router // Add this line
  ) {
    this.signupForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){6,12}$/)]],
      rePassword: ['', [Validators.required]]
    }, { validator: this.passwordMatchValidator });
  }

  signUp() {
    if (this.signupForm.valid) {
      const user: UserRequestDto = this.signupForm.value;
      this.authService.signUp(user).subscribe(
        response => {
          alert('User registered successfully!');
          this.router.navigate(['/login']); // Redirect to the login page
        },
        error => {
          console.error('Error during registration', error);
          // Handle error
        }
      );
    }
  }
  

  private passwordMatchValidator(form: FormGroup) {
    return form.get('password')?.value === form.get('rePassword')?.value ? null : { 'mismatch': true };
  }
}
