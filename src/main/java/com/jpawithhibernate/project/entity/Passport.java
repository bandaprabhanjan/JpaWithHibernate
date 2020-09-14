package com.jpawithhibernate.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {
	@Id
	@GeneratedValue
	private Long id;
	private String pnumber;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Passport(String pnumber) {
		super();
		this.pnumber = pnumber;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", pnumber=" + pnumber + "]";
	}

	public Passport() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getPnumber() {
		return pnumber;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

}
