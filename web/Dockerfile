FROM node:18.16.0-alpine

WORKDIR /app

COPY package.json .

RUN npm install

CMD ["npm", "run", "serve"]