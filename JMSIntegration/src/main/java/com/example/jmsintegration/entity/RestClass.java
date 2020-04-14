package com.example.jmsintegration.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestClass {

	private  long total_count;
    private  Boolean incomplete_results;
    private List<Item> items;
	public RestClass(@JsonProperty("total_count")int total_count,@JsonProperty("incomplete_results") Boolean incomplete_results,@JsonProperty("items") List<Item> items) {
		super();
		this.total_count = total_count;
		this.incomplete_results = incomplete_results;
		this.items = items;
	}
	public long getTotal_count() {
		return total_count;
	}
	public void setTotal_count(long total_count) {
		this.total_count = total_count;
	}
	public Boolean getIncomplete_results() {
		return incomplete_results;
	}
	public void setIncomplete_results(Boolean incomplete_results) {
		this.incomplete_results = incomplete_results;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "UserRepo [total_count=" + total_count + ", incomplete_results=" + incomplete_results + ", items="
				+ items + "]";
	}
	public RestClass() {
		super();
	}
}
