export type Rol = 'ADMIN' | 'USER';

export interface LoginUser {
  username: string;
  password: string;
  rol: Rol;
}

export interface RegisterUser {
  username: string;
  password: string;
}
