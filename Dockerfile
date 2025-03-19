FROM eclipse-temurin:21-jdk-jammy

RUN apt update \
  && apt install tzdata -y \
  && apt clean

#Cambiar zona horaria para chile
ENV TZ="America/Bogota"

ARG JAR_FILE=target/tenpo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080/tcp

ENTRYPOINT ["java","-jar","/app.jar"]