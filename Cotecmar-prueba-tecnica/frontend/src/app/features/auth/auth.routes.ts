import { Routes } from '@angular/router';
import { Login } from '@features/auth/page/login/login';

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
];
