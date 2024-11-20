FROM openjdk:17-jdk-slim

LABEL authors="miri"

WORKDIR /app

COPY target/backend.uni-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]


