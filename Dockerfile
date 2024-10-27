FROM maven:3.8.6-eclipse-temurin-17 as builder
WORKDIR lab2/app
COPY mvnw pom.xml ./
COPY ./src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR lab2/app
EXPOSE 8080
COPY --from=builder /lab2/app/target/*.jar /lab2/app/*.jar
ENTRYPOINT ["java", "-jar", "/lab2/app/*.jar"]