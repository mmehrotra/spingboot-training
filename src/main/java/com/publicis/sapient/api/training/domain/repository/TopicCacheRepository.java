package com.publicis.sapient.api.training.domain.repository;

import com.publicis.sapient.api.training.domain.entity.Topic;

public interface TopicCacheRepository {

    void putTopic(String key, Topic topic);

    Topic getTopic(String key);
}
