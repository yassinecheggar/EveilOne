package com.upem.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Antenne {

	@Id @GeneratedValue
	private Integer id; 
	private String name;
	private double leng;
	private double alt;
	private String status;
	
	@OneToMany(mappedBy="antenne")
	private List<DeviceData>datas;
	
	
	
	public Antenne() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<DeviceData> getDatas() {
		return datas;
	}


	public void setDatas(List<DeviceData> datas) {
		this.datas = datas;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLeng() {
		return leng;
	}
	public void setLeng(double leng) {
		this.leng = leng;
	}
	public double getAlt() {
		return alt;
	}
	public void setAlt(double alt) {
		this.alt = alt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Antenne [id=" + id + ", name=" + name + ", leng=" + leng + ", alt=" + alt + ", status=" + status + "]";
	}
	
	
}
