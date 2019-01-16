package com.publicis.sapient.api.training;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
/*import com.spring.training.course.Course;
import com.spring.training.domain.ResponseVO;
import com.spring.training.topic.Topic; */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseApiDataIntegrationTest {
/*
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void topicTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
	    Topic topic = mapper.readValue(new ClassPathResource("jsons/createTopic.json").getFile(),
				Topic.class);

		// *****************************************************************************************************************************
		// *****************************************************************************************************************************		
		// Testing POST :: /topics
		ResponseEntity<ResponseVO> response = restTemplate.postForEntity("/topics", topic, ResponseVO.class);
		ResponseVO responseVO = response.getBody();
		assertEquals("SUCCESS", responseVO.getStatus());
		assertNotNull(responseVO.getMessage());
		assertNotNull(responseVO.getData());

		// Testing GET :: /topics
		ResponseEntity<ResponseVO> response2 = restTemplate.getForEntity("/topics", ResponseVO.class);
		ResponseVO responseVO2 = response2.getBody();
		assertEquals("SUCCESS", responseVO2.getStatus());
		assertNotNull(responseVO2.getMessage());
		assertNotNull(responseVO2.getData());

		// Testing GET :: /topics/java
		ResponseEntity<ResponseVO> response3 = restTemplate.getForEntity("/topics/java", ResponseVO.class);
		ResponseVO responseVO3 = response3.getBody();
		assertEquals("SUCCESS", responseVO3.getStatus());
		assertNotNull(responseVO3.getMessage());
		assertNotNull(responseVO3.getData());
		// *****************************************************************************************************************************
		// *****************************************************************************************************************************		
				
		
		Course course = mapper.readValue(new ClassPathResource("jsons/createCourse.json").getFile(),
				Course.class);

		// *****************************************************************************************************************************
		// *****************************************************************************************************************************		
		// Testing POST :: /topics/java/courses
		ResponseEntity<ResponseVO> courseResponse = restTemplate.postForEntity("/topics/java/courses", course, ResponseVO.class);
		ResponseVO courseResponseVO = courseResponse.getBody();
		assertEquals("SUCCESS", courseResponseVO.getStatus());
		assertNotNull(courseResponseVO.getMessage());
		assertNotNull(courseResponseVO.getData());

		// Testing GET :: /topics/java/courses
		ResponseEntity<ResponseVO> courseResponse2 = restTemplate.getForEntity("/topics/java/courses", ResponseVO.class);
		ResponseVO courseResponseVO2 = courseResponse2.getBody();
		assertEquals("SUCCESS", courseResponseVO2.getStatus());
		assertNotNull(responseVO2.getMessage());
		assertNotNull(responseVO2.getData());

		// Testing GET :: /topics/java/courses/javafundamentals
		ResponseEntity<ResponseVO> courseResponse3 = restTemplate.getForEntity("/topics/java/courses/javafundamentals", ResponseVO.class);
		ResponseVO courseResponseVO3 = courseResponse3.getBody();
		assertEquals("SUCCESS", courseResponseVO3.getStatus());
		assertNotNull(courseResponseVO3.getMessage());
		assertNotNull(courseResponseVO3.getData());
		// *****************************************************************************************************************************
		// *****************************************************************************************************************************		

		
		
		
		
		
		
		/*
		
		// Testing D :: /topics/java
		HttpEntity<ResponseVO> response4 = restTemplate
				.getForEntity("/topics/java", ResponseVO.class);
		ResponseVO responseVO4 = response4.getBody();
		assertEquals("SUCCESS", responseVO4.getStatus());
		assertNotNull(responseVO4.getMessage());
		assertNotNull(responseVO4.getData());
*/

}
