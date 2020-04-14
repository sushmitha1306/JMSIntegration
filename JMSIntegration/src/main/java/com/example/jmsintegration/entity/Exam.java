package com.example.jmsintegration.entity;

public class Exam {

	private String name;
	private String link;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @param name
	 * @param link
	 */
	public Exam(String name, String link) {
		super();
		this.name = name;
		this.link = link;
	}
	/**
	 * 
	 */
	public Exam() {
		super();
	}
	@Override
	public String toString() {
		return "Exam [name=" + name + ", link=" + link + "]";
	}
	
}
