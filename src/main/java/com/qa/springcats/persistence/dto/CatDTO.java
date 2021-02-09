package com.qa.springcats.persistence.dto;

public class CatDTO {

	private Long id;

	private String name;
	private Integer age; //protecting
	private Float speechVolume;
	
	//private HouseDTO myHouse;
	//causes a recursive error (because house shows cat list)

	public CatDTO(Long id, String name, Float speechVolume) {
		super();
		this.id = id;
		this.name = name;
		this.speechVolume = speechVolume;
	}

	public CatDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public Float getSpeechVolume() {
		return speechVolume;
	}

	public void setSpeechVolume(Float speechVolume) {
		this.speechVolume = speechVolume;
	}

}
