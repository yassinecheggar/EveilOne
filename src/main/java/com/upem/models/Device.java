package com.upem.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Device {
	@Id @GeneratedValue
	private Integer id;
	private String nom;
	private String idf ;
	private String cle ;
	
	@JsonIgnore
	@OneToMany(mappedBy="datadevice")
	private List<DeviceData> deviceData;
	
	@JsonIgnore
	@OneToMany(mappedBy="deviceloca")
	private List<localisation> locali;
	
	public Device() {
		// TODO Auto-generated constructor stub
	}
	
	public List<DeviceData> getDeviceData() {
		return deviceData;
	}



	public void setDeviceData(List<DeviceData> deviceData) {
		this.deviceData = deviceData;
	}



	public List<localisation> getLocali() {
		return locali;
	}



	public void setLocali(List<localisation> locali) {
		this.locali = locali;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getIdf() {
		return idf;
	}

	public void setIdf(String idf) {
		this.idf = idf;
	}

	
	public String getCle() {
		return cle;
	}

	public void setCle(String cle) {
		this.cle = cle;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", nom=" + nom + ", idf=" + idf + ", clé=" + cle + "]";
	}
	
	
	
}
