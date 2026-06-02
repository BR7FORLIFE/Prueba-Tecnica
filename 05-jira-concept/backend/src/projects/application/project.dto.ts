import { IsNotEmpty, IsString } from 'class-validator';

export class CreateProject {
  @IsString()
  @IsNotEmpty()
  name: string;

  @IsString()
  @IsNotEmpty()
  description: string;
}

export class AssignmentTaskToProject {
  projectId: string;
  taskId: string;
}
