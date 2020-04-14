package com.example.jmsintegration.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

	private String login;
    private int id;
    private String htmlUrl;
    @JsonCreator
    public Item(@JsonProperty("login") String login, @JsonProperty("id") int id, @JsonProperty("html_url") String htmlUrl) {
        super();
        this.login = login;
        this.id = id;
        this.htmlUrl = htmlUrl;
    }
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHtmlUrl() {
		return htmlUrl;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}
	public Item() {
		super();
	}
	@Override
	public String toString() {
		return "User [login=" + login + ", id=" + id + ", htmlUrl=" + htmlUrl + "]";
	}
    

}
