package com.jpawithhibernate.project.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.jpawithhibernate.project.entity.Review;
import com.jpawithhibernate.project.entity.Student;

@NamedQueries(value = { @NamedQuery(name = "get-all-course", query = "select c from Course c"),
		@NamedQuery(name = "get-courses-like", query = "select c from Course c where name like '%MicroService' ") })
@Entity
public class Course {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	@CreationTimestamp
	private LocalDateTime createdDate;
	@UpdateTimestamp
	private LocalDateTime updatedLastTime;
	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<Review>();
	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<Student>();

	public List<Student> getStudent() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReviews(Review review) {
		this.reviews.add(review);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

	protected Course() {
		super();
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
