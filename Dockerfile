# Java 17 베이스 이미지
FROM openjdk:17-jdk-slim

# Secret 값을 전달받기 위한 ARG 정의
ARG OPENAI_API_KEY
ARG NOTION_TOKEN
ARG NOTION_DATABASE_ID
ARG WEBHOOK_SECRET

# ENV 설정 → Spring Boot에서 ${}로 주입되게 함
ENV OPENAI_API_KEY=${OPENAI_API_KEY}
ENV NOTION_TOKEN=${NOTION_TOKEN}
ENV NOTION_DATABASE_ID=${NOTION_DATABASE_ID}
ENV WEBHOOK_SECRET=${WEBHOOK_SECRET}

# 작업 디렉토리 생성
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY build/libs/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
