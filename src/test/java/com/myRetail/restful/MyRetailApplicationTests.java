package com.myRetail.restful;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.PageAttributes.MediaType;
import java.util.Collections;
import java.util.Optional;

import org.hibernate.annotations.MetaValue;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.Assert;

import com.myRetail.restful.dbrepo.dbrepo;
import com.myRetail.restful.model.current_price;
import com.myRetail.restful.model.product;
import com.myRetail.restful.model.productprice;


@WebMvcTest
class MyRetailApplicationTests {
	
	@Autowired
	MockMvc mockmvc;
	
	@MockBean
	current_price cp;
	
	@MockBean
	retrieveProduct rp;

	@MockBean
	dbrepo dbrepo;

	@Test
	void getPriceTest() {
		
		productprice pp = new productprice(13860428, 13.49, "USD");
		
		Mockito.when(dbrepo.findById(Mockito.anyInt())).thenReturn(Optional.of(pp));
		
	try {
		MvcResult mvcResult = mockmvc.perform(
					MockMvcRequestBuilders.get("/products/13860428/price").accept(org.springframework.http.MediaType.APPLICATION_JSON)
					).andReturn();
		
		System.out.println(mvcResult.getResponse().getContentAsString());
		String expected = "{\"pid\":13860428,\"value\":13.49,\"currency_code\":\"USD\"}";
		Mockito.verify(dbrepo).findById(Mockito.anyInt());
		JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
		
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
				
			
	}
	
	@Test
	void getproductsTest()
	{
		try {
			MvcResult mvcResult = mockmvc.perform(
					MockMvcRequestBuilders.get("/products/13860428").accept(org.springframework.http.MediaType.APPLICATION_JSON)
					).andReturn();
			
			String Expected = "{\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}";
			System.out.println(mvcResult.getResponse().getContentAsString());
			
			JSONAssert.assertEquals(Expected, mvcResult.getResponse().getContentAsString(), false);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	void putPriceTest() { 
		
		productprice pp = new productprice(13860428, 13.49, "USD");
		Mockito.when(dbrepo.save(pp)).thenReturn(pp);
		String actual = "{\"pid\":13860428,\"value\":13.49,\"currency_code\":\"USD\"}";
		try {
			 RequestBuilder requestBuilder = MockMvcRequestBuilders
			            .put("/products/13860428")
			            .accept(org.springframework.http.MediaType.APPLICATION_JSON)
			            .characterEncoding("UTF-8")
			            .content(actual)
			            .contentType(org.springframework.http.MediaType.APPLICATION_JSON);

			    MvcResult result = mockmvc.perform(requestBuilder).andReturn();
			
			System.out.println(result.getResponse().getContentAsString());
			
			String Expected = "{\"pid\":13860428,\"value\":13.49,\"currency_code\":\"USD\"}";
			Mockito.verify(dbrepo).save(pp);
			JSONAssert.assertEquals(Expected, result.getResponse().getContentAsString(), false);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	void postPriceTest() { 
		
		productprice pp = new productprice(13860428, 13.49, "USD");
		Mockito.when(dbrepo.postPrice(pp.getPid(), pp.getValue(), pp.getCurrency_code())).thenReturn(1);
		String actual = "{\"pid\":13860428,\"value\":13.49,\"currency_code\":\"USD\"}";
		try {
			 RequestBuilder requestBuilder = MockMvcRequestBuilders
			            .post("/products/13860428")
			            .accept(org.springframework.http.MediaType.APPLICATION_JSON)
			            .characterEncoding("UTF-8")
			            .content(actual)
			            .contentType(org.springframework.http.MediaType.APPLICATION_JSON);

			    MvcResult result = mockmvc.perform(requestBuilder).andReturn();
			
			System.out.println(result.getResponse().getContentAsString());
			
			String Expected = "Inserted Successfully";
			Mockito.verify(dbrepo).postPrice(pp.getPid(), pp.getValue(), pp.getCurrency_code());
			assertEquals(Expected, result.getResponse().getContentAsString());

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	

}
