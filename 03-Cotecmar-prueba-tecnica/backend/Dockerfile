FROM openjdk:27-ea-slim-trixie

WORKDIR /app

COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

RUN mkdir -p /app/data

VOLUME /app/data

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
