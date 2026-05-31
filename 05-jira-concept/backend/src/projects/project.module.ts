import { Module } from '@nestjs/common';
import { ProjectController } from './infra/project.controller.js';
import { ProjectRepository } from './infra/project.repository.js';

@Module({
  controllers: [ProjectController],
  providers: [ProjectRepository],
})
export class ProjectModule {}
