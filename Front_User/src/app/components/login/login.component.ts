import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { AuthService } from '../../services/auth.service';
import { UserRequestDto } from 'src/app/modules/authmodule/user-request-dto/user-request-dto.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [ReactiveFormsModule, HttpClientModule, CommonModule],// Add CommonModule here,
  standalone: true,
  styleUrls: ['./login.component.scss'] // Add this if you have a CSS file
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
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
          // Assuming response contains userId
          alert(`Login successful! Your user ID is ${response.userId}`);
          // Optionally store user info or JWT token
          localStorage.setItem('userId', response.userId);
          this.router.navigate(['/']); // Redirect to home or any other page
        },
        error: (error) => {
          alert('Login failed. Please check your credentials.');
        }
      });
    }
  }
}
