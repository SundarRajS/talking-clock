#Start with a base image containing Java runtime
FROM openjdk:11-slim as build

#Information around who maintains the image
LABEL maintainer="sundar.com"

# Add the application's jar to the container
COPY target/time-0.0.1-SNAPSHOT.jar time-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/time-0.0.1-SNAPSHOT.jar"]