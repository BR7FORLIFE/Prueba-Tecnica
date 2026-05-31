import { Injectable } from '@nestjs/common';
import { PrismaDB } from '../../config/db.config.js';

@Injectable()
export class ProjectRepository {
  constructor(private readonly db: PrismaDB) {}

  async save(
    name: string,
    description: string,
  ): Promise<{ id: string; name: string }> {
    const result = await this.db.projects.create({
      data: {
        name,
        description,
      },
    });

    return { id: result.id, name: result.name };
  }

  async getAll() {
    return await this.db.projects.findMany();
  }
}
