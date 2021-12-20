FROM openjdk:11-jdk-slim

MAINTAINER Zlatan "zlajox@gmail.com"

EXPOSE 8080

WORKDIR /usr/local/bin/

COPY ./presentation/target/presentation-0.0.1-SNAPSHOT.jar webapp.jar

CMD ["java", "-jar", "webapp.jar"]