FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml ./
COPY api-gateway/pom.xml ./api-gateway/
COPY shared/pom.xml ./shared/
COPY auth-service/pom.xml ./auth-service/
COPY item-service/pom.xml ./item-service/

RUN mvn -B dependency:go-offline -pl auth-service -am

COPY . .

RUN mvn clean install -pl auth-service -am -DskipTests

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/auth-service/target/auth-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
