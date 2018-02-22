#Installing jdk 8 and maven 3 with alpine as base image
#FROM maven:3-jdk-8-alpine
#FROM openjdk:8-jdk-alpine
# Custom Docker Image for Maven 3.0.5 and jdk8 
FROM siddhirajpantoji/maven-3.0.5-alpine

#Created directory for source code  
RUN mkdir codesource


# Verifying Maven Version 
RUN mvn -version

# Verifying JDK Version 
RUN java -version

# Copying code base into created directory 
COPY . ./codesource/
WORKDIR /codesource

# Installing Dependencies of Maven 
#RUN mvn install 

# Generating package with dependencies  
RUN mvn clean verify  package 

# Exposing port from which the APIs will be exposed 
EXPOSE 8082

# Specifies the startup command for running docker 
ENTRYPOINT ["java","-jar","./target/JAVAPOC-0.1.0.jar"]