FROM maven:3.5.2-ibmjava-8 as mvnpackage


FROM java:8
COPY /target/zipkin-app-1.0-SNAPSHOT.jar /zipkin-app-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT java -jar /zipkin-app-1.0-SNAPSHOT.jar
