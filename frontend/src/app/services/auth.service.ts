import { Injectable, inject } from '@angular/core';
import { User } from '../models/user';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly urlEndPoint: string = `${environment.apiUrl}/auth`;
  private http = inject(HttpClient);
  private router = inject(Router);
  constructor() {}

  login(user: User): Observable<User> {
    return this.http.post<User>(`${this.urlEndPoint}/authenticate`, user).pipe(
      tap((resp: any) => {
        localStorage.setItem('token', resp.access_token);
        this.router.navigate(['/admin']);
      })
    );
  }

  /** Load token from localstorage */
  getToken() {
    const token: any = localStorage.getItem('token');
    if (token) {
      return token;
    }
    localStorage.clear();
    return null;
  }

  /** Load userdata from token */
  getUserDetails() {
    const user = localStorage.getItem('token');
    console.log(user);
  }
}
