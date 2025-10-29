# Start with a base image with 17 Java runtime
FROM openjdk:17-jdk-slim

MAINTAINER Ojas

# This jar is present in the target folder and I want you to copy the same jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# This will tell docker what command has to be executed , we have kept , in command because there is space between the commands
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]