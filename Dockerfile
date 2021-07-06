FROM maven:3.8.1-openjdk-16

COPY . .

RUN mvn install -Dmaven.test.skip=true

CMD ["java", "-jar", "./target/back-0.0.1-SNAPSHOT.jar"]