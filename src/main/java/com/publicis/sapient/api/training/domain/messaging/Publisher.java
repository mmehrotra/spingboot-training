package com.publicis.sapient.api.training.domain.messaging;

import com.publicis.sapient.api.training.domain.entity.Topic;

public interface Publisher {

    void publish(Topic topic);
}
