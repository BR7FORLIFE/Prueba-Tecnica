import { Injectable } from '@nestjs/common';
import { PrismaDB } from '../../config/db.config.js';
import { TaskModel } from '../domain/task.model.js';
import { CreateTask } from '../application/task.dto.js';

@Injectable()
export class TaskRepository {
  constructor(private db: PrismaDB) {}

  async save(task: CreateTask): Promise<TaskModel> {
    const result = await this.db.task.create({
      data: {
        projectId: task.projectId,
        title: task.title,
        description: task.description,
        status: task.status,
        priority: task.priority,
        assignmentTo: task.assignmentToId,
      },
    });

    return {
      ...result,
    };
  }

  async delete(taskId: string) {
    await this.db.task.delete({
      where: {
        id: taskId,
      },
    });
  }
}
