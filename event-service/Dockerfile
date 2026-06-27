
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /build

COPY pom.xml .
COPY event-service event-service
COPY notification-service notification-service
COPY api-gateway api-gateway

RUN mvn -q -pl event-service -am clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /build/event-service/target/event-service-*.jar app.jar