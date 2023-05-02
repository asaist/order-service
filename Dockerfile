FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-Xms512M", "-Xmx1024M", "-Duser.timezone=Europe/Moscow", "-jar","/app.jar"]
