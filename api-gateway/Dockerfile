FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY build-context/api-gateway.xml ./pom.xml
COPY api-gateway/pom.xml ./api-gateway/
COPY shared/pom.xml ./shared/

RUN mvn -B dependency:go-offline -DskipTests

COPY api-gateway ./api-gateway
COPY shared ./shared

RUN mvn clean install -DskipTests

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
