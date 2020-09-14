package com.jpawithhibernate.project.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpawithhibernate.project.JpawithHibernate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpawithHibernate.class)
public class SpringDataCourseRepositoryTest {
	@Autowired
	SpringDataCourseRepository repository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void findByIdWithCoursePresent() {
		Optional<Course> coursePresent = repository.findById(10002L);
		assertTrue(coursePresent.isPresent());
	}

	@Test
	public void findByIdWithCourseNotPresent() {
		Optional<Course> courseNotPresent = repository.findById(100000L);
		assertFalse(courseNotPresent.isPresent());
	}

	@Test
	public void playingWithSpringData() {
		Course saveCourse = repository.save(new Course("AWS in 100 steps"));
		logger.info("I want List Of Courses->{}", repository.findAll());
		saveCourse.setName("AWS in 100 steps - Updated");
		repository.save(saveCourse);
		logger.info("Is this Course Updated ->{}", repository.findAll());
		logger.info("Course Count-> {}", repository.count());
	}

	@Test
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page Content -> {}", firstPage.getContent());

		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Second Page Content ->{}", secondPage.getContent());

	}

	@Test
	public void findByNameAndId() {
		List<Course> findByNameAndId = repository.findByNameAndId("Microservices", 10002L);
		logger.info("findByNameAndId ->{}", findByNameAndId);
	}

}
