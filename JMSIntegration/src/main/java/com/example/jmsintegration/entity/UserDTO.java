package com.example.jmsintegration.entity;

import java.util.Set;

public class UserDTO {

	private String uemail;
	private String upassword;
	private String uname;
	private String urole;
	private Set<AddressDTO> uaddress;
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUrole() {
		return urole;
	}
	public void setUrole(String urole) {
		this.urole = urole;
	}
	public Set<AddressDTO> getUaddress() {
		return uaddress;
	}
	public void setUaddress(Set<AddressDTO> uaddress) {
		this.uaddress = uaddress;
	}
	@Override
	public String toString() {
		return "UserDTO [uemail=" + uemail + ", upassword=" + upassword + ", uname=" + uname + ", urole=" + urole
				+ ", uaddress=" + uaddress + "]";
	}
	/**
	 * @param uemail
	 * @param upassword
	 * @param uname
	 * @param urole
	 * @param uaddress
	 */
	public UserDTO(String uemail, String upassword, String uname, String urole, Set<AddressDTO> uaddress) {
		super();
		this.uemail = uemail;
		this.upassword = upassword;
		this.uname = uname;
		this.urole = urole;
		this.uaddress = uaddress;
	}
	/**
	 * 
	 */
	public UserDTO() {
		super();
	}
	
}
