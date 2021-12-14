FROM openjdk:11

RUN apt-get update && apt-get install -y maven
COPY . /project
RUN  cd /project && mvn package

ARG DB_URL
ARG PASSWORD
ARG USERNAME
ARG HOST_URL
ARG RMQ_USERNAME
ARG RMQ_PASSWORD

ENV DB_URL=$DB_URL
ENV PASSWORD=$PASSWORD
ENV USERNAME=$USERNAME
ENV HOST_URL=$HOST_URL
ENV RMQ_USERNAME=$RMQ_USERNAME
ENV RMQ_PASSWORD=$RMQ_PASSWORD
 

#run the spring boot application
ENTRYPOINT ["java", "-jar","/project/target/Sensor_API-0.0.1-SNAPSHOT.jar"]
