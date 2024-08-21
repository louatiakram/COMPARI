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

  constructor(private http: HttpClient) {}

  signUp(user: UserRequestDto): Observable<any> {
    return this.http.post(`${this.apiUrl}`, user);
  }
  signIn(username: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { username, password });
  }
}
