package com.target.myretail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDetail {

	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("current_price")
	private ProductPrice currentPrice;
	/**
	 * @return the currentPrice
	 */
	public ProductPrice getCurrentPrice() {
		return currentPrice;
	}
	/**
	 * @param currentPrice the currentPrice to set
	 */
	public void setCurrentPrice(ProductPrice currentPrice) {
		this.currentPrice = currentPrice;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
