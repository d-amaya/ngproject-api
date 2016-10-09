FROM java:8-jdk
MAINTAINER d-amaya <roldaniel89@gmail.com>

ENV JAVA_HOME              /usr/lib/jvm/java-8-openjdk-amd64
ENV JAVA_OPTS              ""
ENV PATH                   $PATH:$JAVA_HOME/bin

COPY build/libs/ngproject-api-0.1.0.jar /app/ngproject-api-0.1.0.jar

WORKDIR /app
EXPOSE 8080

CMD ["/bin/sh", "-c", "java $JAVA_OPTS -jar /app/ngproject-api-0.1.0.jar"]
