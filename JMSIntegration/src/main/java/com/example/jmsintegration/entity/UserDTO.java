package com.example.jmsintegration.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class UserDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@Column(unique=true)
	private String email;
	private String password;
	@NotEmpty(message="Username is mandatory")
	@Column(unique=true)
	private String name;
	private String role;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Address> address;
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
	public Set<Address> getAddress() {
		return address;
	}
	public void setAddress(Set<Address> address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserDTO() {
		super();
	}
	public UserDTO(String email, String password, @NotEmpty(message = "Username is mandatory") String name, String role,
			Set<Address> address) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", name=" + name + ", role=" + role + ", address="
				+ address + "]";
	}
	
	
	
}
