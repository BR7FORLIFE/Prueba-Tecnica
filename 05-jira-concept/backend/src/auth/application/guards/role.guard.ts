import {
  Injectable,
  type CanActivate,
  type ExecutionContext,
} from '@nestjs/common';
import { Reflector } from '@nestjs/core';
import { ROLE_KEY } from '../../../core/decorators/decorators.js';
import type { AuthRequest } from '../../../core/types/auth-request.js';
import { NotAllowedUnathorize } from '../../domain/user-exceptions.js';

@Injectable()
export class RoleGuard implements CanActivate {
  constructor(private reflector: Reflector) {}

  canActivate(context: ExecutionContext): boolean {
    const requiredRole = this.reflector.getAllAndOverride<string[]>(ROLE_KEY, [
      context.getHandler(),
      context.getClass(),
    ]);

    if (!requiredRole) {
      return true;
    }

    const request: AuthRequest = context.switchToHttp().getRequest();

    const user = request.user;

    if (!requiredRole.includes(user.rol)) {
      throw new NotAllowedUnathorize();
    }

    return true;
  }
}
