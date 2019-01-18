package com.publicis.sapient.api.training.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.sapient.api.training.domain.entity.Course;
import com.publicis.sapient.api.training.domain.entity.Topic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TopicControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TopicController topicController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createANewStock() throws Exception {
        Topic topic = objectMapper.readValue(new ClassPathResource("data/topic.json").getFile(),
                Topic.class);

        this.mockMvc.perform(post("/topics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(topic)))
                .andExpect(status().isCreated());
        cleanTopic(topic.getId());
    }

    @Test
    public void getTopic() throws Exception {
        Topic topic = loadTopic();
        String url = "/topics/" + topic.getId();
        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.[0].name", is(topic.getName())));
        cleanTopic(topic.getId());
    }

    @Test
    public void getAllTopic() throws Exception {
        String url = "/topics";
        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateTopic() throws Exception {
        Topic topic = loadTopic();
        String url = "/topics/" + topic.getId();
        topic.setName("XYZ");
        topic.setDescription("ABC");

        this.mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(topic)))
                .andExpect(status().isNoContent());
        cleanTopic(topic.getId());
    }

    @Test
    public void deleteTopic() throws Exception {
        Topic topic = loadTopic();
        String url = "/topics/" + topic.getId();
        this.mockMvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void createCourse() throws Exception {
        Topic topic = loadTopic();
        Course course = objectMapper.readValue(new ClassPathResource("data/course.json").getFile(),
                Course.class);
        String url = "/topics/" + topic.getId() + "/courses";

        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isCreated());
        cleanTopic(topic.getId());
    }

    @Test
    public void getCourse() throws Exception {
        Topic topic = loadTopic();
        Course course = loadCourse(topic);
        String url = "/topics/" + topic.getId() + "/courses/" + course.getId();

        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.[0].name", is(course.getName())));
        cleanTopic(topic.getId());
    }

    @Test
    public void getAllCourse() throws Exception {
        Topic topic = loadTopic();
        loadCourse(topic);
        String url = "/topics/" + topic.getId() + "/courses";

        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        cleanTopic(topic.getId());
    }

    @Test
    public void updateCourse() throws Exception {
        Topic topic = loadTopic();
        Course course = loadCourse(topic);
        String url = "/topics/" + topic.getId() + "/courses/" + course.getId();

        course.setName("XYZ");
        course.setDuration("ABC");

        this.mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isNoContent());
        cleanTopic(topic.getId());
    }

    @Test
    public void deleteCourse() throws Exception {
        Topic topic = loadTopic();
        Course course = loadCourse(topic);
        String url = "/topics/" + topic.getId() + "/courses/" + course.getId();

        this.mockMvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        cleanTopic(topic.getId());
    }

    private Topic loadTopic() throws Exception {
        Topic topic = objectMapper.readValue(new ClassPathResource("data/topic.json").getFile(),
                Topic.class);
        ResponseEntity<String> response = topicController.createTopic(topic);
        String path = response.getHeaders().getLocation().getPath();
        topic.setId(path.substring(path.indexOf("/") + 1, path.length()));
        return topic;
    }

    private void cleanTopic(String topicId) throws Exception {
        topicController.deleteTopic(topicId);
    }

    private Course loadCourse(Topic topic) throws Exception {
        Course course = objectMapper.readValue(new ClassPathResource("data/course.json").getFile(),
                Course.class);
        ResponseEntity<String> response = topicController.createCourse(topic.getId(), course);
        String path = response.getHeaders().getLocation().getPath();
        course.setId(path.substring(path.lastIndexOf("/") + 1, path.length()));
        return course;
    }

}
