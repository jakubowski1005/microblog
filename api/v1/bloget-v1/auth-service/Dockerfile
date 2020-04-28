FROM openjdk:14

ADD build/libs/auth-service-0.0.1-SNAPSHOT.jar auth-service-0.0.1-SNAPSHOT.jar

EXPOSE 8031

ENTRYPOINT ["java", "-jar", "auth-service-0.0.1-SNAPSHOT.jar"]