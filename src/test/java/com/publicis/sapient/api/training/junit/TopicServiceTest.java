package com.publicis.sapient.api.training.junit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class TopicServiceTest {
/*
	@Autowired
	private TopicService topicServiceMock;
	private TopicRepository topicRepositoryMock;
	private Topic topic;

	@Before
	public void setUp() {
		topicRepositoryMock = Mockito.mock(TopicRepository.class);
		topicServiceMock = new TopicService(topicRepositoryMock);
		topic = new Topic();
		topic.setId("java");
		topic.setName("Java Framework");
		topic.setDescription("Java Framework Description");
	}

	@Test
	public void createTopicSuccessfuly() throws Exception {

		when(topicRepositoryMock.findOne("java")).thenReturn(topic);
		doAnswer(new Answer<Topic>() {
			@Override
			public Topic answer(final InvocationOnMock invocation) throws Throwable {
				return topic;
			}
		}).when(topicRepositoryMock).save(any(Topic.class));

		Topic topicLocal = new Topic();
		Topic topic = topicServiceMock.addTopic(topicLocal);
		assertEquals("Java Framework", topic.getName());
		assertNotNull(topic.getName());
	}
*/
}
