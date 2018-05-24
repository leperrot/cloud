package org.iut.model;

import java.io.Serializable;

public class Account implements Serializable{

	private Long account;
	private String nom;
	private String prenom;
	private String risk;
	private float amount;
	
	public Account(Long account, String nom, String prenom, String risk, float amount) {
		this.account = account;
		this.nom = nom;
		this.prenom = prenom;
		this.risk = risk;
		this.amount = amount;
	}
	
	public Account() {}
	
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
}
