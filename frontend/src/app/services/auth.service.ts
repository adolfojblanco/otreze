import { Injectable, inject } from '@angular/core';
import { User } from '../models/user';
import { Observable, catchError, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { HotToastService } from '@ngneat/hot-toast';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly urlEndPoint: string = `${environment.apiUrl}/auth`;
  private http = inject(HttpClient);
  private router = inject(Router);
  private toast = inject(HotToastService);
  constructor() {}

  login(user: User): Observable<User> {
    return this.http.post<User>(`${this.urlEndPoint}/authenticate`, user).pipe(
      tap((resp: any) => {
        console.log(resp);
        localStorage.setItem('token', resp.access_token);
        this.router.navigate(['/admin']);
      }),
      catchError((err) => {
        this.toast.error(`Hubo un error al autenticar, credenciales invalidas`);
        throw 'error in source. Details: ' + err;
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
  getUserDetails(): User {
    const token: any = localStorage.getItem('token');
    let decoded: any = '';
    if (token) {
      decoded = jwtDecode(token);
    }
    return decoded;
  }
}
