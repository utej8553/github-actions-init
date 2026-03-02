
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/app.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","app.jar"]
