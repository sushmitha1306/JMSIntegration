package com.example.jmsintegration.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	private String home;
	private String office;
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public Address(String home, String office) {
		super();
		this.home = home;
		this.office = office;
	}
	public Address() {
		super();
	}
	@Override
	public String toString() {
		return "Address [home=" + home + ", office=" + office + "]";
	}
	
}
