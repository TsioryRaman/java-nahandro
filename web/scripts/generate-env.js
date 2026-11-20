/**
 * Generates src/environments/environment.ts from web/.env.
 * Run automatically by `npm start`, `npm run build` and `npm run watch`.
 */
const fs = require('fs');
const path = require('path');

const projectRoot = path.resolve(__dirname, '..');
const envFile = path.join(projectRoot, '.env');
const outputFile = path.join(projectRoot, 'src', 'environments', 'environment.ts');

const DEFAULT_API_BASE_URL = 'http://localhost:8080';

function parseEnv(contents) {
  return contents.split(/\r?\n/).reduce((env, line) => {
    const trimmed = line.trim();

    if (!trimmed || trimmed.startsWith('#')) {
      return env;
    }

    const match = trimmed.match(/^([\w.-]+)\s*=\s*(.*)$/);

    if (!match) {
      return env;
    }

    let value = match[2].trim();

    if (
      (value.startsWith('"') && value.endsWith('"')) ||
      (value.startsWith("'") && value.endsWith("'"))
    ) {
      value = value.slice(1, -1);
    }

    env[match[1]] = value;
    return env;
  }, {});
}

const env = fs.existsSync(envFile)
  ? parseEnv(fs.readFileSync(envFile, 'utf8'))
  : {};

const apiBaseUrl = (env.API_BASE_URL || DEFAULT_API_BASE_URL).replace(/\/+$/, '');

const output = `// This file is generated from web/.env by scripts/generate-env.js.
// Do not edit it by hand; update .env and run \`npm run config\` instead.
export const environment = {
  apiBaseUrl: '${apiBaseUrl.replace(/\\/g, '\\\\').replace(/'/g, "\\'")}'
};
`;

fs.mkdirSync(path.dirname(outputFile), { recursive: true });
fs.writeFileSync(outputFile, output);

console.log(`[config] API_BASE_URL=${apiBaseUrl}`);
