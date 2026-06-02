import { Module } from '@nestjs/common';
import { AuthModule } from './auth/auth.module.js';
import { ProjectModule } from './projects/project.module.js';
import { TaskModule } from './task/task.module.js';
import { PrismaModule } from './config/prisma.module.js';

@Module({
  imports: [AuthModule, ProjectModule, TaskModule, PrismaModule],
  controllers: [],
  providers: [],
})
export class AppModule {}
