# Use Java 21 base image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built jar into the container
COPY target/expense-manager-1.0-SNAPSHOT.jar app.jar

# Expose port 8081
EXPOSE 8081

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
