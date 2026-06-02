import * as dotenv from 'dotenv';
import fs from 'fs';

dotenv.config();

function obtainKeys(path: string) {
  const key = fs.readFileSync(path, 'utf-8');

  const parserKey = key
    .replace('-----BEGIN PRIVATE KEY-----', '')
    .replace('-----END PRIVATE KEY-----', '')
    .replaceAll('\\s', '');

  return parserKey;
}

export const PRIVATE_KEY = obtainKeys('src/config/keys/private.pem');
export const DATABASE_URL = process.env.DATABASE_URL;
