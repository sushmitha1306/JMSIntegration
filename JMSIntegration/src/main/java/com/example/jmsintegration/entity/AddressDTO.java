package com.example.jmsintegration.entity;

public class AddressDTO {

	private int aid;
	private String atype;
	private String uaddr;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAtype() {
		return atype;
	}
	public void setAtype(String atype) {
		this.atype = atype;
	}
	public String getUaddr() {
		return uaddr;
	}
	public void setUaddr(String uaddr) {
		this.uaddr = uaddr;
	}
	@Override
	public String toString() {
		return "AddressDTO [aid=" + aid + ", atype=" + atype + ", uaddr=" + uaddr + "]";
	}
	/**
	 * @param aid
	 * @param atype
	 * @param uaddr
	 */
	public AddressDTO(int aid, String atype, String uaddr) {
		super();
		this.aid = aid;
		this.atype = atype;
		this.uaddr = uaddr;
	}
	/**
	 * 
	 */
	public AddressDTO() {
		super();
	}
	
}
