import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [
    `
      .logo {
        width: 350px;
      }
      .card {
        padding-top: 50px;
      }
      .error {
        padding: 16px;
        width: 300px;
        color: white;
        background-color: red;
      }
    `,
  ],
})
export class LoginComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);

  public formLogin: FormGroup = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });

  login() {
    if (this.formLogin.invalid) {
      this.formLogin.markAllAsTouched();
      return;
    }

    this.authService.login(this.formLogin.value).subscribe((res: any) => {});
  }

  isValid(campo: string) {
    return (
      this.formLogin.controls[campo].errors &&
      this.formLogin.controls[campo].touched
    );
  }
}
