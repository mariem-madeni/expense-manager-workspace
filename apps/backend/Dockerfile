FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN apt-get update && apt-get install -y maven && mvn package -DskipTests

EXPOSE 8083

CMD ["java", "-jar", "target/expense-manager-1.0-SNAPSHOT.jar"]
