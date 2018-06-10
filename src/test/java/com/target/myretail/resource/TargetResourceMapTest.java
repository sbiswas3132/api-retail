package com.target.myretail.resource;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest 
public class TargetResourceMapTest {

	TargetResourceMap resource = new TargetResourceMap();
	
	@Test
	public void TestTargetURI(){
		resource.setUri("http://someuri");
		assertEquals("URI should be equal", resource.getUri(),"http://someuri");
	}
	@Test
	public void TestTargetParams(){
		resource.setParams("someparams");
		assertEquals("CurrencyCode should be equal", resource.getParams(),"someparams");
	}
}
