package com.learn.pojo;

import java.io.Serializable;

public class Passport implements Serializable {
	private Long id;
	private String bh;
	private Person person;
	
	public Passport(){}
	
	public Passport(Long id, String bh, Person person) {
		super();
		this.id = id;
		this.bh = bh;
		this.person = person;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", bh=" + bh +"]";
	}
	
	
	
}
