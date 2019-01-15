package com.publicis.sapient.api.training.domain.service;

import com.publicis.sapient.api.training.domain.entity.Course;
import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public String createTopic(Topic topic) {
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

    public String createCourse(String topicId, Course course) {
        course.setId(getRandomNumber());
        Topic topic = topicRepository.getTopic(topicId);
        List<Course> courses = topic.getCourses();

        if(courses == null) {
            courses = new ArrayList<>();
        }

        courses.add(course);
        topic.setCourses(courses);
        topicRepository.createCourse(topicId, topic);

        return course.getId();
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

    private String getRandomNumber() {
        String number = null;
        try {
            number = Integer.valueOf(SecureRandom.getInstance("SHA1PRNG").nextInt()).toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return number;
    }
}
