# spingboot-training
This repository is for SpringBoot Training

$mvn -N io.takari:maven:wrapper

$./mvnw test spring-boot:run


zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
kafka-server-start /usr/local/etc/kafka/server.properties

kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topics
kafka-console-producer --broker-list localhost:9092 --topic topics
kafka-console-consumer --bootstrap-server localhost:9092 --topic topics --from-beginning


http://localhost:8081/actuator/beans
http://localhost:8081/actuator/health
http://localhost:8081/actuator/configprops
http://localhost:8081/actuator/env
http://localhost:8081/actuator/httptrace
http://localhost:8081/actuator/info
http://localhost:8081/actuator/loggers


Change logger level - 
curl -i -X POST -H 'Content-Type: application/json' -d '{"configuredLevel": "DEBUG"}' http://localhost:8081/actuator/loggers/ROOT

Install Promethus
https://prometheus.io/download/
tar xvfz prometheus-*.tar.gz
cd prometheus-*
./prometheus --config.file=prometheus.yml

Download Grafana
https://grafana.com/grafana/download
brew update 
brew install grafana
brew services start grafana

Add Promethueus datasource in Grafana and configure dashbaord

Postman collections - https://www.getpostman.com/collections/7b47cc424b30c0e9c310
