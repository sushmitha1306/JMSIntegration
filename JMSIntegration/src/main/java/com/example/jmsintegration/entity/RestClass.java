package com.example.jmsintegration.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestClass {
    private List<Item> items;
	public RestClass(@JsonProperty("items") List<Item> items) {
		super();
		this.items = items;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "UserRepo [ items="
				+ items + "]";
	}
	public RestClass() {
		super();
	}
}
