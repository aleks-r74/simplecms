import { Routes } from '@angular/router';
import { Login } from './login/login';
import { PageEdit } from './page-edit/page-edit';

export const routes: Routes = [
    {path: "login", component: Login, title: "This is a private web site, no registration is available"},
    {path: "new", component: PageEdit, title: "New page"}
];
