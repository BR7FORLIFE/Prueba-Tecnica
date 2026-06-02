import {
  Body,
  Controller,
  Delete,
  Get,
  Patch,
  Post,
  UseGuards,
} from '@nestjs/common';
import type { CreateProject } from '../application/project.dto.js';
import { ProjectService } from '../application/project.service.js';
import { JwtGuard } from '../../auth/application/guards/jwt.guard.js';
import { RoleGuard } from '../../auth/application/guards/role.guard.js';
import { Roles } from '../../core/decorators/decorators.js';

@UseGuards(JwtGuard, RoleGuard)
@Roles('ADMIN')
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
