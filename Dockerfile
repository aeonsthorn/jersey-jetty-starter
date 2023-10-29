# Start with an official Maven image to build the project
FROM maven:3.8-jdk-8 as build

# Set the working directory in Docker
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml ./
COPY src ./src/

# Package the application. This will create a war file in the target directory.
RUN mvn clean package

# Use the official Jetty image as the base image
FROM jetty:9.4-jre8-alpine

# Set the working directory in Docker to /app
WORKDIR /app

# Create a new directory adjacent to /app for Jetty
WORKDIR /jetty

# Copy the war file from the build stage to the new Jetty webapps directory
COPY --from=build /app/target/*.war ./webapps/ROOT.war

# Expose port 9090 for the application
EXPOSE 9090

# Run Jetty from the /jetty directory
CMD ["java", "-jar", "/usr/local/jetty/start.jar"]
