FROM maven:3.8.1-openjdk-16

COPY . .

RUN mvn install

CMD ["java", "-jar", "./target/back-0.0.1-SNAPSHOT.jar"]
