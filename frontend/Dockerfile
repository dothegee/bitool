# 📄 bitool_front/Dockerfile

# 1. Node.js를 이용해 build 먼저 실행
FROM node:20-alpine as build

WORKDIR /app
COPY . .

RUN npm install && npm run build

# 2. Nginx 이미지로 정적 파일 서빙
FROM nginx:stable-alpine

# 3. Nginx 기본 설정 파일 교체 (선택 사항)
COPY --from=build /app/build /usr/share/nginx/html

# 4. 포트 노출
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
