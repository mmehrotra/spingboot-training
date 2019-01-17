package com.publicis.sapient.api.training.domain.repository;

import com.publicis.sapient.api.training.domain.entity.Topic;

import java.util.List;

public interface TopicRepository {

    Topic saveUpdateTopic(Topic topic);

    Topic getTopic(String topicId);

    List<Topic> getAllTopic();

    Boolean deleteTopic(String topicId);
}
