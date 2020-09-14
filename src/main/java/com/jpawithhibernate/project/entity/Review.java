package com.jpawithhibernate.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.jpawithhibernate.project.repository.Course;


@Entity
public class Review {
	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private String rating;
	//Here Review Side Relationship is owning
	@ManyToOne
	private Course course;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Review(String description, String rating) {
		super();
		this.description = description;
		this.rating = rating;
	}

	protected Review() {
		super();
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", description=" + description + ", rating=" + rating + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

}
