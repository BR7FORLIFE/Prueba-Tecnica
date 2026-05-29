import { Injectable } from '@nestjs/common';
import { PrismaDB } from '../../config/db.config.js';
import type { UserModel } from '../domain/user.model.js';

@Injectable()
export class AuthRepository {
  constructor(private db: PrismaDB) {}

  async findByEmail(email: string) {
    const user = this.db.user.findUnique({
      where: { email },
    });

    return user;
  }

  async save(data: UserModel) {
    const result = this.db.user.create({
      data,
    });

    return result;
  }
}
