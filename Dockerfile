FROM openjdk:14-alpine
COPY build/libs/kafka-micronaut-demo-*-all.jar kafka-micronaut-demo.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "kafka-micronaut-demo.jar"]