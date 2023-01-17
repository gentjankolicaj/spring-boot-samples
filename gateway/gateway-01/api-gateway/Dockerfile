FROM amazoncorretto:11

WORKDIR /app

COPY ./target/api-gateway-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]