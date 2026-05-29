import { Injectable } from '@nestjs/common';
import type { ProjectRepository } from '../infra/project.repository.js';

@Injectable()
export class ProjectService {
  constructor(private repository: ProjectRepository) {}

  async createProject(name: string, description: string) {
    const { id, name: nameProject } = await this.repository.save(
      name,
      description,
    );

    return { id, nameProject };
  }

  async getAll() {
    return await this.repository.getAll();
  }
}
