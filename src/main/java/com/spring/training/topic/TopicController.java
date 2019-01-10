package com.spring.training.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.training.course.Course;
import com.spring.training.domain.ResponseVO;

@RestController
public class TopicController {

	private static final String FAILURE = "FAILURE";
	private static final String SUCCESS = "SUCCESS";
	@Autowired
	TopicService topicService;

	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public ResponseVO getAllTopics() {
		ResponseVO responseVo = new ResponseVO();
		List<Topic> topics = topicService.getAllTopics();
		if (topics != null && topics.size() > 0) {
			responseVo.setStatus(SUCCESS);
			responseVo.setMessage("Topics have been found successfully");
			responseVo.setData(topics);
		} else {
			responseVo.setStatus(FAILURE);
			responseVo.setMessage("Topics was not fetched successfully");
		}
		return responseVo;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics/{id}")
	public ResponseVO getTopic(@PathVariable String id) {
		ResponseVO responseVo = new ResponseVO();
		Topic topic = topicService.getTopic(id);
		if (topic != null) {
			responseVo.setStatus(SUCCESS);
			responseVo.setMessage("Topic has been found successfully");
			responseVo.setData(topic);
		} else {
			responseVo.setStatus(FAILURE);
			responseVo.setMessage("Topic was not fetched successfully");
		}
		return responseVo;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics", produces="application/json")
	public ResponseVO addTopic(@RequestBody Topic topic) {

		ResponseVO responseVo = new ResponseVO();
		Topic returnTopic = topicService.addTopic(topic);

		if (returnTopic != null) {
			responseVo.setStatus(SUCCESS);
			responseVo.setMessage("Topic has been created successfully");
			responseVo.setData(returnTopic);
		} else {
			responseVo.setStatus(FAILURE);
			responseVo.setMessage("Topic was not created successfully. Please try again !");
		}
		return responseVo;

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
	public ResponseVO updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		topicService.updateTopic(topic, id);

		ResponseVO responseVo = new ResponseVO();
		Topic returnTopic = topicService.updateTopic(topic, id);

		if (returnTopic != null) {
			responseVo.setStatus(SUCCESS);
			responseVo.setMessage("Topic has been updated successfully");
			responseVo.setData(returnTopic);
		} else {
			responseVo.setStatus(FAILURE);
			responseVo.setMessage("Topic was not updated successfully. Please try again !");
		}
		return responseVo;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
	public ResponseVO deleteTopic(@PathVariable String id) {

		ResponseVO responseVo = new ResponseVO();
		try {
			topicService.deleteTopic(id);
			responseVo.setStatus(SUCCESS);
			responseVo.setMessage("Topic has been deleted successfully");
		} catch (Exception e) {
			responseVo.setStatus(FAILURE);
			responseVo.setMessage("Topic was not deleted. Got Exception [" + e.getMessage() + "]");
		}

		return responseVo;

	}
}
