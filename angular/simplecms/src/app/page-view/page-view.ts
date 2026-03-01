import { Component, inject, OnInit } from '@angular/core';
import { DomSanitizer, Meta, SafeHtml, Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Page } from '../models/Page';
import { DatePipe } from '@angular/common';
import { PageService } from '../services/page-service';

@Component({
  selector: 'app-page-view',
  imports: [DatePipe],
  templateUrl: './page-view.html',
})
export class PageView implements OnInit {
  private route = inject(ActivatedRoute);
  private pageService = inject(PageService);
  private sanitizer = inject(DomSanitizer);
  private titleService = inject(Title);
  private metaService = inject(Meta);

  safeHtml?: SafeHtml;
  page!: Page;
  
  ngOnInit() {
    this.route.data.subscribe((data) => {
      this.page = data['page'];
      this.safeHtml = this.sanitizer.bypassSecurityTrustHtml(this.page.content);

      this.titleService.setTitle(this.page.metaTitle || this.page.title);

      this.metaService.updateTag({
        name: 'description',
        content: this.page.metaDescription || '',
      });
    });
  }
}
