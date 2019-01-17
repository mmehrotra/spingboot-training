package com.publicis.sapient.api.training.domain.events;

import com.publicis.sapient.api.training.domain.entity.Topic;

public interface MessagePublisher {

    void publish(Topic topic);
}
