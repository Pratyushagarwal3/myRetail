package com.myRetail.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.myRetail.restful.model.current_price;
import com.myRetail.restful.model.product;

@SpringBootApplication
public class MyRetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRetailApplication.class, args);
		
	}

}
