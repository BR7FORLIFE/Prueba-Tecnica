import { Injectable } from '@nestjs/common';
import type { JwtService } from '@nestjs/jwt';
import type { RegisterDto } from './auth.dto.js';
import type { AuthRepository } from '../infra/auth.repository.js';
import {
  PasswordException,
  UserAlreadyExistsException,
  UserNotFoundExeption,
} from '../domain/user-exceptions.js';
import type { UserModel } from '../domain/user.model.js';
import * as bcrypt from 'bcrypt';

interface RegisterResponse {
  id: string;
  accessToken: string;
}

export interface PayloadJwt {
  id: string;
  email: string;
  rol: string;
}

@Injectable()
export class AuthService {
  constructor(
    private jwtService: JwtService,
    private authRepository: AuthRepository,
  ) {}

  async register(data: RegisterDto): Promise<RegisterResponse> {
    const existsUser = await this.authRepository.findByEmail(data.email);

    if (existsUser) throw new UserAlreadyExistsException();

    const newUser: UserModel = {
      id: crypto.randomUUID(),
      email: data.email,
      name: data.name,
      password: bcrypt.hashSync(data.password, 10),
      rol: 'USER',
    };

    const payload: PayloadJwt = {
      id: newUser.id,
      email: newUser.email,
      rol: newUser.rol,
    };

    const result = await this.authRepository.save(newUser);

    const accessToken = await this.jwtService.signAsync(payload);

    return {
      id: result.id,
      accessToken,
    };
  }

  async login(email: string, password: string) {
    const user = await this.authRepository.findByEmail(email);

    if (!user) throw new UserNotFoundExeption();

    const comparePassword = await bcrypt.compare(password, user.password);

    if (!comparePassword) throw new PasswordException();

    const payload: PayloadJwt = {
      id: user.id,
      email: user.email,
      rol: user.rol,
    };

    const accessToken = await this.jwtService.signAsync(payload);

    return { accessToken };
  }
}
