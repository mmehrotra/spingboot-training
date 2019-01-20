package com.publicis.sapient.api.training.domain.service;

import com.publicis.sapient.api.training.domain.entity.Course;
import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.events.MessagePublisher;
import com.publicis.sapient.api.training.domain.repository.TopicCacheRepository;
import com.publicis.sapient.api.training.domain.repository.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    private Logger logger = LoggerFactory.getLogger(TopicService.class);
    private TopicRepository topicRepository;
    private TopicCacheRepository topicCacheRepository;
    private MessagePublisher messagePublisher;

    public TopicService(TopicRepository topicRepository, TopicCacheRepository topicCacheRepository, MessagePublisher messagePublisher) {
        this.topicRepository = topicRepository;
        this.topicCacheRepository = topicCacheRepository;
        this.messagePublisher = messagePublisher;
    }

    public String createTopic(Topic topicReq) {
        Topic topic = topicRepository.saveUpdateTopic(topicReq);
        putTopicCache(topic.getId(), topic);
        //messagePublisher.publish(topic);
        return topic.getId();
    }

    public Topic getTopic(String topicId) {
        Topic topic = getTopicCache(topicId);
        if (topic == null) {
            topic = topicRepository.getTopic(topicId);
        }
        return topic;
    }

    public List<Topic> getAllTopic() {
        return topicRepository.getAllTopic();
    }

    public Topic updateTopic(String topicId, Topic topicReq) {
        Topic topic = getTopicCache(topicId);
        if (topic == null) {
            topic = topicRepository.getTopic(topicId);
        }

        if (topic != null) {
            mapTopic(topic, topicReq);
            topicRepository.saveUpdateTopic(topic);
            putTopicCache(topic.getId(), topic);
        }

        return topic;
    }

    public Boolean deleteTopic(String topicId) {
        Boolean isDeleted = topicRepository.deleteTopic(topicId);
        if (isDeleted) {
            deleteTopicCache(topicId);
        }
        return isDeleted;
    }

    public String createCourse(String topicId, Course course) {
        String courseId = null;
        Topic topic = getTopicCache(topicId);
        if (topic == null) {
            topic = topicRepository.getTopic(topicId);
        }

        if (topic != null) {
            List<Course> courses = topic.getCourses();
            if (courses == null) {
                courses = new ArrayList<>();
            }

            courseId = getRandomNumber();
            course.setId(courseId);
            courses.add(course);
            topic.setCourses(courses);
            topicRepository.saveUpdateTopic(topic);
            putTopicCache(topic.getId(), topic);
        }

        return courseId;
    }

    public Course getCourse(String topicId, String courseId) {
        Course course = null;
        Topic topic = getTopicCache(topicId);
        if (topic == null) {
            topic = topicRepository.getTopic(topicId);
        }

        if (topic != null) {
            course = topic.getCourses().stream().filter(c -> c.getId().equals(courseId)).findAny().orElse(null);
        }

        return course;
    }

    public List<Course> getAllCourse(String topicId) {
        List<Course> courses = null;
        Topic topic = getTopicCache(topicId);
        if (topic == null) {
            topic = topicRepository.getTopic(topicId);
        }

        if (topic != null) {
            courses = topic.getCourses();
        }

        return courses;
    }

    public void updateCourse(String topicId, String courseId, Course courseReq) {
        Topic topic = getTopicCache(topicId);
        if (topic == null) {
            topic = topicRepository.getTopic(topicId);
        }

        if (topic != null) {
            Course course = topic.getCourses().stream().filter(c -> c.getId().equals(courseId)).findAny().orElse(null);
            if (course != null) {
                mapCourse(course, courseReq);
                topicRepository.saveUpdateTopic(topic);
                putTopicCache(topic.getId(), topic);
            }
        }
    }

    public Boolean deleteCourse(String topicId, String courseId) {
        Topic topic = getTopicCache(topicId);
        if (topic == null) {
            topic = topicRepository.getTopic(topicId);
        }

        if (topic != null) {
            topic.getCourses().removeIf(course -> course.getId().equals(courseId));
            topicRepository.saveUpdateTopic(topic);
            putTopicCache(topic.getId(), topic);
            return true;
        }

        return false;
    }

    private void putTopicCache(String topicId, Topic topic) {
        try {
            topicCacheRepository.putTopic(topicId, topic);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Topic getTopicCache(String topicId) {
        Topic topic = null;
        try {
            topic = topicCacheRepository.getTopic(topicId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return topic;
    }

    private Long deleteTopicCache(String topicId) {
        Long count = null;
        try {
            count = topicCacheRepository.deleteTopic(topicId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
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
