package com.target.myretail.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductPriceTest {
	ProductPrice price = new ProductPrice();

	@Test
	public void TestProductPrice(){
		price.setCurrencyCode("USD");
		price.setValue((float) 25.49);
		
		assertEquals("CurrencyCode should be equal", price.getCurrencyCode(),"USD");
		assertEquals("Value should be equal", price.getValue(),(float) 25.49,0.1);
		
	}
}
