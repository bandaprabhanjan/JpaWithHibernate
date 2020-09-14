package com.jpawithhibernate.project;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jpawithhibernate.project.entity.Review;
import com.jpawithhibernate.project.entity.Student;
import com.jpawithhibernate.project.repository.Course;
import com.jpawithhibernate.project.repository.CourseRepository;
import com.jpawithhibernate.project.repository.StudentRepository;

@SpringBootApplication
//if we make any database changes then we should add 
@Transactional
public class JpawithHibernate implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpawithHibernate.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course courseFindById = courseRepository.findById(10002L);
		logger.info("Course 10001 ->{}", courseFindById);

		courseRepository.deleteById(10001L);

		courseRepository.save(new Course("Microservices With 30 Steps"));

		Course wantToUpdate = courseRepository.findById(10004L);
		wantToUpdate.setName("Angular-Updated");
		Course course = courseRepository.save(wantToUpdate);
		logger.info("Course Updated ->{}", course);
		courseRepository.playWithEntityManager();
		courseRepository.addHardCodedReviewsForCourse();
		List<Review> reviews = Arrays.asList(new Review("New Generic Code HandsOn", "1"),new Review("New Review", "5"));
		courseRepository.addReviewsForCourse(10003L, reviews);
	//	logger.info("CourseAndReviewRelationShip: ->{}", course );
		studentRepository.saveStudentPassportDetails();
		studentRepository.insertHardcodedStudentAndCourse();
		studentRepository.insertStudentAndCourse(new Student("RajKumar"), new Course("Java in 30 Days"));

	}

}
