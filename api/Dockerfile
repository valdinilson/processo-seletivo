# Etapa 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /usr/src/app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /usr/src/app/src/
RUN mvn clean package -DskipTests

# Etapa 2: Executa a aplicação
FROM eclipse-temurin:21

RUN mkdir -p /opt/seletivo

WORKDIR /opt/seletivo

COPY --from=builder /usr/src/app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
