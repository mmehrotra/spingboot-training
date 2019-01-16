package com.publicis.sapient.api.training.infrastructure.kafka;

import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.messaging.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisherImpl implements Publisher {

    @Value(value = "${kafka.topic}")
    private String topic;

    private KafkaTemplate<String, Topic> kafkaTemplate;

    public KafkaPublisherImpl(KafkaTemplate<String, Topic> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Topic topicReq) {
        kafkaTemplate.send(topic, topicReq);
    }
}
