import { Body, Controller, Post } from '@nestjs/common';
import type { LoginDto, RegisterDto } from '../application/auth.dto.js';
import type { AuthService } from '../application/auth.service.js';

@Controller({
  version: '1',
  path: 'auth',
})
export class AuthController {
  constructor(private authService: AuthService) {}

  @Post('register')
  async register(@Body() dto: RegisterDto) {
    const { id, accessToken } = await this.authService.register(dto);

    return {
      id,
      accessToken,
    };
  }

  @Post('login')
  async login(@Body() dto: LoginDto) {
    const { accessToken } = await this.authService.login(
      dto.email,
      dto.password,
    );

    return { accessToken };
  }
}
