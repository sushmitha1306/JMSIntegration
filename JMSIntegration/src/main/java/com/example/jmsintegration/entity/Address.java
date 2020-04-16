package com.example.jmsintegration.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	private int id;
	private String type;
	private String addr;
	@Override
	public String toString() {
		return "Address [id=" + id + ", type=" + type + ", addr=" + addr + "]";
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * @param id
	 * @param type
	 * @param address
	 */
	public Address(int id, String type, String addr) {
		super();
		this.id = id;
		this.type = type;
		this.addr = addr;
	}
	/**
	 * 
	 */
	public Address() {
		super();	}
		
}
