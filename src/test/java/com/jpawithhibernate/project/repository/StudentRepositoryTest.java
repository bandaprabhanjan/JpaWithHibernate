package com.jpawithhibernate.project.repository;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpawithhibernate.project.JpawithHibernate;
import com.jpawithhibernate.project.entity.Passport;
import com.jpawithhibernate.project.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpawithHibernate.class)
public class StudentRepositoryTest {
	@Autowired
	private EntityManager entityManager;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// Below test case is for Eager Fetch ==>we just queried on student class but it
	// is fetching passport details also.
	// Query: "select * from Student A Left Outer Join on Passport B where
	// A.passport_id = B.id
	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		Student student = entityManager.find(Student.class, 30001L);
		logger.info("Student Details ->{}", student);
		logger.info("paspport Details -> {}", student.getPassport());
		Passport passport = new Passport("A12345");
		passport.setPnumber("A12345 - Updated");
		student.setName("Ankit Updated");
		logger.info("Name updated->{}", student.getName());
		logger.info("Print passportId-> {}", passport.getPnumber());
	}

	@Test
	@Transactional
	public void retrievePassportAssociatedWithStudent() {
		Passport passport = entityManager.find(Passport.class, 20001L);
		logger.info("Passport Details: -> {}", passport);
		logger.info("Student Details: -> {}", passport.getStudent());
	}
	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = entityManager.find(Student.class, 30001L);
		logger.info("Student Information:->{}", student);
		logger.info("Student Info with Course: ->{}", student.getCourses());
	}
}
