FROM openjdk:16
RUN mkdir /emagazine
COPY /build/libs/emagazine-0.0.1-SNAPSHOT.jar /emagazine
WORKDIR /emagazine
CMD ["java", "-jar", "emagazine-0.0.1-SNAPSHOT.jar"]