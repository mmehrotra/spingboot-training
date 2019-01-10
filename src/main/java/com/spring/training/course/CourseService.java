package com.spring.training.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	CourseRespository courseRespository;

	public List<Course> getAllCourses(String topicId) {
		List<Course> topics = new ArrayList<Course>();
		courseRespository.findByTopicId(topicId).forEach(topics::add);
		return topics;
	}

	public Course getCourse(String id) {
		return courseRespository.findOne(id);
	}

	public Course addCourse(Course course) {
		Course returnCourse = courseRespository.save(course);
		return returnCourse;
	}

	public Course updateCourse(Course course) {
		Course returnCourse = courseRespository.save(course);
		return returnCourse;
	}

	public void deleteCourse(String id) {
		courseRespository.delete(id);
	}

}
