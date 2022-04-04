# Start with a base image containing Java runtime
FROM repository.mirbozorgiarsalan.com/micro/base:2.0.0


# Add Maintainer Info
LABEL maintainer="http://www.mirbozorgiarsalan.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=/base/target/api-gateway.jar
# Add the application's jar to the container
ADD ${JAR_FILE} api-gateway.jar

ARG WAIT_SH=./wait-for-it.sh
ADD ${WAIT_SH} wait-for-it.sh
RUN apk add bash && apk add curl

RUN chmod +x ./wait-for-it.sh

