import { Module } from '@nestjs/common';
import { TaskController } from './infra/task.controller.js';

@Module({
  controllers: [TaskController],
  providers: [],
})
export class TaskModule {}
