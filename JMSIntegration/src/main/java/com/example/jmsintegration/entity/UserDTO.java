package com.example.jmsintegration.entity;

import java.util.Set;

public class UserDTO {

	private String email;
	private String password;
	private String name;
	private String role;
	private Set<Address> address;
	/**
	 * 
	 */
	public UserDTO() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Set<Address> getAddress() {
		return address;
	}
	public void setAddress(Set<Address> address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", password=" + password + ", name=" + name + ", role=" + role + ", address="
				+ address + "]";
	}
	/**
	 * @param email
	 * @param password
	 * @param name
	 * @param role
	 * @param address
	 */
	public UserDTO(String email, String password, String name, String role, Set<Address> address) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
		this.address = address;
	}
	

}
