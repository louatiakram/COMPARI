// src/app/services/auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserRequestDto } from '../modules/authmodule/user-request-dto/user-request-dto.module';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8082/api/users';
  private loginUrl = 'http://localhost:8082/api/users/login'; // Update with your backend API URL

  

  constructor(private http: HttpClient) {}

  signUp(user: UserRequestDto): Observable<any> {
    return this.http.post(`${this.apiUrl}`, user);
  }
  login(credentials: { email: string; password: string; rememberMe: boolean }): Observable<any> {
    return this.http.post<any>(this.loginUrl, credentials);
  }
}
