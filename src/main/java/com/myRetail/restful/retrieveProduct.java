package com.myRetail.restful;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myRetail.restful.model.current_price;
import com.myRetail.restful.model.product;
import com.myRetail.restful.model.productprice;

public class retrieveProduct {
	
	public product getProductDetails(int id)
	{
		productprice productprice = getProdcutPrice(id);
		if(productprice==null)
			return null;
		
		String resPName = getProductName(id);
		if(resPName==null)
			return null;
		
		product product = new product();
		current_price current_price = new current_price();
		current_price.setCurrency_code(productprice.getCurrency_code());
		current_price.setValue(productprice.getValue());
		
		product.setName(resPName);
		product.setCurrent_price(current_price);
		product.setId(productprice.getPid());
		
		return product;
		
	}
	
	public productprice getProdcutPrice(int id)
	{
		RestTemplate restTemplate = new RestTemplate();
		productprice productprice = restTemplate.getForObject("http://localhost:8080/products/"+id+"/price", productprice.class);
	
		if(productprice==null)
			return null;
		
		return productprice;
		
	}
	
	
	public String getProductName(int id) 
	{
		String resPName="",response="";
		
		try {
			
			URL url = new URL("https://redsky.target.com/v2/pdp/tcin/"+id+"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics");
			
			try {
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				BufferedReader in = new BufferedReader(
						  new InputStreamReader(conn.getInputStream()));
						String inputLine;
						StringBuffer content = new StringBuffer();
						while ((inputLine = in.readLine()) != null) {
						    content.append(inputLine);
						}
						in.close();
						conn.disconnect();
						response=content.toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
				return null;
			} 
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			JsonNode jsonNode = mapper.readTree(response);
			String resPID=jsonNode.get("product").get("available_to_promise_network").get("product_id").asText();
			resPName=jsonNode.get("product").get("item").get("product_description").get("title").asText();
			
			
			if(! resPID.equals(Integer.toString(id)))
				return null;
			
			return resPName;
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
