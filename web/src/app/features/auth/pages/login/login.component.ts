import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { finalize } from 'rxjs';

import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  readonly form = this.formBuilder.nonNullable.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required]]
  });

  isSubmitting = false;
  showPassword = false;
  errorMessageKey: string | null = null;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly authService: AuthService,
    private readonly router: Router
  ) { }

  get email() {
    return this.form.controls.email;
  }

  get password() {
    return this.form.controls.password;
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting = true;
    this.errorMessageKey = null;

    this.authService
      .login(this.form.getRawValue())
      .pipe(finalize(() => (this.isSubmitting = false)))
      .subscribe({
        next: () => this.router.navigate(['/dashboard']),
        error: (error: HttpErrorResponse) => (this.errorMessageKey = this.toErrorKey(error))
      });
  }

  private toErrorKey(error: HttpErrorResponse): string {
    if (error.status === 0) {
      return 'common.unreachable';
    }

    if (error.status === 401 || error.status === 403) {
      return 'login.invalidCredentials';
    }

    return 'common.genericError';
  }
}
