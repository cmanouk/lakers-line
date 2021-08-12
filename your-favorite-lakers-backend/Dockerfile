FROM amazoncorretto:11
WORKDIR /llserver
COPY /target/your-favorite-lakers-0.0.1-SNAPSHOT.jar your-favorite-lakers-0.0.1-SNAPSHOT.jar
COPY keystore.p12 keystore.p12
CMD ["java", "-jar", "your-favorite-lakers-0.0.1-SNAPSHOT.jar"]