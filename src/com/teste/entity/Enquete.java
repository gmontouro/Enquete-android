package com.teste.entity;

import java.util.List;

import com.google.gson.annotations.Expose;

public class Enquete {
	@Expose
	private int id;
	@Expose
	private String name;
	@Expose
	private String description;
	@Expose
	private String link;
	@Expose
	private Long created;
	@Expose
	private boolean active;
	@Expose
	private String image;
	@Expose
	private List<Questao> questions;
	
	public List<Questao> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Questao> questions) {
		this.questions = questions;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Long getCreated() {
		return created;
	}
	public void setCreated(Long created) {
		this.created = created;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
