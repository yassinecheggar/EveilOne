package com.upem.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id @GeneratedValue
	private Integer id;
	private String nom;
	private String prenom;
	private String mail;
	private String mdp;
	
	
	@JsonIgnore
	@ManyToMany (cascade = {CascadeType.ALL})
	@Column(unique = false,nullable = true)
	private List<Device> UserDevices = new ArrayList<Device>(); 
	
	@JsonIgnore
	@ManyToMany (cascade = {CascadeType.ALL})
	@Column(unique = false,nullable = true)
	private List<Service> userServices = new ArrayList<Service>(); 
	
	
	public User() {
		
	}
	
	public List<Device> getUserDevices() {
		return UserDevices;
	}

	public void setUserDevices(List<Device> userDevices) {
		UserDevices = userDevices;
	}

	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Service> getUserServices() {
		return userServices;
	}

	public void setUserServices(List<Service> userServices) {
		this.userServices = userServices;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", mdp=" + mdp + "]";
	}
	
	
	
	
}
