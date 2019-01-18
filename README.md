# spingboot-training
This repository is for SpringBoot Training

$mvn -N io.takari:maven:wrapper

$./mvnw test spring-boot:run


zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
kafka-server-start /usr/local/etc/kafka/server.properties

kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topics
kafka-console-producer --broker-list localhost:9092 --topic topics
kafka-console-consumer --bootstrap-server localhost:9092 --topic topics --from-beginning