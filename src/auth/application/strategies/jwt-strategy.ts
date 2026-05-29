import { PassportStrategy } from '@nestjs/passport';
import { Strategy, ExtractJwt } from 'passport-jwt';
import { PRIVATE_KEY } from '../../../../constant.js';

import type { PayloadJwt } from '../auth.service.js';
import { Injectable } from '@nestjs/common';

@Injectable()
export class JwtStrategyImp extends PassportStrategy(Strategy) {
  constructor() {
    super({
      jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
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
