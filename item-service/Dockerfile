FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY build-context/item-service.xml ./pom.xml
COPY item-service/pom.xml ./item-service/
COPY shared/pom.xml ./shared/

RUN mvn -B dependency:go-offline -DskipTests

COPY item-service ./item-service
COPY shared ./shared

RUN mvn clean install -DskipTests

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/item-service/target/item-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
