import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { RegisterUser, LoginUser, type Rol } from '@features/auth/models/user.model';
import type { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  authApiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  register(username: string, password: string, rol: Rol): Observable<RegisterUser> {
    return this.http.post<RegisterUser>(
      `${this.authApiUrl}/register`,
      { username, password, rol },
      { withCredentials: true }
    );
  }
  login(username: string, password: string): Observable<LoginUser> {
    return this.http.post<LoginUser>(
      `${this.authApiUrl}/login`,
      { username, password },
      { withCredentials: true }
    );
  }
  logout(): void {}
}
