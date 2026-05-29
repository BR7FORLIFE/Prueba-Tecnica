import { Body, Controller, Delete, Get, Patch, Post } from '@nestjs/common';
import type { CreateProject } from '../application/project.dto.js';
import type { ProjectService } from '../application/project.service.js';

@Controller({
  version: '1',
  path: 'project',
})
export class ProjectController {
  constructor(private readonly service: ProjectService) {}

  @Get()
  getAllProjects() {
    return this.service.getAll();
  }

  @Post()
  async createProject(@Body() dto: CreateProject) {
    const { id, nameProject } = await this.service.createProject(
      dto.name,
      dto.description,
    );
    return { id, nameProject };
  }

  @Patch()
  modifyProject() {}

  @Delete()
  deleteProject() {}
}
