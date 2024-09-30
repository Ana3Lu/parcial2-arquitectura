
FROM openjdk:17-jdk-slim as build
LABEL authors="jespi"
WORKDIR /parcial2-arquitectura-master
COPY build.gradle gradle.properties gradlew ./
COPY gradle gradle
RUN ./gradlew build --no-daemon
COPY src ./src
RUN ./gradlew bootJar --no-daemon
FROM openjdk:17-jdk-slim
WORKDIR /parcial2-arquitectura-master
COPY --from=build /parcial2-arquitectura-master/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
