package org.iut.model;

import java.io.Serializable;

public class Loan implements Serializable{

	private String nom;
	private Long account;
	private float amount;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Long getAccount() {
		return account;
	}
	public void setAccount(Long account) {
		this.account = account;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public Loan(String nom, Long account, float amount) {
		this.nom = nom;
		this.account = account;
		this.amount = amount;
	}
	
	public Loan() {}
}
