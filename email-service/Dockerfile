FROM openjdk:14

ADD build/libs/email-service-0.0.1-SNAPSHOT.jar email-service-0.0.1-SNAPSHOT.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "email-service-0.0.1-SNAPSHOT.jar"]

MAINTAINER Artur Jakubowski