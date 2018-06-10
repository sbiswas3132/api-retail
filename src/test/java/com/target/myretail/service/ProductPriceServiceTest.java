package com.target.myretail.service;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.target.myretail.model.Product;
import com.target.myretail.model.ProductDetail;
import com.target.myretail.model.ProductPrice;
import com.target.myretail.repository.ProductRepository;

@SpringBootTest
public class ProductPriceServiceTest {

	@Mock
	private ProductRepository repo ;
	
	@InjectMocks
	private ProductPriceService service;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	@Test
	public void TestGet(){
		
		Product actual = new Product(12345, "USD", (float) 55.99);
		
		Mockito.when(repo.findByProductId(Matchers.anyLong())).thenReturn(actual);
		
		Map<String,Object> expected =  service.get(Matchers.anyLong());
		
		assertEquals("Product Id should be equal", expected.get("productId"), 12345);
		assertEquals("Currency Code should be equal", expected.get("currencyCode"), "USD");
		
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Test
	public void TestUpdate(){
		
		ProductDetail actual = new ProductDetail();
		Product product = new Product(12345, "USD", (float) 55.99);
		ProductPrice price = new ProductPrice();
		price.setCurrencyCode("USD");
		price.setValue((float) 25.49);
		actual.setCurrentPrice(price);
		actual.setId("12345");
		actual.setName("Blue Ray");
		
		Mockito.when(repo.findByProductId(Matchers.anyLong())).thenReturn(product);
		Mockito.when(repo.save(Matchers.any(Product.class))).thenReturn(product);
		
		
		ResponseEntity  expected =  service.update(12345,actual);
		assertEquals("Update should be OK", expected.getStatusCode(), HttpStatus.OK);
		
	}
	
}
