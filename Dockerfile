# Use the official Maven image with OpenJDK
FROM maven:3.8-openjdk-11

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy the pom.xml and source code to the working directory
COPY pom.xml .
COPY src ./src

# Expose port 9090
EXPOSE 9090

# Run the app using Maven and the jetty-maven-plugin
CMD ["mvn", "jetty:run"]
