import { Global, Module } from '@nestjs/common';
import { PrismaDB } from './db.config.js';

@Global()
@Module({
  providers: [PrismaDB],
  exports: [PrismaDB],
})
export class PrismaModule {}
