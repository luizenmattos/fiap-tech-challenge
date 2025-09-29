# Stage 1: build
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/fiap-tech-challenge-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
