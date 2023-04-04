FROM maven:3.8.3-openjdk-17 AS MAVEN_BUILD
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install -DskipTests

FROM openjdk:17-alpine
ENV TZ=Asia/Almaty
COPY --from=MAVEN_BUILD /home/app/target/diploma-app-backend.jar diploma-app-backend.jar
EXPOSE 8080
MAINTAINER Miras Madiyev <27677@iitu.edu.kz>
ENTRYPOINT ["java","-jar","diploma-app-backend.jar"]