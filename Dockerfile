FROM amazoncorretto:21-alpine
WORKDIR /app
COPY build/libs/userService-0.0.1-SNAPSHOT.jar /app/userService.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/userService.jar"]
