package com.spring.training.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.training.domain.ResponseVO;
import com.spring.training.topic.Topic;

@RestController
public class CourseController {

	private static final String FAILURE = "FAILURE";
	private static final String SUCCESS = "SUCCESS";
	@Autowired
	CourseService courseService;

	@RequestMapping("/topics/{topicId}/courses")
	public ResponseVO getAllCourses(@PathVariable String topicId) {
		ResponseVO responseVo = new ResponseVO();
		List<Course> courses = courseService.getAllCourses(topicId);
		if (courses != null && courses.size() > 0) {
			responseVo.setStatus(SUCCESS);
			responseVo.setMessage("Courses have been found against the topic");
			responseVo.setData(courses);
		} else {
			responseVo.setStatus(FAILURE);
			responseVo.setMessage("Courses was not fetched successfully against the topic");
		}
		return responseVo;
	}

	@RequestMapping("/topics/{topicId}/courses/{courseId}")
	public ResponseVO getCourse(@PathVariable String topicId, @PathVariable String courseId) {
		ResponseVO responseVo = new ResponseVO();
		Course course = courseService.getCourse(courseId);
		if (course != null) {
			responseVo.setStatus(SUCCESS);
			responseVo.setMessage("Course have been found against the topic");
			responseVo.setData(course);
		} else {
			responseVo.setStatus(FAILURE);
			responseVo.setMessage("Course was not fetched successfully against the topic");
		}
		return responseVo;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses")
	public ResponseVO addCourse(@RequestBody Course course, @PathVariable String topicId) {
		ResponseVO responseVo = new ResponseVO();
		course.setTopic(new Topic(topicId, "", ""));
		Course returnCourse = courseService.addCourse(course);

		if (returnCourse != null) {
			responseVo.setStatus(SUCCESS);
			responseVo.setMessage("Course had been created successfully");
			responseVo.setData(returnCourse);
		} else {
			responseVo.setStatus(FAILURE);
			responseVo.setMessage("Course was not created successfully. Please try again !");
		}
		return responseVo;

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{topicId}/courses/{courseId}")
	public ResponseVO updateT(@RequestBody Course course, @PathVariable String topicId, @PathVariable String courseId) {
		ResponseVO responseVo = new ResponseVO();
		course.setTopic(new Topic(topicId, "", ""));
		Course returnCourse = courseService.updateCourse(course);

		if (returnCourse != null) {
			responseVo.setStatus(SUCCESS);
			responseVo.setMessage("Course had been updated successfully");
			responseVo.setData(returnCourse);
		} else {
			responseVo.setStatus(FAILURE);
			responseVo.setMessage("Course was not updated successfully. Please try again !");
		}
		return responseVo;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{topicId}/courses/{courseId}")
	public ResponseVO deleteCourse(@PathVariable String topicId, @PathVariable String courseId) {
		ResponseVO responseVo = new ResponseVO();
		try {
			courseService.deleteCourse(courseId);
			responseVo.setStatus(SUCCESS);
			responseVo.setMessage("Course has been deleted successfully");
		} catch (Exception e) {
			responseVo.setStatus(FAILURE);
			responseVo.setMessage("Course was not deleted. Got Exception [" + e.getMessage() + "]");
		}
		return responseVo;
	}
}
