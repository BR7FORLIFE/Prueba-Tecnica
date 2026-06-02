import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module.js';
import { AllexceptionFilter } from './core/filters/exception-filter.js';
import CookieParser from 'cookie-parser';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  app.useGlobalFilters(new AllexceptionFilter());
  app.use(CookieParser());

  app.enableCors({
    origin: 'http://localhost:5173',
    credentials: true,
  });

  await app.listen(process.env.PORT ?? 3000);
}
bootstrap();
