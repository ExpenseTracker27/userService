FROM amazoncorretto:21
WORKDIR /app
COPY build/libs/userService-0.0.1-SNAPSHOT-plain.jar /app/userService.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/userService.jar"]
