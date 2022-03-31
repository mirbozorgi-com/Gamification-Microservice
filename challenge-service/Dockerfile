FROM maven:3.5.4-jdk-8-alpine as build 
COPY  src /app/src
WORKDIR /app/src
RUN mvn clean package

FROM repository.mirbozorgigames.com/micro/base:2.0.0
WORKDIR /app
COPY --from=build /app/src/api/target/challenge-service.jar /app

ARG WAIT_SH=./wait-for-it.sh
ADD ${WAIT_SH} wait-for-it.sh
RUN apk add bash && apk add curl

RUN chmod +x ./wait-for-it.sh