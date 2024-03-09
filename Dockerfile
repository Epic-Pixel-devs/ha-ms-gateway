FROM openjdk:17

COPY . app/

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dserver.port=8080 -jar target/ms-gateway-1.0.0.jar"]