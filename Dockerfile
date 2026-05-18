# Base Image
FROM ubuntu:24.04

# Avoid interactive prompts
ARG DEBIAN_FRONTEND=noninteractive

# Install Java, Maven, Git
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven git



# Set Working Directory
WORKDIR /app

COPY . .

# Build Application
RUN mvn clean package -DskipTests

# Expose Spring Boot Port
EXPOSE 8080

# Run Spring Boot Jar
CMD ["java", "-jar", "target/QuantityMeasurementApp-0.0.1-SNAPSHOT.jar"]
