# Use the official OpenJDK 11 image
FROM openjdk:11-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application jar to the container
COPY target/bookstore-1.0.0.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
