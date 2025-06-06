FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml ./
COPY api-gateway/pom.xml ./api-gateway/
COPY shared/pom.xml ./shared/
COPY auth-service/pom.xml ./auth-service/
COPY item-service/pom.xml ./item-service/

# Pre-download dependencies (optional but helpful)
RUN mvn -B dependency:go-offline -pl item-service -am

COPY . .

RUN mvn clean install -pl item-service -am -DskipTests

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/item-service/target/item-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
