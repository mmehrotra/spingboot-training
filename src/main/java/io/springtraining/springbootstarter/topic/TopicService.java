package io.springtraining.springbootstarter.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	@Autowired
	TopicRespository topicRespository;

	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<Topic>();
		topicRespository.findAll().forEach(topics::add);
		return topics;
	}

	public Topic getTopic(String id) {
		return topicRespository.findOne(id);
	}

	public void addTopic(Topic topic) {
		topicRespository.save(topic);
	}

	public void updateTopic(Topic topic, String id) {
		topicRespository.save(topic);
	}

	public void deleteTopic(String id) {
		topicRespository.delete(id);
	}

}
