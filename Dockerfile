FROM openjdk:8
VOLUME /tmp
EXPOSE 8087
ADD /target/spring-boot-jwt-0.0.1-SNAPSHOT.jar spring-boot-jwt-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "spring-boot-jwt-0.0.1-SNAPSHOT.jar"]