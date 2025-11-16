FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw -DskipTests clean package

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

# ---- 👇 add this section ----
ENV TZ=Asia/Ho_Chi_Minh
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV JAVA_OPTS="-Duser.timezone=Asia/Ho_Chi_Minh"
# -----------------------------

ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 9999

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
