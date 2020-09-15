FROM openjdk:8-jdk-alpine
COPY target/testWebApp-1.0-SNAPSHOT-jar-with-dependencies.jar ./target/Application.jar
COPY src/main/java/com/project2/resources* src/main/java/com/project2/resources/
ENTRYPOINT java -jar target/Application.jar
