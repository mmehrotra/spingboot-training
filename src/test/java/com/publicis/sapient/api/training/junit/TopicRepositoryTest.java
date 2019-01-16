package com.publicis.sapient.api.training.junit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@DataJpaTest
public class TopicRepositoryTest {
/*
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TopicRepository topicRepository;

	@Before
	public void setup() {
		Topic topic = new Topic();
		topic.setId("java");
		topic.setName("Java Framework");
		topic.setDescription("Java Framework Description");
		entityManager.persist(topic);
	}

	@Test
	public void testFindByName() {
		Topic returnedTopic = topicRepository.findOne("java");
		assertEquals("Java Framework", returnedTopic.getName());
	}

	@Test
	public void testFindByType() {
		Iterable<Topic> returnedTopic = topicRepository.findAll();
		assertEquals("Java Framework", returnedTopic.iterator().next().getName());
	}
*/
}
