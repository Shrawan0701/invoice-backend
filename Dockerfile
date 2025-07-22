# Stage 1: Build the application
FROM openjdk:17-jdk-slim as builder


WORKDIR /app


COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .


RUN ./mvnw dependency:go-offline

# Copy the rest of the application source code
COPY src ./src

# Build the Spring Boot application
RUN ./mvnw clean install -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-jre-slim

# Set the working directory
WORKDIR /app


COPY --from=builder /app/target/*.jar invoicegeneratorapi-0.0.1-SNAPSHOT.jar


EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "invoicegeneratorapi-0.0.1-SNAPSHOT.jar"]