package com.jpawithhibernate.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jpawithhibernate.project.entity.Review;

@Repository
public class CourseRepository {
	@Autowired
	EntityManager entityManager;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Course findById(Long id) {
		return entityManager.find(Course.class, id);
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		entityManager.remove(course);
	}

	public Course save(Course course) {
		if (course.getId() == null) {
			// save
			entityManager.persist(course);
		} else {
			// update
			entityManager.merge(course);
		}
		return course;
	}

	public void playWithEntityManager() {
		Course newCourse = new Course("Web Service in 100 steps");
		entityManager.persist(newCourse);
		newCourse.setName("Web Service in 100 steps - Updated");
		entityManager.flush();
		Course course2 = findById(10003L);
		course2.setName("JavaScript - Updated");
//		newCourse.setName(null);
	}

	public void addHardCodedReviewsForCourse() {
		// 1)Get the course

		Course course = findById(10003L);

		// 2)add 2 reviews to it

		Review review1 = new Review("Really,Nice Course", "5");
		Review review2 = new Review("Bad Course", "2");

		// 3)setting the relationship

		course.addReviews(review1);
		course.addReviews(review2);

		review1.setCourse(course);
		review2.setCourse(course);

		// 4)save it to database

		entityManager.persist(review1);
		entityManager.persist(review2);

	}

	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		Course course = entityManager.find(Course.class, courseId);
		for (Review review : reviews) {
			// setting the relationship
			course.addReviews(review);
			review.setCourse(course);
			//owning side we should persist
			entityManager.persist(review);

		}
	}

}
