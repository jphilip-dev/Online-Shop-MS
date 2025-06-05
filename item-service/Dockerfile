FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY . .

RUN mvn clean install -pl item-service -am -DskipTests

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/item-service/target/item-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
