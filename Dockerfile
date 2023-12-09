
FROM node:16-alpine


WORKDIR /apps

ENV PORT 3000

EXPOSE 3000

COPY . .

RUN npm install

CMD ["node", "./bin/www"]
