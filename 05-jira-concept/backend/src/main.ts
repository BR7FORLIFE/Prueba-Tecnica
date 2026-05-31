import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module.js';
import { AllexceptionFilter } from './core/filters/exception-filter.js';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  app.useGlobalFilters(new AllexceptionFilter());

  await app.listen(process.env.PORT ?? 3000);
}
bootstrap();
