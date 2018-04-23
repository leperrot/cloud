package org.iut.cloud.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Account implements Serializable{

	private @Id Long account;
	private String nom;
	private String prenom;
	private String risk;
	
	public Account(Long account, String nom, String prenom, String risk) {
		this.account = account;
		this.nom = nom;
		this.prenom = prenom;
		this.risk = risk;
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
}
