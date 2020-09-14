package com.jpawithhibernate.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpawithhibernate.project.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeQueriesTest {
	@Autowired
	EntityManager entityManager;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void nativeQuery_basic() {
		Query createNativeQuery1 = entityManager.createNativeQuery("select * from Course", Course.class);
		List resultList = createNativeQuery1.getResultList();
		logger.info("Native Query ->{}", resultList);
	}

	@Test
	public void nativeQueryWithParameter() {

		Query createNativeQuery2 = entityManager.createNativeQuery("select * from Course where id = ?", Course.class);
		createNativeQuery2.setParameter(1, 10001L);
		List resultList1 = createNativeQuery2.getResultList();
		logger.info("nativeQueryWithParameter->{}", resultList1);

	}

	@Test
	public void nativeQueryWithNamedParameter() {

		Query createNativeQuery3 = entityManager.createNativeQuery("select * from Course where id = :id", Course.class);
		createNativeQuery3.setParameter("id", 10001L);
		List resultList2 = createNativeQuery3.getResultList();
		logger.info("nativeQueryWithParameter->{}", resultList2);

	}

	@Test
	@Transactional
	public void nativeQueryWithMassUpdate() {

		Query createNativeQuery3 = entityManager.createNativeQuery("update Course set updated_Last_Time = sysdate()",
				Course.class);
		int noOfRowsUpdated = createNativeQuery3.executeUpdate();
		logger.info("Number Of Rows Updated -> {}", noOfRowsUpdated);

	}

	@Test
	public void students_with_passports_certainPattern() {
		Query createNativeQuery = entityManager.createNativeQuery(
				"SELECT * FROM STUDENT A,PASSPORT B WHERE A.PASSPORT_ID = B.ID AND PNUMBER LIKE '%12345'",
				Student.class);
		List resultList = createNativeQuery.getResultList();
		logger.info("Prabhanjan Native Quey->{}", resultList);
	}

}
