package com.spring.training.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	@Autowired
	TopicRepository topicRespository;

	public TopicService(TopicRepository topicRepository) {
		this.topicRespository = topicRepository;
	}
	
	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<Topic>();
		topicRespository.findAll().forEach(topics::add);
		return topics;
	}

	public Topic getTopic(String id) {
		return topicRespository.findOne(id);
	}

	public Topic addTopic(Topic topic) {
		Topic returnTopic = topicRespository.save(topic);
		return returnTopic;
	}

	public Topic updateTopic(Topic topic, String id) {
		Topic returnTopic = topicRespository.save(topic);
		return returnTopic;
	}

	public void deleteTopic(String id) {
		topicRespository.delete(id);
	}

}
