package io.springtraining.springbootstarter.course;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CourseRespository extends CrudRepository<Course, String> {
	
	
	// The implementation of this method will be done automatically by the Spring JPA. No need to implement this method.
	// Only thing which needs to be taken care of is use findBy property-name
	public List<Course> findByName(String name);
	
	// Here topic is a class instance, hence we are referring it with topic and its field Id. [Camel case is required]
	public List<Course> findByTopicId(String topicId);
	

}
