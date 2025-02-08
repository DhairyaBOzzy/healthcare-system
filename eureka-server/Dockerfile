# Use a lightweight Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Create a directory inside the container for the app
WORKDIR /app

# Copy the jar file produced by "mvn clean package"
# For example: eureka-server-0.0.1-SNAPSHOT.jar
COPY target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar

# Expose port 8761 for Eureka
EXPOSE 8761

# Command to run when container starts
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]
