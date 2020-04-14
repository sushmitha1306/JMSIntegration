package com.example.jmsintegration.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	private int id;
	private String type;
	private String address;
	@Override
	public String toString() {
		return "Address [id=" + id + ", type=" + type + ", address=" + address + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @param id
	 * @param type
	 * @param address
	 */
	public Address(int id, String type, String address) {
		super();
		this.id = id;
		this.type = type;
		this.address = address;
	}
	/**
	 * 
	 */
	public Address() {
		super();	}
		
}
