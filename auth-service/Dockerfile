FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY build-context/auth-service.xml ./pom.xml
COPY auth-service/pom.xml ./auth-service/
COPY shared/pom.xml ./shared/

RUN mvn -B dependency:go-offline -DskipTests

COPY auth-service ./auth-service
COPY shared ./shared

RUN mvn clean install -DskipTests

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/auth-service/target/auth-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
