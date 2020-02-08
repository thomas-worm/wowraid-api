FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} wowraid-api.jar
ENTRYPOINT ["java","-jar","/wowraid-api.jar"]
