FROM maven:3.8.5-openjdk17 as build

WORKDIR /app

COPY . .

RUN mvn clean install

FROM openjdk:17 as java17

COPY --from=build ./app/target/*.jar ./ms-gateway.jar

EXPOSE 8081
# -Dspring.profiles.active=production
# -Dserver.port=8081

ENTRYPOINT java -jar ms-gateway.jar
