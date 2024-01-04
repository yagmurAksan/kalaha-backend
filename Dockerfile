FROM bellsoft/liberica-openjdk-debian:17.0.6-10 as builder
COPY . app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
