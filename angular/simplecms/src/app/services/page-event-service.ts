import { Injectable, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { Page } from '../models/Page';

@Injectable({
  providedIn: 'root',
})
export class PageEventService implements OnDestroy{
  ngOnDestroy(): void {
    this.saveSubject.unsubscribe();
  }
    private saveSubject = new Subject<Page>();
  save$ = this.saveSubject.asObservable();

  emitSave(page: Page) {
    this.saveSubject.next(page);
  }
}
