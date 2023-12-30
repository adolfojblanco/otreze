import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Router } from '@angular/router';
import { HotToastService } from '@ngneat/hot-toast';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthInterceptorService implements HttpInterceptor {
  private authServices = inject(AuthService);
  private router = inject(Router);
  private toast = inject(HotToastService);
  constructor() {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token: string | null = this.authServices.getToken();
    let request = req;
    if (token) {
      request = req.clone({
        setHeaders: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });
    }
    return next.handle(request).pipe(
      catchError((err: HttpErrorResponse) => {
        if (err.status === 401) {
          return throwError(() => {
            localStorage.removeItem('token');
            this.router.navigate(['/auth/login']);
            this.toast.info('Debes volver a loguarte');
            this.toast.error(`${err.error.message}: ${err.error.error}`);
          });
        }

        if (err.status === 403) {
          this.toast.error('No estas autorizado');
          this.router.navigate(['/auth/login']);
          localStorage.clear();
          this.toast.error(`${err.error.message}: ${err.error.error}`);
        }

        if (err.status === 400) {
          this.toast.error(`${err.error.message}: ${err.error.error}`);
        }

        return throwError(() => {
          console.log(err);
          //this.toast.error(`${err.error.message}: ${err.error.error}`);
          //this.router.navigate(['/admin']);
        });
      })
    );
  }
}
