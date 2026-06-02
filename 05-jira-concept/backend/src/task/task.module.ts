import { Module } from '@nestjs/common';
import { TaskController } from './infra/task.controller.js';
import { PrismaModule } from '../config/prisma.module.js';
import { TaskService } from './application/task.service.js';
import { TaskRepository } from './infra/task.repository.js';

@Module({
  imports: [PrismaModule],
  controllers: [TaskController],
  providers: [TaskService, TaskRepository],
})
export class TaskModule {}
