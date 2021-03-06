package com.publicis.sapient.api.training.infrastructure.mongo.repository;

import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.repository.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class TopicRepositoryImpl implements TopicRepository {

    private Logger logger = LoggerFactory.getLogger(TopicRepositoryImpl.class);

    private final MongoTemplate mongoTemplate;

    public TopicRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Topic saveUpdateTopic(Topic topic) {
        return mongoTemplate.save(topic);
    }

    @Override
    public Topic getTopic(String topicId) {
        return mongoTemplate.findOne(query(where("_id").is(topicId)), Topic.class);
    }

    @Override
    public List<Topic> getAllTopic() {
        return mongoTemplate.findAll(Topic.class);
    }

    @Override
    public Boolean deleteTopic(String topicId) {
        return mongoTemplate.remove(query(where("_id").is(topicId)), Topic.class).wasAcknowledged();
    }

}