FROM amazoncorretto:11
WORKDIR /llserver
COPY /target/your-favorite-lakers-0.0.1-SNAPSHOT.jar your-favorite-lakers-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "your-favorite-lakers-0.0.1-SNAPSHOT.jar"]

# FROM maven:3.6.3-openjdk-11-slim as BUILDER
# WORKDIR /build/
# COPY pom.xml /build/
# COPY src /build/src/
#
# RUN mvn clean package
# COPY target/your-favorite-lakers-backend-0.0.1-SNAPSHOT.jar target/application.jar
#
# FROM amazoncorretto:11
# WORKDIR /llserver
# COPY --from=BUILDER /build/target/application.jar /app/
# CMD java -jar /app/application.jar