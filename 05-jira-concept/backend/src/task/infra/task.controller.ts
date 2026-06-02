import {
  Body,
  Controller,
  Delete,
  Get,
  Patch,
  Post,
  UseGuards,
} from '@nestjs/common';
import { TaskService } from '../application/task.service.js';
import type { CreateTask } from '../application/task.dto.js';
import { JwtGuard } from '../../auth/application/guards/jwt.guard.js';
import { RoleGuard } from '../../auth/application/guards/role.guard.js';
import { Roles } from '../../core/decorators/decorators.js';

@UseGuards(JwtGuard, RoleGuard)
@Controller({
  version: '1',
  path: 'task',
})
export class TaskController {
  constructor(private taskService: TaskService) {}

  @Get()
  getTasks() {}

  @Roles('ADMIN')
  @Post()
  async createTask(@Body() dto: CreateTask) {
    const data = await this.taskService.createTask(dto);

    return { ...data };
  }

  @Patch()
  editTask() {}

  @Roles('ADMIN')
  @Delete()
  async deleteTask(@Body() taskId: string) {
    await this.deleteTask(taskId);
    return {
      id: taskId,
      message: 'task deleted succesfull!',
    };
  }
}
