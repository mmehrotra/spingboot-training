package com.publicis.sapient.api.training.application.wrapper;

import com.publicis.sapient.api.training.domain.entity.Topic;
import lombok.Data;

import java.util.List;

@Data
public class TopicWrapper {

    private List<Topic> data;
}
