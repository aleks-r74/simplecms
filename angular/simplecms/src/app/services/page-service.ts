import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Page } from '../models/Page';

@Injectable({ providedIn: 'root' })
export class PageService {

  private http = inject(HttpClient);
  private baseUrl = '/pages';

  getBySlug(slug: string): Observable<Page> {
    return this.http.get<Page>(`${this.baseUrl}/${slug}`);
  }

  getAll(): Observable<Page[]> {
    return this.http.get<Page[]>(this.baseUrl);
  }

  create(page: Page): Observable<Page> {
    return this.http.post<Page>(this.baseUrl, page);
  }

  update(id: number, page: Page): Observable<Page> {
    return this.http.put<Page>(`${this.baseUrl}/${id}`, page);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}