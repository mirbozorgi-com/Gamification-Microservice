# Start with a base image containing Java runtime
FROM repository.mirbozorgi.com/micro/base:2.0.0

# Add Maintainer Info
LABEL maintainer="http://www.mirbozorgi.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=/src/api/target/user-service.jar

# Add the application's jar to the container
ADD ${JAR_FILE} user-service.jar

ARG WAIT_SH=./wait-for-it.sh
ADD ${WAIT_SH} wait-for-it.sh
RUN apk add bash && apk add curl

RUN chmod +x ./wait-for-it.sh
