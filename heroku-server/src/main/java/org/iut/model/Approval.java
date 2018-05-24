package org.iut.model;

import java.io.Serializable;

public class Approval implements Serializable{
	
	private Long id;
	private Long account;
	private String nom;
	private String response;
	
	public Approval(Long id, Long account, String nom, String response) {
		this.id = id;
		this.account = account;
		this.nom = nom;
		this.response = response;
	}
	
	public Approval() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccount() {
		return account;
	}
	public void setAccount(Long account) {
		this.account = account;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}
