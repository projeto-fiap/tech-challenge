# Use a base image with JDK 11 for consistency with your requirement
FROM openjdk:17-jdk-slim

# Install necessary tools for development
RUN apt-get update && apt-get install -y \
    maven \
    git \
    curl \
    vim \
    && rm -rf /var/lib/apt/lists/*

# Set environment variables for development
ENV MAVEN_OPTS="-Xms256m -Xmx512m"

# Define the working directory
WORKDIR /app

# Copy the entire project into the container (assuming your source code is in the current directory)
COPY . .

# Run Maven to download dependencies
RUN mvn dependency:resolve

# Expose the port your application will run on
EXPOSE 8080

# Command to run the application in development mode (e.g., using Spring Boot dev tools)
CMD ["mvn", "spring-boot:run"]
