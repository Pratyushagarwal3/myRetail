package com.myRetail.restful.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class productprice {
	
	@Id
	int pid;
	double value;
	String currency_code;
	
	public productprice() {
		super();
	}
	public productprice(int pid, double value, String currency_code) {
		super();
		this.pid = pid;
		this.value = value;
		this.currency_code = currency_code;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency_code == null) ? 0 : currency_code.hashCode());
		result = prime * result + pid;
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		productprice other = (productprice) obj;
		if (currency_code == null) {
			if (other.currency_code != null)
				return false;
		} else if (!currency_code.equals(other.currency_code))
			return false;
		if (pid != other.pid)
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	@Override
	public String toString() {
		return "productprice [pid=" + pid + ", value=" + value + ", currency_code=" + currency_code + "]";
	}

}
