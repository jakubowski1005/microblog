FROM openjdk:14

ADD build/libs/service-registry-0.0.1-SNAPSHOT.jar service-registry-0.0.1-SNAPSHOT.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "service-registry-0.0.1-SNAPSHOT.jar"]

MAINTAINER Artur Jakubowski