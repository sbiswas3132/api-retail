package com.target.myretail.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.myretail.model.ProductDetail;
import com.target.myretail.repository.ProductRepository;
import com.target.myretail.service.ProductDetailService;
import com.target.myretail.service.ProductPriceService;


@CrossOrigin
@RestController
public class ProductController {

	@Autowired
	private ProductPriceService productPriceService;
	
	@Autowired
	private ProductDetailService productDetailService;
	

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	private List<Map<String, Object>> productContentList = null;
	private static String title="";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/api/products/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Map> get(@PathVariable Number productId)
			throws RestClientException, ExecutionException {	


		// Get ProductDetail Data
		CompletableFuture<Map<String,Object>> detailFuture = CompletableFuture.supplyAsync(() -> {
			try {
				return productDetailService.getProductData(productId);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		});

		// Get ProductPrice data
		CompletableFuture<Map<String,Object>> priceFuture = CompletableFuture.supplyAsync(() -> {
			try {
				return productPriceService.get(productId);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		});
		// Put the futures in array
		List<CompletableFuture<Map<String, Object>>> futures = Arrays.asList(detailFuture, priceFuture);

		// Prepare to execute the threads
		CompletableFuture<Void> dpFutures = CompletableFuture
				.allOf(futures.toArray(new CompletableFuture[futures.size()]));

		// Prepare to combine the response from all the threads
		CompletableFuture<List<Map<String, Object>>> productContents = dpFutures.thenApply(result -> {
			return futures.stream().map(future -> future.join()).collect(Collectors.toList());
		});
		// Execute and Wait for all threads to get complete
		try {
			productContentList = productContents.get();
		} catch (InterruptedException | ExecutionException | NullPointerException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Map<String,Object> response = formatResponse(productId);

		// Update the LAWW url links with GN Taxonomy links
		return new ResponseEntity(response, HttpStatus.OK);
	
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/api/products/{productId}",  method = RequestMethod.PUT)
	public ResponseEntity get(@PathVariable Number productId, @RequestBody ProductDetail productDetail)
			throws RestClientException, ExecutionException {	
		return  productPriceService.update(productId, productDetail);
	}
	@ResponseBody
	@RequestMapping(value = "/api/load", method = RequestMethod.POST)
	public void load()
			throws RestClientException, ExecutionException {	
		
		System.out.println("load called");
		productPriceService.load();
	
	}
	@SuppressWarnings("unchecked")
	private Map<String,Object> formatResponse(Number productId){
		Map<String, Object> response = new HashMap<String,Object>();
		Map<String, Object> price = new HashMap<String,Object>();
		if(productContentList != null ){
			if(productContentList.get(0) != null && productContentList.get(0).get("product") != null ) {
				iterate((Map<String, Object>) productContentList.get(0).get("product"));
				response.put("id", productId);
				response.put("name", title);
				if(productContentList.get(1) != null){
					price.put("value", productContentList.get(1).get("value"));
					price.put("currency_code", productContentList.get(1).get("currencyCode"));
				}
				response.put("current_price", price);
			}
			
		}
		return response;
	}
	@SuppressWarnings("unchecked")
	public static void iterate(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
        	if(entry.getKey() == "title")
        		title = (String) entry.getValue();
            if (entry.getValue() instanceof Map) {
                iterate((Map<String, Object>) entry.getValue());
            } 
        }
    }
}
