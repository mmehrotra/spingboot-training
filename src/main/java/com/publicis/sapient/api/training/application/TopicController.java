package com.publicis.sapient.api.training.application;

import com.publicis.sapient.api.training.application.wrapper.CourseWrapper;
import com.publicis.sapient.api.training.application.wrapper.TopicWrapper;
import com.publicis.sapient.api.training.domain.entity.Course;
import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.service.TopicService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/topics")
public class TopicController {

    private TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> createTopic(@RequestBody Topic topicBody) {
        Topic topic = topicService.createTopic(topicBody);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("topics/" + topic.getId()).build().toUri()).build();
    }

    @GetMapping(path = "/{topicId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<TopicWrapper> getTopic(@PathVariable String topicId) {
        Topic topic = topicService.getTopic(topicId);
        List topics = new ArrayList();
        topics.add(topic);

        TopicWrapper wrapper = new TopicWrapper();
        wrapper.setData(topics);

        return ResponseEntity.ok(wrapper);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<TopicWrapper> getAllTopic() {
        List<Topic> topics = topicService.getAllTopic();
        TopicWrapper wrapper = new TopicWrapper();
        wrapper.setData(new ArrayList<>(topics));
        return ResponseEntity.ok(wrapper);
    }

    @PutMapping(path = "/{topicId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> updateTopic(@PathVariable String topicId, @RequestBody Topic topic) {
        topicService.updateTopic(topicId, topic);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{topicId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> deleteTopic(@PathVariable String topicId) {
        topicService.deleteTopic(topicId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{topicId}/courses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> createCourse(@PathVariable String topicId, @RequestBody Course courseBody) {
        Course course = topicService.createCourse(topicId, courseBody);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("topics/" + topicId + "/" + course.getId()).build().toUri()).build();
    }

    @GetMapping(path = "/{topicId}/courses/{courseId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CourseWrapper> getCourse(@PathVariable String topicId, @PathVariable String courseId) {
        Course course = topicService.getCourse(topicId, courseId);
        List courses = new ArrayList();
        courses.add(course);

        CourseWrapper wrapper = new CourseWrapper();
        wrapper.setData(courses);

        return ResponseEntity.ok(wrapper);
    }

    @GetMapping(path = "/{topicId}/courses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CourseWrapper> getAllCourse(@PathVariable String topicId) {
        List<Course> courses = topicService.getAllCourse(topicId);
        CourseWrapper wrapper = new CourseWrapper();
        wrapper.setData(new ArrayList<>(courses));
        return ResponseEntity.ok(wrapper);
    }

    @PutMapping(path = "/{topicId}/courses/{courseId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> updateCourse(@PathVariable String topicId, @PathVariable String courseId, @RequestBody Course course) {
        topicService.updateCourse(topicId, courseId, course);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{topicId}/courses/{courseId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> deleteCourse(@PathVariable String topicId, @PathVariable String courseId) {
        topicService.deleteCourse(topicId, courseId);
        return ResponseEntity.noContent().build();
    }

}
