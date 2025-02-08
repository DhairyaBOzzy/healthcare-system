FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/doctor-service-0.0.1-SNAPSHOT.jar doctor-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "doctor-service.jar"]
