import { Component } from '@angular/core';
import { RouterModule, RouterLink, Router } from '@angular/router';

@Component({
  selector: 'app-page-not-found',
  imports: [RouterModule, RouterLink],
  templateUrl: './page-not-found.html'
})
export class PageNotFound {
  constructor(private router: Router) {}

  navigateHome() {
    this.router.navigate(['']);
    console.log('Navigating to home');
  }
}
