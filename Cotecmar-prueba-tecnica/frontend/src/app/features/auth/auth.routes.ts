import { Routes } from '@angular/router';
import { Login } from '@features/auth/page/login/login';
import { Register } from '@features/auth/page/register/register';

export const AUTH_ROUTES: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: Login,
  },
  {
    path: 'register',
    component: Register,
  },
];
