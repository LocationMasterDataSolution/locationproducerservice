ARG JAVA_VERSION=17
FROM openjdk:${JAVA_VERSION}
COPY target/locationproducerservice-0.0.1-SNAPSHOT.jar locationproducerservice-docker.jar
EXPOSE 8888
CMD ["java","-jar","/locationproducerservice-docker.jar"]