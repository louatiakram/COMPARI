import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { UserRequestDto } from '../modules/authmodule/user-request-dto/user-request-dto.module';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8082/api/v1/auth/register';
  private loginUrl = 'http://localhost:8082/api/v1/auth/authenticate';

  constructor(private http: HttpClient) {}

  signUp(user: UserRequestDto): Observable<any> {
    return this.http.post<any>(this.apiUrl, user).pipe(
      catchError(this.handleError)
    );
  }

  login(credentials: { email: string; password: string; rememberMe: boolean }): Observable<any> {
    return this.http.post<any>(this.loginUrl, credentials).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    console.error('An error occurred:', error.message);
    return throwError(() => new Error('Something went wrong; please try again later.'));
  }
}
