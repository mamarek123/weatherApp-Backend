FROM openjdk:17-jdk-alpine
MAINTAINER baeldung.com
COPY target/demoWeatherBackend-1.0.0.jar demoWeatherBackend-1.0.0.jar
ENTRYPOINT ["java","-jar","/demoWeatherBackend-1.0.0.jar"]