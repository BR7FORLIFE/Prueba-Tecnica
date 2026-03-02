import { Component, OnInit } from '@angular/core';
import {
  ReactiveFormsModule,
  NonNullableFormBuilder,
  Validators,
  FormGroup,
  type FormControl,
} from '@angular/forms';

import { Auth } from '@features/auth/service/auth';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
})
export class Login implements OnInit {
  message = '';
  form!: FormGroup<{
    username: FormControl<string>;
    password: FormControl<string>;
  }>;

  constructor(private fb: NonNullableFormBuilder, private authService: Auth) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      username: this.fb.control('', { validators: [Validators.required] }),
      password: this.fb.control('', { validators: [Validators.required] }),
    });
  }

  submit(): void {
    if (this.form.valid) {
      const { username, password } = this.form.getRawValue();
      this.authService.login(username, password).subscribe({
        next: () => (this.message = 'Authentication Succesfull'),
        error: () => (this.message = ' Authentication Failed'),
      });
    } else {
      this.message = 'Fill all fields';
      this.form.markAsTouched();
    }
  }
}
