package com.publicis.sapient.api.training.infrastructure.repository;

import com.publicis.sapient.api.training.domain.entity.Course;
import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.repository.TopicRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
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
    public Topic createTopic(Topic topic) {
        topic.setId(mongoTemplate.save(topic).getId());
        return topic;
    }

    @Override
    public Topic getTopic(String topicId) {
        Query query = new Query();
        query.addCriteria(where("id").is(topicId));
        return mongoTemplate.findOne(query, Topic.class);
    }

    @Override
    public List<Topic> getAllTopic() {
        return mongoTemplate.findAll(Topic.class);
    }

    @Override
    public void updateTopic(String topicId, Topic topic) {
        topic.setId(topicId);
        mongoTemplate.save(topic);
    }

    @Override
    public void deleteTopic(String topicId) {
        mongoTemplate.remove(query(where("_id").is(topicId)), Topic.class);
    }

    @Override
    public Course createCourse(String topicId, Course course) {
        return null;
    }

    @Override
    public Course getCourse(String topicId, String courseId) {
        return null;
    }

    @Override
    public List<Course> getAllCourse(String topicId) {
        return null;
    }

    @Override
    public void updateCourse(String topicId, String courseId, Course course) {

    }

    @Override
    public void deleteCourse(String topicId, String courseId) {

    }
}