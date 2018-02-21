#Installing jdk 8 and maven 3 with alpine as base image
#FROM maven:3-jdk-8-alpine
FROM openjdk:8-jdk-alpine
# Installing maven 3.0.5 for Http Security issues 
RUN apk add --no-cache curl tar bash

ARG MAVEN_VERSION=3.0.5
ARG USER_HOME_DIR="/root"
ARG SHA=707b1f6e390a65bde4af4cdaf2a24d45fc19a6ded00fff02e91626e3e42ceaff
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha256sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"


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
EXPOSE 8080

# Specifies the startup command for running docker 
ENTRYPOINT ["java","-jar","./target/JAVAPOC-0.1.0.jar"]