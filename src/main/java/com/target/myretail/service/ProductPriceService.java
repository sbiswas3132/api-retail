package com.target.myretail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.target.myretail.model.Product;
import com.target.myretail.model.ProductDetail;
import com.target.myretail.repository.ProductRepository;

@Component
public class ProductPriceService {
	
	@Autowired
	private ProductRepository repo;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceService.class);

	
	public Map<String, Object> get(Number productId){
		Product product = repo.findByProductId(productId.longValue());
		return convertToMap(product);
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity update(Number productId, ProductDetail productDetail){
		Product p = repo.findByProductId(productId.longValue());
		p.setValue(productDetail.getCurrentPrice().getValue());
		try{
		Product product = repo.save(p);
		return (product != null) ?  new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}		
	}
	private Map<String, Object> convertToMap(Product product){
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> map = oMapper.convertValue(product, Map.class);
		return map;
		
	}
	public void load(){
		MongoOperations mongoOperation = (MongoOperations) new MongoTemplate(new MongoClient("127.0.0.1"),"productdb");
		
		Product product1 = new Product(15117729, "USD", (float) 13.49);
		Product product2 = new Product(16483589, "USD", (float) 14.49);
		Product product3 = new Product(16696652, "USD", (float) 15.49);
		Product product4 = new Product(16752456, "USD", (float) 16.49);
		Product product5 = new Product(16752456, "USD", (float) 17.49);
		Product product6 = new Product(13860428, "USD", (float) 17.49);
				
		
		List<Product> list = new ArrayList<Product>();
		list.add(product1);
		list.add(product2);
		list.add(product3);
		list.add(product4);
		list.add(product5);
		list.add(product6);
		mongoOperation.insert(list, Product.class);
		mongoOperation.findAll(Product.class).forEach(product -> {
			System.out.println(product.getProductId());
		});
	}
}
