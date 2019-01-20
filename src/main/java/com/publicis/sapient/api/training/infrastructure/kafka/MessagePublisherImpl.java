package com.publicis.sapient.api.training.infrastructure.kafka;

import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.events.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisherImpl implements MessagePublisher {

    private Logger logger = LoggerFactory.getLogger(MessagePublisherImpl.class);

    @Value(value = "${kafka.topic}")
    private String topic;

    private KafkaTemplate<String, Topic> kafkaTemplate;

    public MessagePublisherImpl(KafkaTemplate<String, Topic> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Topic topicMsg) {
        kafkaTemplate.send(topic, topicMsg.getId(), topicMsg);
    }
}
