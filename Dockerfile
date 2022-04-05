# Start with a base image containing Java runtime
FROM openjdk:8-jre-alpine

# Add Maintainer Info
LABEL maintainer="http://www.mirbozorgi.com"

# Add a volume pointing to /tmp
VOLUME /tmp

RUN apk update && apk add bash && apk add curl

