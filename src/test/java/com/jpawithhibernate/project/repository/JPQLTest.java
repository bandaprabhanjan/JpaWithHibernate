package com.jpawithhibernate.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpawithhibernate.project.JpawithHibernate;
import com.jpawithhibernate.project.entity.Passport;
import com.jpawithhibernate.project.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpawithHibernate.class)
public class JPQLTest {
	@Autowired
	EntityManager entityManager;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void jpql_findAll() {
		Query query = entityManager.createQuery("select c from Course c");
		logger.info("select c from Course c -> {}", query.getResultList());

	}

	@Test
	public void jpql_findAllNamedQuery() {
		Query query = entityManager.createNamedQuery("get-all-course");
		logger.info("select c from Course c -> {}", query.getResultList());

	}

	@Test
	public void jpql_typedQuery() {
		Query createQuery = entityManager.createQuery("select c from Course c", Course.class);
		logger.info("select c from Course c TypedQuery->{}", createQuery.getResultList());
	}

	@Test
	public void jpql_whereTypedQuery() {
		TypedQuery<Course> createQueryWhere = entityManager
				.createQuery("select c from Course c where name like '%Microservices' ", Course.class);
		logger.info("JPQL Where Condition->{}", createQueryWhere.getResultList());
	}

	@Test
	public void jpql_whereNamedQuery() {
		TypedQuery<Course> createQueryWhere = entityManager.createNamedQuery("get-courses-like", Course.class);
		logger.info("JPQL Where Condition->{}", createQueryWhere.getResultList());
	}

	@Test
	public void jpql_courses_without_students() {
		Query coursesWithoutStudents = entityManager.createQuery("select c from Course c where c.students is empty",
				Course.class);
		List<Course> resultList = coursesWithoutStudents.getResultList();
		logger.info("Courses without Students ->{}", resultList);
	}

	@Test
	public void jpql_courses_atleast2Students() {
		TypedQuery<Course> query = entityManager.createQuery("select c from Course c where size(c.students) >= 2",
				Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Courses atleast 2 students->{}", resultList);
	}
	@Test
	public void jpql_students_with_passports_certainPattern() {
		TypedQuery<Student> typedQuery = entityManager
				.createQuery("select s from Student s where s.passport.pnumber Like '%0000%' ", Student.class);
		List<Student> resultList = typedQuery.getResultList();
		logger.info("Students with Passport in a certain pattern ->{}", resultList);
	}

}
