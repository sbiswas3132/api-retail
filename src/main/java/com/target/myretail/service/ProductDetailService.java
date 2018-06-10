package com.target.myretail.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.target.myretail.resource.TargetResourceMap;

@Component
public class ProductDetailService {

	@Autowired
	private TargetResourceMap resource;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> getProductData(Number productId) {

		HttpEntity<String> entity = createRequestEntity();
		final StringBuilder resourcePath = new StringBuilder(resource.getUri()).append(productId).append("?").append(resource.getParams());
		return restTemplate.getForObject(resourcePath.toString(), Map.class, entity);
	}
	

	private HttpEntity<String> createRequestEntity() {
		return new HttpEntity<String>(new HttpHeaders());
	}	

}
