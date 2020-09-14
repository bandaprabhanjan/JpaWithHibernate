package com.jpawithhibernate.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCourseRepository extends JpaRepository<Course, Long> {
	List<Course> findByNameAndId(String name, Long id);

	/*
	 * List<Course> countByName(String name);
	 * 
	 * List<Course> DeleteByName(String name);
	 * 
	 * @Query("select c from course c where name like '%microservices'")
	 * List<Course> courseWithName();
	 */

}
