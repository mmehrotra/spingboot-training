# Topics Application

## Before you begin
- Install Java 8 - https://www.guru99.com/install-java.html
- Install Maven - https://maven.apache.org/install.html
- Install STS IDE - https://javatutorial.net/spring-web-app-sts 
- Install MongoDB - https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/ 
- Install Redis - https://redis.io/topics/quickstart 
- Install Kafka - https://kafka.apache.org/quickstart 

## Start Application
Open terminal and execute below commands to start application.
```text
$mvn -N io.takari:maven:wrapper
```
```text
$./mvnw test spring-boot:run
```

Open Swagger UI
```text
http://localhost:8081/swagger-ui.html
```
## Install Prometheus
- Download binary from https://prometheus.io/download
- Extract file “tar xvfz prometheus-*.tar.gz”
- cd prometheus-*
- Execute “./prometheus --config.file=prometheus.yml

You can find prometheus.yml in /platform/prometheus/
#### Open Prometheus UI 
```text
http://localhost:9090/graph
```
