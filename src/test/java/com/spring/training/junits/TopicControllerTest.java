package com.spring.training.junits;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.training.topic.Topic;
import com.spring.training.topic.TopicController;
import com.spring.training.topic.TopicService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { TopicController.class }, secure = false)
public class TopicControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	TopicService topicServiceMock;

	@Autowired
	ObjectMapper objectMapper;

	Topic topic;

	@Before
	public void setup() {
		Topic topic = new Topic();
		topic.setId("java");
		topic.setName("Java Framework");
		topic.setDescription("Java Framework Description");
	}

	@Test
	public void testCreateTopicSuccessfully() throws Exception {

		// Positive Test Case
		BDDMockito.given(topicServiceMock.addTopic(any(Topic.class))).willReturn(topic);
		
		mockMvc.perform(post("/topics").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(topic))).andExpect(jsonPath("$.status", is("SUCCESS")))
				.andExpect(jsonPath("$.message", notNullValue())).andExpect(jsonPath("$.data", notNullValue()));


		// Negative Test case
		/*BDDMockito.given(topicServiceMock.addTopic(any(Topic.class))).willReturn(null);

		mockMvc.perform(
				post("/topics").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(topic)))
				.andExpect(jsonPath("$.status", is("FAILURE"))).andExpect(jsonPath("$.message", notNullValue()))
				.andExpect(jsonPath("$.data", nullValue()));
*/
	}

/*	@Test
	public void testGetAllTopics() throws Exception {

		List<Topic> topics = new ArrayList<Topic>();
		topics.add(topic);

		// Positive Test Case
		BDDMockito.given(topicServiceMock.getAllTopics()).willReturn(topics);
		mockMvc.perform(get("/topics").contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status", is("SUCCESS"))).andExpect(jsonPath("$.message", notNullValue()))
				.andExpect(jsonPath("$.data", notNullValue()));

		// Negative Test Case
		BDDMockito.given(topicServiceMock.getAllTopics()).willReturn(null);
		mockMvc.perform(get("/topics").contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status", is("FAILURE"))).andExpect(jsonPath("$.message", notNullValue()))
				.andExpect(jsonPath("$.data", nullValue()));

	}
*/
}
