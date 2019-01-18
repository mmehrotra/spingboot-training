package com.publicis.sapient.api.training.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.events.MessagePublisher;
import com.publicis.sapient.api.training.domain.repository.TopicCacheRepository;
import com.publicis.sapient.api.training.domain.repository.TopicRepository;
import com.publicis.sapient.api.training.domain.service.TopicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicServiceUnitTest {

    @Autowired
    private TopicService topicService;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private TopicCacheRepository topicCacheRepository;

    @MockBean
    private MessagePublisher messagePublisher;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTopic() throws Exception {
        Topic topic = loadTopic();
        BDDMockito.given(topicRepository.saveUpdateTopic(topic)).willReturn(topic);
        String topicId = topicService.createTopic(topic);
        assertThat(topicId).isNotNull().isNotBlank();
    }

    @Test
    public void getTopic() throws Exception {
        Topic topic = loadTopic();
        BDDMockito.given(topicRepository.getTopic(topic.getId())).willReturn(topic);
        topic = topicService.getTopic(topic.getId());
        assertThat(topic).isNotNull();
    }

    @Test
    public void getAllTopic() throws Exception {
        Topic topic = loadTopic();
        List<Topic> topics = new ArrayList();
        topics.add(topic);
        BDDMockito.given(topicRepository.getAllTopic()).willReturn(topics);
        topics = topicService.getAllTopic();
        assertThat(topics).isNotNull().isNotEmpty();
    }

    @Test
    public void updateTopic() throws Exception {
        Topic topic = loadTopic();
        BDDMockito.given(topicRepository.getTopic(topic.getId())).willReturn(topic);
        topic = topicService.updateTopic(topic.getId(), topic);
        assertThat(topic).isNotNull();
    }

    @Test
    public void deleteTopic() throws Exception {
        Topic topic = loadTopic();
        BDDMockito.given(topicRepository.deleteTopic(topic.getId())).willReturn(true);
        Boolean isDeleted = topicService.deleteTopic(topic.getId());
        assertThat(isDeleted).isNotNull().isTrue();
    }

    private Topic loadTopic() throws Exception {
        Topic topic = objectMapper.readValue(new ClassPathResource("data/topic.json").getFile(),
                Topic.class);
        topic.setId(getRandomNumber());
        return topic;
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
