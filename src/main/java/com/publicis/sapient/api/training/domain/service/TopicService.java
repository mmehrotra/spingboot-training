package com.publicis.sapient.api.training.domain.service;

import com.publicis.sapient.api.training.domain.entity.Course;
import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.messaging.Publisher;
import com.publicis.sapient.api.training.domain.repository.TopicCacheRepository;
import com.publicis.sapient.api.training.domain.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    private TopicRepository topicRepository;
    private TopicCacheRepository topicCacheRepository;
    private Publisher publisher;

    public TopicService(TopicRepository topicRepository, TopicCacheRepository topicCacheRepository, Publisher publisher) {
        this.topicRepository = topicRepository;
        this.topicCacheRepository = topicCacheRepository;
        this.publisher = publisher;
    }

    public String createTopic(Topic topicReq) {
        Topic topic = topicRepository.saveUpdateTopic(topicReq);
        topicCacheRepository.putTopic(topic.getId(), topic);
        //publisher.publish(topic);
        return topic.getId();
    }

    public Topic getTopic(String topicId) {
        Topic topic = topicCacheRepository.getTopic(topicId);
        if (topic == null) {
            topic = topicRepository.getTopic(topicId);
        }
        return topic;
    }

    public List<Topic> getAllTopic() {
        return topicRepository.getAllTopic();
    }

    public Topic updateTopic(String topicId, Topic topicReq) {
        Topic topic = topicRepository.getTopic(topicId);
        if (topic != null) {
            mapTopic(topic, topicReq);
            topicRepository.saveUpdateTopic(topic);
        }

        return topic;
    }

    public Boolean deleteTopic(String topicId) {
        return topicRepository.deleteTopic(topicId);
    }

    public String createCourse(String topicId, Course course) {
        Topic topic = topicRepository.getTopic(topicId);
        List<Course> courses = topic.getCourses();
        if (courses == null) {
            courses = new ArrayList<>();
        }

        course.setId(getRandomNumber());
        courses.add(course);
        topic.setCourses(courses);
        topicRepository.saveUpdateTopic(topic);

        return course.getId();
    }

    public Course getCourse(String topicId, String courseId) {
        Course course = null;
        Topic topic = topicRepository.getTopic(topicId);
        if (topic != null) {
            course = topic.getCourses().stream().filter(c -> c.getId().equals(courseId)).findAny().orElse(null);
        }

        return course;
    }

    public List<Course> getAllCourse(String topicId) {
        List<Course> courses = null;
        Topic topic = topicRepository.getTopic(topicId);
        if (topic != null) {
            courses = topic.getCourses();
        }

        return courses;
    }

    public void updateCourse(String topicId, String courseId, Course courseReq) {
        Topic topic = topicRepository.getTopic(topicId);
        if (topic != null) {
            Course course = topic.getCourses().stream().filter(c -> c.getId().equals(courseId)).findAny().orElse(null);
            if (course != null) {
                mapCourse(course, courseReq);
                topicRepository.saveUpdateTopic(topic);
            }
        }
    }

    public Boolean deleteCourse(String topicId, String courseId) {
        Topic topic = topicRepository.getTopic(topicId);
        if (topic != null) {
            topic.getCourses().removeIf(course -> course.getId().equals(courseId));
            topicRepository.saveUpdateTopic(topic);
            return true;
        }

        return false;
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

    private void mapTopic(Topic topic, Topic topicReq) {
        if (topicReq.getDescription() != null) {
            topic.setDescription(topicReq.getDescription());
        }
        if (topicReq.getName() != null) {
            topic.setName(topicReq.getName());
        }
    }

    private void mapCourse(Course course, Course courseReq) {
        if (courseReq.getDelivery() != null) {
            course.setDelivery(courseReq.getDelivery());
        }
        if (courseReq.getDescription() != null) {
            course.setDescription(courseReq.getDescription());
        }
        if (courseReq.getDuration() != null) {
            course.setDuration(courseReq.getDuration());
        }
        if (courseReq.getName() != null) {
            course.setName(courseReq.getName());
        }
    }
}
