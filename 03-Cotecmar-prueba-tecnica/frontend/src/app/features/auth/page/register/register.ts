import { Component } from '@angular/core';
import {
  FormGroup,
  FormControl,
  NonNullableFormBuilder,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';

import { Auth } from '@features/auth/service/auth';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule],
  templateUrl: './register.html',
})
export class Register {
  message = '';
  form!: FormGroup<{
    username: FormControl<string>;
    password: FormControl<string>;
  }>;

  constructor(private fb: NonNullableFormBuilder, private AuthService: Auth) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      username: this.fb.control('', [Validators.required, Validators.minLength(3)]),
      password: this.fb.control('', [Validators.required, Validators.minLength(8)]),
    });
  }

  submit(): void {
    if (this.form.valid) {
      const { username, password } = this.form.getRawValue();
      this.AuthService.register(username, password).subscribe();
    } else {
      this.form.markAllAsTouched();
      this.message = 'Por favor corrige los errores';
    }
  }
}
