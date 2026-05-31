import { Module } from '@nestjs/common';
import { AuthModule } from './auth/auth.module.js';
import { ProjectModule } from './projects/project.module.js';
import { TaskModule } from './task/task.module.js';
import { PrismaDB } from './config/db.config.js';

@Module({
  imports: [],
  controllers: [],
  providers: [PrismaDB, AuthModule, ProjectModule, TaskModule],
})
export class AppModule {}
