#Base image with Java runtime
FROM openjdk:8-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Add jar file to container. JAR_FILE also provided as argument
ARG JAR_FILE='neo-service-statements/target/exchange-service-0.0.1-SNAPSHOT.jar'
ADD ${JAR_FILE} statements-service.jar

# Run the jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/statements-service.jar"]


FROM penjdk:8-jdk-alpine

VOLUME /tmp
