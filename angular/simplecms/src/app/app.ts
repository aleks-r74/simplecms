import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { PageEventService } from './services/page-event-service';
import { AuthService } from './services/auth-service';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterLink, AsyncPipe, RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  mobileSidebarOpen = false;

  constructor(private peService: PageEventService, public auth: AuthService) {
    this.peService.save$.subscribe(page => {
      this.createPage(page);
    });
  }

  toggleMobileSidebar() {
    this.mobileSidebarOpen = !this.mobileSidebarOpen;
  }

  closeMobileSidebar() {
    this.mobileSidebarOpen = false;
  }

  createPage(page: any) {
  console.log('New page data:', page);
  // call backend API to save
}
}
