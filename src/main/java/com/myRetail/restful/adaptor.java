package com.myRetail.restful;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myRetail.restful.dbrepo.dbrepo;
import com.myRetail.restful.model.current_price;
import com.myRetail.restful.model.product;
import com.myRetail.restful.model.productprice;

@RestController
public class adaptor {
	
	@Autowired
	dbrepo repo;
	
	
	@GetMapping("/products/{id}")
	public product getproducts(@PathVariable("id") int id)
	{
		
		return new retrieveProduct().getProductDetails(id);
	}
	
	
	@PostMapping("/products/{id}")
	public String postproducts(@RequestBody productprice productprice)
	{
		
		int res = repo.postPrice(productprice.getPid(),productprice.getValue(),productprice.getCurrency_code());
		if(res==1)
			return "Inserted Successfully";
		return "Failed";
	}
	
	
	@PutMapping("/products/{id}")
	public productprice putproducts(@RequestBody productprice productprice)
	{
		
		return repo.save(productprice);
		
	}
	
	@RequestMapping("/products/{id}/price")
	public Optional<productprice> productsprice(@PathVariable("id") int id)
	
	{
		return repo.findById(id);
	}
	
	

}
