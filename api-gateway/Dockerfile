FROM maven:3.8.8-eclipse-temurin-8 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package


FROM tomcat:8.5.87-jdk8-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/api-gateway.war \
    /usr/local/tomcat/webapps/ROOT.war