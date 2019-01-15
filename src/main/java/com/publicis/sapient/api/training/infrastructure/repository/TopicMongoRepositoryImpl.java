package com.publicis.sapient.api.training.infrastructure.repository;

import com.publicis.sapient.api.training.domain.entity.Course;
import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.repository.TopicRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
    public String createTopic(Topic topic) {
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
    public void updateTopic(String topicId, Topic topic) {
        mongoTemplate.updateFirst(query(where("_id").is(topicId)), getTopicUpdateQuery(topic), Topic.class);
    }

    @Override
    public void deleteTopic(String topicId) {
        mongoTemplate.remove(query(where("_id").is(topicId)), Topic.class);
    }

    @Override
    public void createCourse(String topicId, Topic topic) {
        mongoTemplate.save(topic);
    }

    @Override
    public Course getCourse(String topicId, String courseId) {

        Course course = null;
        Topic topic = mongoTemplate.findOne(new Query().addCriteria(
                new Criteria().andOperator(
                        Criteria.where("id").is(topicId),
                        Criteria.where("courses.id").is(courseId)
                )
        ), Topic.class);

        if (topic != null) {
            course = topic.getCourses().get(0);
        }

        return course;
    }

    @Override
    public List<Course> getAllCourse(String topicId) {
        List<Course> courses = null;
        Topic topic = mongoTemplate.findOne(new Query().addCriteria(
                new Criteria().andOperator(
                        Criteria.where("id").is(topicId)
                )
        ), Topic.class);

        if (topic != null) {
            courses = topic.getCourses();
        }

        return courses;
    }

    @Override
    public void updateCourse(String topicId, String courseId, Course course) {
        mongoTemplate.updateFirst(query(where("_id").is(topicId)), new Update().addToSet("courses", course), Course.class);
    }

    @Override
    public void deleteCourse(String topicId, String courseId) {

    }

    private Update getTopicUpdateQuery(Topic topic) {
        Update update = new Update();

        if (topic.getDescription() != null) {
            update.set("description", topic.getDescription());
        }

        if (topic.getName() != null) {
            update.set("name", topic.getName());
        }

        return update;
    }

    private Update getCourseUpdateQuery(Course course) {
        Update update = new Update();

        if (course.getDelivery() != null) {
            update.set("delivery", course.getDelivery());
        }
        if (course.getDescription() != null) {
            update.set("description", course.getDescription());
        }
        if (course.getDuration() != null) {
            update.set("duration", course.getDuration());
        }
        if (course.getName() != null) {
            update.set("name", course.getName());
        }

        return update;
    }
}