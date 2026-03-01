import { Component } from '@angular/core';
import { FormGroup, Validators, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../services/auth-service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
})
export class Login {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.required]],
      password: ['', Validators.required],
    });
  }

  submit() {
    if (this.loginForm.valid) {
      this.auth.login({ token: 'abcd' });
      console.log('Login submitted', this.loginForm.value);
    } else {
      console.log('Form invalid');
      this.loginForm.markAllAsTouched();
    }
  }

}
