FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/patient-service-0.0.1-SNAPSHOT.jar patient-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "patient-service.jar"]
