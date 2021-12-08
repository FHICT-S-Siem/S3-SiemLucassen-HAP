FROM openjdk:11

RUN apt-get update && apt-get install -y maven
COPY . /project
RUN  cd /project && mvn package

#run the spring boot application
ENTRYPOINT ["java", "-jar","/project/target/Sensor_API-0.0.1-SNAPSHOT.jar"]

# FROM openjdk:11-jdk-alpine
# ADD target/Sensor_API.jar Sensor_API.jar
# EXPOSE 4444
# ENTRYPOINT ["java", "-jar", "Sensor_API"]