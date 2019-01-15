package com.publicis.sapient.api.training.infrastructure.repository;

import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.repository.TopicRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class TopicMongoRepositoryImpl implements TopicRepository {

    private final MongoTemplate mongoTemplate;

    public TopicMongoRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String saveUpdateTopic(Topic topic) {
        return mongoTemplate.save(topic).getId();
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