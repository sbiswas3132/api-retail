package com.target.myretail.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.target.myretail.resource.TargetResourceMap;

@SpringBootTest
public class ProductDetailServiceTest {

	@Mock
	private TargetResourceMap resourceMap;
	
	@Mock
	private RestTemplate template;
	
	@InjectMocks
	private ProductDetailService service;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Test
	public void TestGetProductData() {
		
		Map<String,Object> product = new HashMap<String,Object>();
		product.put("product","product map");
		
		Mockito.when(resourceMap.getUri()).thenReturn("http://www.target.com");
		Mockito.when(resourceMap.getParams()).thenReturn("some params");
		Mockito.when(template.getForObject(Matchers.anyString(), Matchers.<Class<Map>>any(), Matchers.<HttpEntity<?>>any())).thenReturn(product);
		
		Map<String,Object> obj = service.getProductData(12345);
		
		assertEquals("Product Data should be equal", obj.get("product"),"product map");
		

		
	}
	
}
