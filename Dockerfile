#Installing jdk 8 and maven 3 with alpine as base image
FROM maven:3-jdk-8-alpine

#Created directory for source code  
RUN mkdir codesource

# Copying code base into created directory 
COPY . ./codesource/
WORKDIR /codesource

# Verifying Maven Version 
RUN mvn -version

# Verifying JDK Version 
RUN java -version

# Installing Dependencies of Maven 
#RUN mvn install 

# Generating package with dependencies  
RUN mvn clean verify  package 

# Exposing port from which the APIs will be exposed 
EXPOSE 8080

# Specifies the startup command for running docker 
ENTRYPOINT ["java","-jar","./target/JAVAPOC-0.1.0.jar"]