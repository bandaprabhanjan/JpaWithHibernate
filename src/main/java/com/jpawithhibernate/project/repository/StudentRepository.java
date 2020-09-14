package com.jpawithhibernate.project.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpawithhibernate.project.entity.Passport;
import com.jpawithhibernate.project.entity.Student;

@Repository
public class StudentRepository {
	@Autowired
	EntityManager entityManager;

	public void saveStudentPassportDetails() {
		Passport passport = new Passport("E12345");
		entityManager.persist(passport);
		Student student = new Student("Ranga");
		student.setPassport(passport);
		entityManager.persist(student);
	}

	public void findById(Long id) {
		entityManager.find(Student.class, id);
	}

	@Transactional
	public void insertHardcodedStudentAndCourse() {
		Student student = new Student("Jack");
		Course cousre = new Course("Management Course");
		entityManager.persist(student);
		entityManager.persist(cousre);
		student.addCourses(cousre);
		cousre.addStudent(student);
		// we should persist from owning side
		entityManager.persist(student);

	}
	
	public void insertStudentAndCourse(Student student, Course course) {
		student.addCourses(course);
		course.addStudent(student);
		entityManager.persist(student);
		entityManager.persist(course);
	}
}
