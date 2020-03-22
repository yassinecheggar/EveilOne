package com.upem.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class DeviceData {
	
	@Id @GeneratedValue
	private Integer id;
	private String temp;
	private String hum;
	private String rssi;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Device datadevice;
	
	private Date date;
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Antenne antenne;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getHum() {
		return hum;
	}
	public void setHum(String hum) {
		this.hum = hum;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Antenne getAntenne() {
		return antenne;
	}
	public void setAntenne(Antenne antenne) {
		this.antenne = antenne;
	}
	public Device getDatadevice() {
		return datadevice;
	}
	public void setDatadevice(Device datadevice) {
		this.datadevice = datadevice;
	}
	
	public String getRssi() {
		return rssi;
	}
	public void setRssi(String rssi) {
		this.rssi = rssi;
	}
	
	@Override
	public String toString() {
		return "DeviceData [id=" + id + ", temp=" + temp + ", hum=" + hum + ", datadevice=" + datadevice + ", date="
				+ date + ", antenne=" + antenne + "]";
	}
	

}
