
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  registerUser(userDTO: any, password: string): Observable<any> {
    const url = `${this.baseUrl}/register?password=${password}`;  // Sending password as a query parameter

    return this.http
      .post(url, userDTO)  // Send user data in the body, password in the URL
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An error occurred';

    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = error.error.message;
    } else {
      // Server-side error
      errorMessage =
        error.error?.message || `Server returned code ${error.status}`;
    }

    return throwError(() => new Error(errorMessage));
  }
}
