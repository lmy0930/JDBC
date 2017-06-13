package com.learn.pojo;

import java.io.Serializable;

public class Student implements Serializable {
	private Long id;
	private String name;
	private String address;
	private String gender;
	private Integer age;
	
	public Student(){}
	
	public Student(Long id, String name, String address, String gender, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.age = age;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", address=" + address + ", gender=" + gender + ", age=" + age
				+ "]";
	}
	
	
}
