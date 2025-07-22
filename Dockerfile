# Java 17 베이스 이미지
FROM openjdk:17-jdk-slim

# 작업 디렉토리 생성
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY build/libs/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]