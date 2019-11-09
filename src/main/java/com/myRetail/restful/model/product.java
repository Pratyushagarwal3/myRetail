package com.myRetail.restful.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class product {
	
	int id;
	String name;
	current_price current_price;
	
	
	
	public product() {
		super();
	}

	public product(int id, String name, com.myRetail.restful.model.current_price current_price) {
		super();
		this.id = id;
		this.name = name;
		this.current_price = current_price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public current_price getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(current_price current_price) {
		this.current_price = current_price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void comp() {
		System.out.print("sfdsfsdfsd");
	}

	

}
