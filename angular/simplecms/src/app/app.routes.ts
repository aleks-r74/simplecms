import { Routes } from '@angular/router';
import { Login } from './login/login';
import { PageEdit } from './page-edit/page-edit';
import { PageView } from './page-view/page-view';
import { PageResolver } from './services/page-resolver';
import { PageNotFound } from './page-not-found/page-not-found';

export const routes: Routes = [
    {path: "login", component: Login, title: "This is a private web site, no registration is available"},
    {path: "new", component: PageEdit, title: "New page"},
    { path: '', redirectTo: 'about', pathMatch: 'full' },
    { path: '404', component: PageNotFound, title: 'Page not found' },
    { path: ':slug', component: PageView, resolve: { page: PageResolver } },

];
