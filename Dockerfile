# Use a base image with Maven to build the application
FROM maven:3.8.2-openjdk-17 AS build

# Copy the Maven project descriptor
COPY pom.xml .

# Copy the rest of the source code
COPY src ./src

# Build the application
RUN mvn clean package

# Use a base image with OpenJDK to run the application
FROM openjdk:17-jdk-slim

#RUN groupadd -r app && useradd -r -g app app

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the Maven build stage to the current location in the container
COPY target/*.jar /app/runner.jar/

EXPOSE 8080

# Expose the debug port
EXPOSE 5005

#USER app

# Define the command to run the application when the container starts
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","-jar", "/app/runner.jar"]




