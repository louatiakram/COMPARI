import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [ReactiveFormsModule, HttpClientModule, CommonModule], // Add CommonModule here
  styleUrls: ['./login.component.scss'] // Add this if you have a CSS file
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      rememberMe: [false]
    });
  }

  signIn() {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
        next: (response: any) => {
          const userName = response.name; // Adjust based on your API response
          const userId = response.userId; // Adjust based on your API response
          const token = response.token; // Adjust based on your API response
          
          alert(`Login successful! Welcome ${userName}. Your user ID is ${userId}`);
          
          // Store the token or user info if needed
          localStorage.setItem('userToken', token);
          this.router.navigate(['/']); // Redirect to home or any other page
        },
        error: (error) => {
          alert('Login failed. Please check your credentials.');
        }
      });
    }
  }
}
