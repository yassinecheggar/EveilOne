package com.upem.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Humidite {
	
	@Id @GeneratedValue
	private Integer id;
	private Double val;
	private Date date;
	
	
	
	public Humidite() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getVal() {
		return val;
	}

	public void setVal(Double val) {
		this.val = val;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		
		return "Humidite [val=" + val + ", date=" + date + "]";
		
	}
	

}
