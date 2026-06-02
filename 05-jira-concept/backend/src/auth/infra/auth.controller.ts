import { Body, Controller, Post, Res } from '@nestjs/common';
import type { LoginDto, RegisterDto } from '../application/auth.dto.js';
import { AuthService } from '../application/auth.service.js';
import type { Response } from 'express';

@Controller({
  version: '1',
  path: 'auth',
})
export class AuthController {
  constructor(private authService: AuthService) {}

  @Post('register')
  async register(@Body() dto: RegisterDto) {
    const { id } = await this.authService.register(dto);

    return {
      id,
      message: 'Register succesfull',
    };
  }

  @Post('login')
  async login(
    @Body() dto: LoginDto,
    @Res({ passthrough: true }) res: Response,
  ) {
    const { accessToken } = await this.authService.login(
      dto.email,
      dto.password,
    );

    res.cookie('accessToken', accessToken, {
      httpOnly: true,
      secure: false, // en local funciona pero en prod es true
      sameSite: 'lax',
      path: '/',
      maxAge: 900000,
    });

    return { message: 'login succesfull!' };
  }
}
