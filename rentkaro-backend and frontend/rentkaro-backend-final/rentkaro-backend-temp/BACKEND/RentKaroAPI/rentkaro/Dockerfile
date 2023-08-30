# Use the official Maven image as a builder
FROM maven:3.8.4-openjdk-17-slim AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY ./pom.xml /app/pom.xml
RUN mvn dependency:go-offline -B

# Copy the source code
COPY ./src /app/src

# Build the application
RUN mvn package -DskipTests

# Use the official OpenJDK image as the base image
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled application from the builder stage
COPY --from=builder /app/target/rentkaro-*.jar /app/app.jar

# Expose the port that the Spring Boot application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "./app.jar"]
