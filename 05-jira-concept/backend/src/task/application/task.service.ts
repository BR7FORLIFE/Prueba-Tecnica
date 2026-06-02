import { Injectable } from '@nestjs/common';
import { TaskRepository } from '../infra/task.repository.js';
import type { CreateTask } from './task.dto.js';

interface CreateTaskResponse {
  id: string;
  projectId: string;
  title: string;
  status: string;
  priority: string;
}

@Injectable()
export class TaskService {
  constructor(private repository: TaskRepository) {}

  async createTask(dto: CreateTask): Promise<CreateTaskResponse> {
    const { id, projectId, title, status, priority } =
      await this.repository.save(dto);

    return {
      id,
      projectId,
      title,
      status,
      priority,
    };
  }

  async deleteTask(taskId: string) {
    await this.deleteTask(taskId);
  }
}
