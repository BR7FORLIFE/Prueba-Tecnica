import { Module } from '@nestjs/common';
import { AuthController } from './infra/auth.controller.js';
import { AuthService } from './application/auth.service.js';
import { AuthRepository } from './infra/auth.repository.js';
import { JwtStrategyImp } from './application/strategies/jwt-strategy.js';
import { JwtModule } from '@nestjs/jwt';

import { PRIVATE_KEY } from '../../constant.js';

@Module({
  imports: [
    JwtModule.register({
      secret: PRIVATE_KEY,
      signOptions: {
        expiresIn: '15m',
      },
    }),
  ],
  controllers: [AuthController],
  providers: [AuthService, AuthRepository, JwtStrategyImp],
})
export class AuthModule {}
