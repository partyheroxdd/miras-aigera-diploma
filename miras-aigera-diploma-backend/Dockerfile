FROM maven:3.8.3-openjdk-17 AS MAVEN_BUILD

COPY ./ ./

RUN mvn clean package -DskipTests

FROM openjdk:17

COPY --from=MAVEN_BUILD /target/diploma-app-backend.jar /diploma-app-backend.jar

CMD ["java", "-jar", "/diploma-app-backend.jar"]

