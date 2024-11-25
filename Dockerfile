FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY target/demoWeatherBackend-1.0.0.jar demoWeatherBackend-1.0.0.jar
ENTRYPOINT ["java","-jar","/demoWeatherBackend-1.0.0.jar"]