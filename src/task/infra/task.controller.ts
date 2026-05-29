import { Controller, Delete, Get, Patch } from '@nestjs/common';

@Controller({
  version: '1',
  path: 'task',
})
export class TaskController {
  @Get()
  getTasks() {}

  @Patch()
  editTask() {}

  @Delete()
  deleteTask() {}
}
