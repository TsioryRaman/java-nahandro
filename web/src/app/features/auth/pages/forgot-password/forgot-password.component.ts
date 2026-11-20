import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  readonly form = this.formBuilder.nonNullable.group({
    email: ['', [Validators.required, Validators.email]]
  });

  submitted = false;
  submittedEmail = '';

  constructor(private readonly formBuilder: FormBuilder) { }

  get email() {
    return this.form.controls.email;
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.submittedEmail = this.form.getRawValue().email;

    // TODO: call the password reset endpoint once it exists on the API.
    // The API currently exposes /auth/login and /auth/register only.
    this.submitted = true;
  }
}
