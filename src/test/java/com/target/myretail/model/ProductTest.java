package com.target.myretail.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest 
public class ProductTest {

	Product obj = new Product(12345, "USD", (float) 25.49);
	
	@Test
	public void TestProductId(){
		assertEquals("ProductId should be equal", obj.getProductId(),12345);
	}
	@Test
	public void TestCurrencyCode(){
		assertEquals("CurrencyCode should be equal", obj.getCurrencyCode(),"USD");
	}
	@Test
	public void TestValue(){
		assertEquals("Value should be equal", obj.getValue(),(float) 25.49,0.1);
	}
	
}
