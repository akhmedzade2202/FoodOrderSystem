# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY build/libs/FoodOrderSystem-0.0.1-SNAPSHOT.jar /app/FoodOrderSystem.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/FoodOrderSystem.jar"]