FROM openjdk:22-jdk

WORKDIR /app

COPY . /app

COPY target/stats_api-0.0.1-SNAPSHOT.jar stats_api.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "stats_api.jar"]
