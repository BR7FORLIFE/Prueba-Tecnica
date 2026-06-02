import { Priority, Status } from '@prisma/client';

export class TaskModel {
  id: string;
  projectId: string;
  title: string;
  description: string;
  status: Status;
  priority: Priority;
  assignmentTo: string;
}
