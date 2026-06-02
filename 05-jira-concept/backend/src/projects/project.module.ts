import { Module } from '@nestjs/common';
import { ProjectController } from './infra/project.controller.js';
import { ProjectRepository } from './infra/project.repository.js';
import { PrismaModule } from '../config/prisma.module.js';
import { ProjectService } from './application/project.service.js';

@Module({
  imports: [PrismaModule],
  controllers: [ProjectController],
  providers: [ProjectRepository, ProjectService],
})
export class ProjectModule {}
