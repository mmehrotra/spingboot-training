package com.publicis.sapient.api.training.domain.repository;

import com.publicis.sapient.api.training.domain.entity.Course;
import com.publicis.sapient.api.training.domain.entity.Topic;

import java.util.List;

public interface TopicRepository {

    String createTopic(Topic topic);

    Topic getTopic(String topicId);

    List<Topic> getAllTopic();

    void updateTopic(String topicId, Topic topic);

    void deleteTopic(String topicId);

    void createCourse(String topicId, Topic topic);

    Course getCourse(String topicId, String courseId);

    List<Course> getAllCourse(String topicId);

    void updateCourse(String topicId, String courseId, Course course);

    void deleteCourse(String topicId, String courseId);
}
