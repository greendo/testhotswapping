FROM java:8

WORKDIR /testhotswapping
ADD ./target/testhotswapping-0.0.1-SNAPSHOT.jar /testhotswapping/testhotswapping.jar

CMD java -jar testhotswapping.jar