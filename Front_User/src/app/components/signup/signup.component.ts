import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
  standalone: true,
  imports: [ReactiveFormsModule, HttpClientModule, CommonModule] // Ensure CommonModule is included
})
export class SignupComponent {
  signupForm: FormGroup;

  constructor(
    private fb: FormBuilder, 
    private authService: AuthService,
    private router: Router
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
      const user = this.signupForm.value; // UserRequestDto is already aligned with the form
      this.authService.signUp(user).subscribe(
        response => {
          alert('User registered successfully!');
          this.router.navigate(['/login']); // Redirect to the login page
        },
        error => {
          console.error('Error during registration', error);
          // Handle error, e.g., display a message to the user
        }
      );
    }
  }

  private passwordMatchValidator(form: FormGroup) {
    return form.get('password')?.value === form.get('rePassword')?.value ? null : { 'mismatch': true };
  }
}
