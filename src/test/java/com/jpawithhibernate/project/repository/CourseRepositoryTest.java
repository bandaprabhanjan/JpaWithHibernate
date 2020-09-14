package com.jpawithhibernate.project.repository;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpawithhibernate.project.JpawithHibernate;
import com.jpawithhibernate.project.entity.Review;

//Launch spring context in UnitTest==>@RunWith(SpringRunner.class)
@RunWith(SpringRunner.class)
//launch entire JpawithHiberante Application
@SpringBootTest(classes = JpawithHibernate.class)
@Transactional
class CourseRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CourseRepository repository;
	@Autowired
	EntityManager em;

	@Test
	public void findById_basic() {
		Course courseById = repository.findById(10002L);
		assertEquals("Microservices", courseById.getName());
	}

	@Test
	// it will get the data back
	@DirtiesContext
	public void deleteById_Basic() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}

	@Test
	public void save() {
//		get the course
		Course course = repository.findById(10003L);
		assertEquals("JavaScript", course.getName());
		// update details
		course.setName("JavaScript -Updated");
		repository.save(course);
		// check the value
		Course course1 = repository.findById(10003L);
		assertEquals("JavaScript -Updated", course1.getName());

	}

	@Test
	@DirtiesContext
	public void entityManagerTest() {
		repository.playWithEntityManager();
	}

	@Test
	@Transactional
	public void retrieveReviewsForCourse() {
		Course course = repository.findById(10003L);
		logger.info("Reviews for Course {}", course.getReviews());
	}

	@Test
	@Transactional
	public void retrieveCourseForReview() {
		Review review = em.find(Review.class, 40001L);
		logger.info("Course for Review {}", review.getCourse());
	}

}
