import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      rememberMe: [false]
    });
  }

  signIn() {
    if (this.loginForm.valid) {
      const { username, password } = this.loginForm.value;
      this.authService.signIn(username, password).subscribe(
        response => {
          const userId = response.userId; // Extract user ID from the response
          console.log('User logged in successfully!', response);
          alert(`Login successful! User ID: ${userId}`);
          this.router.navigate(['/']); // Redirect to home or desired route
        },
        error => {
          console.error('Error during login', error);
          alert('Login failed. Please try again.');
        }
      );
    }
  }
}