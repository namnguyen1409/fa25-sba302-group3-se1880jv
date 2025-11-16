# ===== STAGE 1: BUILD =====
FROM gradle:8.10.2-jdk21-alpine AS builder
WORKDIR /app

# Copy Gradle config
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle gradle
RUN gradle dependencies --no-daemon || return 0

# Copy source và build
COPY src src
RUN gradle clean bootJar -x test --no-daemon && ls build/libs

# ===== STAGE 2: RUNTIME =====
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy jar ra app.jar
COPY --from=builder /app/build/libs/*.jar /app/app.jar

ENV SPRING_PROFILES_ACTIVE=docker
ENV TZ=Asia/Ho_Chi_Minh

EXPOSE 9888
ENTRYPOINT ["java", "-jar", "app.jar"]
