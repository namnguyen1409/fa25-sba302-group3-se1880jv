FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# copy file build trước cho cache tốt hơn
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

COPY src src

RUN ./mvnw -DskipTests clean package

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 9999

ENTRYPOINT ["java", "-jar", "app.jar"]
