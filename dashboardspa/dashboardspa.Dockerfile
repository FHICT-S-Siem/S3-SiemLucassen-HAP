# build environment
FROM node:13.12.0-alpine as build

ARG REACT_APP_AUTH0_DOMAIN
ARG REACT_APP_AUTH0_CLIENT_ID
ARG REACT_APP_NAME
ARG REACT_APP_DEV_API_URL
ARG REACT_APP_DEV_HOSTNAME

ENV REACT_APP_AUTH0_DOMAIN=$REACT_APP_AUTH0_DOMAIN
ENV REACT_APP_AUTH0_CLIENT_ID=$REACT_APP_AUTH0_CLIENT_ID
ENV REACT_APP_NAME=$REACT_APP_NAME
ENV REACT_APP_DEV_API_URL=$REACT_APP_DEV_API_URL
ENV REACT_APP_DEV_HOSTNAME=$REACT_APP_DEV_HOSTNAME

WORKDIR /app

COPY package.json .
COPY package-lock.json .

RUN npm ci
RUN npm install react-scripts@3.4.1 -g --silent

COPY . ./

RUN npm run build

# production environment
FROM nginx:stable-alpine

COPY --from=build /app/build /usr/share/nginx/html

COPY nginx.conf /temp/prod.conf
RUN envsubst /app < /temp/prod.conf > /etc/nginx/conf.d/default.conf

EXPOSE 5555

CMD ["nginx", "-g", "daemon off;"]
