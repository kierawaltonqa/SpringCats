package com.qa.springcats.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class CatDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;
	
	@NotNull
	private Integer age;
	private Float speechVolume;

	@ManyToOne
	private HouseDomain myHouse;
	
	//empty constructor
	public CatDomain() {
		super();
	}

	public CatDomain(Long id, String name,  Integer age, Float speechVolume, HouseDomain myHouse) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.speechVolume = speechVolume;
		this.myHouse = myHouse;
	}
	

	public CatDomain( String name, Integer age, Float speechVolume, HouseDomain myHouse) {
		super();
		this.name = name;
		this.age = age;
		this.speechVolume = speechVolume;
		this.myHouse = myHouse;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Float getSpeechVolume() {
		return speechVolume;
	}

	public void setSpeechVolume(Float speechVolume) {
		this.speechVolume = speechVolume;
	}

	public HouseDomain getMyHouse() {
		return myHouse;
	}

	public void setMyHouse(HouseDomain myHouse) {
		this.myHouse = myHouse;
	}
	
}
