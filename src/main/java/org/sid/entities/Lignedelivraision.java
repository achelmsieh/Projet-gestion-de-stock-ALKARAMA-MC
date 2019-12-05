package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Lignedelivraision implements Serializable {

	
	
	
	@Id @GeneratedValue
	private Long id_LignedeLivraision;
   
	@ManyToOne
	@JoinColumn(name="id_livraison")
	private Livraision Livraision;
	
	@ManyToOne
	@JoinColumn(name="idarticle")
	private Articles article;
	
	private int quantite;

	public Lignedelivraision() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lignedelivraision(org.sid.entities.Livraision livraision, Articles article ,int quantite) {
		super();
		this.quantite=quantite;
		Livraision = livraision;
		this.article = article;
	}

	public Livraision getLivraision() {
		return Livraision;
	}

	public void setLivraision(Livraision livraision) {
		Livraision = livraision;
	}

	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Lignedelivraision [id_LignedeLivraision=" + id_LignedeLivraision + ", Livraision=" + Livraision
				+ ", article=" + article + "]";
	}

	

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
}
	
	
	
