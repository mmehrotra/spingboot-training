package com.publicis.sapient.api.training.domain.service;

import com.publicis.sapient.api.training.domain.entity.Course;
import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic createTopic(Topic topic) {
        return topicRepository.createTopic(topic);
    }

    public Topic getTopic(String topicId) {
        return topicRepository.getTopic(topicId);
    }

    public List<Topic> getAllTopic() {
        return topicRepository.getAllTopic();
    }

    public void updateTopic(String topicId, Topic topic) {
        topicRepository.updateTopic(topicId, topic);
    }

    public void deleteTopic(String topicId) {
        topicRepository.deleteTopic(topicId);
    }

    public Course createCourse(String topicId, Course course) {
        return topicRepository.createCourse(topicId, course);
    }

    public Course getCourse(String topicId, String courseId) {
        return topicRepository.getCourse(topicId, courseId);
    }

    public List<Course> getAllCourse(String topicId) {
        return topicRepository.getAllCourse(topicId);
    }

    public void updateCourse(String topicId, String courseId, Course course) {
        topicRepository.updateCourse(topicId, courseId, course);
    }

    public void deleteCourse(String topicId, String courseId) {
        topicRepository.deleteCourse(topicId, courseId);
    }
}
