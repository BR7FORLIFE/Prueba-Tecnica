import {
  Catch,
  type ArgumentsHost,
  type ExceptionFilter,
} from '@nestjs/common';
import type { Request, Response } from 'express';

interface ExceptionResult {
  path: string;
  message: string;
  time: string;
  statusCode: string | number;
}

@Catch()
export class AllexceptionFilter implements ExceptionFilter {
  catch(exception: any, host: ArgumentsHost) {
    const ctx = host.switchToHttp();

    const request = ctx.getRequest<Request>();
    const response = ctx.getResponse<Response>();

    const messageException =
      exception instanceof Error ? exception.message : 'INTERNAL SERVER ERROR!';

    const responseException: ExceptionResult = {
      path: request.path,
      message: messageException,
      time: new Date().toISOString(),
      statusCode: 406,
    };

    response.status(406).json(responseException);
  }
}
