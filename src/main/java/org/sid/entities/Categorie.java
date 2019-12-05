package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie implements Serializable{
	
	
	@Id @GeneratedValue
	private long id_categorie;
	private String nom_categorie;
	private String description_categorie;
	@OneToMany(mappedBy="categorie_article")
	private Collection<Articles> articles;

	
	
	
	public Categorie( String nom_categorie, String description_categorie) {
		super();
		
		this.nom_categorie = nom_categorie;
		this.description_categorie = description_categorie;
	}
	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}
		@Override
	public String toString() {
		return "Categorie [id_categorie=" + id_categorie + ", nom_categorie=" + nom_categorie
				+ ", description_categorie=" + description_categorie + "]";
	}
	public long getId_categorie() {
		return id_categorie;
	}
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}
	public String getNom_categorie() {
		return nom_categorie;
	}
	public void setNom_categorie(String nom_categorie) {
		this.nom_categorie = nom_categorie;
	}
	public String getDescription_categorie() {
		return description_categorie;
	}
	public void setDescription_categorie(String description_categorie) {
		this.description_categorie = description_categorie;
	}
	

}
