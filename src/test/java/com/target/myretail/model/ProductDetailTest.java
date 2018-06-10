package com.target.myretail.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest 
public class ProductDetailTest {
	
	ProductDetail detail = new ProductDetail();
	ProductPrice price = new ProductPrice();

	@Test
	public void TestProductDetail(){
	
		price.setCurrencyCode("USD");
		price.setValue((float) 25.49);
		detail.setCurrentPrice(price);
		detail.setId("12345");
		detail.setName("Blue Ray");
		
		assertEquals("Name should be equal", detail.getName(),"Blue Ray");
		assertEquals("Id should be equal", detail.getId(),"12345");
		assertEquals("Price should be equal", detail.getCurrentPrice().getClass(),ProductPrice.class);
	}
		
}
