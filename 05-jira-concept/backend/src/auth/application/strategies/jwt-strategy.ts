import { PassportStrategy } from '@nestjs/passport';
import { Strategy, ExtractJwt } from 'passport-jwt';
import { PRIVATE_KEY } from '../../../../constant.js';

import type { PayloadJwt } from '../auth.service.js';
import { Injectable } from '@nestjs/common';
import type { Request } from 'express';

const cookieExtractor = (req: Request): string | null => {
  if (!req?.cookies) {
    return null;
  }

  return req.cookies['accessToken'] as string;
};

@Injectable()
export class JwtStrategyImp extends PassportStrategy(Strategy) {
  constructor() {
    super({
      jwtFromRequest: ExtractJwt.fromExtractors([cookieExtractor]),
      ignoreExpiration: false,
      secretOrKey: PRIVATE_KEY,
    });
  }

  validate(payload: PayloadJwt): PayloadJwt {
    return {
      id: payload.id,
      email: payload.email,
      rol: payload.rol,
    };
  }
}
