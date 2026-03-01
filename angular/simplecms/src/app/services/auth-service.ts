import { Injectable, signal } from '@angular/core';
import {AuthUser} from '../models/AuthUser';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  currentUser = signal<AuthUser | null>(null);

  constructor(private router: Router) {
    const stored = localStorage.getItem('authUser');
    if (stored) {
      this.currentUser.set(JSON.parse(stored));
    }
  }

  login(user: AuthUser) {
    this.currentUser.set(user);
    localStorage.setItem('authUser', JSON.stringify(user));
  }

  logout() {
    this.currentUser.set(null);
    localStorage.removeItem('authUser');
    this.router.navigate(['/']);
  }

  isLoggedIn() {
    return !!this.currentUser();
  }
}
