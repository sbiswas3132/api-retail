package com.target.myretail.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {

	@Id
	private String id;
	@Indexed
	private Number productId;
	private String currencyCode;
	private float value;
	
	public Product(Number productId, String currencyCode, float value) {
		super();
		this.productId = productId;
		this.currencyCode = currencyCode;
		this.value = value;
	}	
	/**
	 * @return the value
	 */
	public float getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(float value) {
		this.value = value;
	}
	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}
	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	/**
	 * @return the productId
	 */
	public Number getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Number productId) {
		this.productId = productId;
	}
	
	
}
