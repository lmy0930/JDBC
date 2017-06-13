package com.learn.pojo;

import java.io.Serializable;

public class Person implements Serializable {
	private Long id;
	private String name;
	private String gender;
	private Integer age;
	private Passport passport;
	
	public Person(){}
	
	public Person(Long id, String name, String gender, Integer age, Passport passport) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.passport = passport;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", passport=" + passport
				+ "]";
	}

	
	
	
	
	
}
