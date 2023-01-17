FROM amazoncorretto:11

WORKDIR /app

COPY ./target/discovery-server-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","app.jar"]