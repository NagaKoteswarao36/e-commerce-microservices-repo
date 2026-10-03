FROM maven:3.9-eclipse-temurin-20 AS build
WORKDIR /workspace
COPY . .
RUN mvn -pl api-gateway -am clean package -DskipTests
FROM eclipse-temurin:20-jre
WORKDIR /app
COPY --from=build /workspace/api-gateway/target/api-gateway-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
