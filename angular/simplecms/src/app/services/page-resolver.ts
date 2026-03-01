import { Injectable } from "@angular/core";
import { PageService } from "./page-service";
import { ActivatedRouteSnapshot, Router } from "@angular/router";
import { catchError, EMPTY } from "rxjs";

@Injectable({ providedIn: 'root' })
export class PageResolver {
  constructor(private pageService: PageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot) {
    const slug = route.paramMap.get('slug')!;

    return this.pageService.getBySlug(slug).pipe(
      catchError(() => {
        this.router.navigate(['/404']);
        return EMPTY;
      })
    );
  }
}