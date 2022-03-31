# Start with a base image containing Java runtime
FROM repository.mirbozorgigames.com/micro/base:2.0.0

# Add Maintainer Info
LABEL maintainer="http://www.mirbozorgigames.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=/base/target/config-service.jar

# Add the application's jar to the container
ADD ${JAR_FILE} config-service.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/config-service.jar"]