package com.upem.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class localisation {
	@Id @GeneratedValue
	private Integer id;
	private Double altitude;
	private Double longitude;
	private Date date;
	
	@ManyToOne
	@JoinColumn
	private Device deviceloca;
	
	
	
	public localisation() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Device getDeviceloca() {
		return deviceloca;
	}



	public void setDeviceloca(Device deviceloca) {
		this.deviceloca = deviceloca;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	@Override
	public String toString() {
		return "localisation [altitude=" + altitude + ", longitude=" + longitude + ", date=" + date + "]";
	}
	

}
