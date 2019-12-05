package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Articles implements Serializable {
	
	
	
	@Id @GeneratedValue
	private Long id_article;
	 private String nom_article;
	 private double prix_unitaire_article;
	 private String description_article;
	 private String photo;
	 @OneToMany(mappedBy="article")
	 private List<Mouvementsdustock> tableauxdemouvementdeStock;
	@ManyToOne
	 @JoinColumn(name="idcategorie")
	 private Categorie categorie_article;
	@OneToMany(mappedBy="article")
	private List<LignedecommandeSuperviseur> tatbleaux_de_comande_superviseur;
	@OneToMany(mappedBy="article")
	private Collection<Lignedelivraision> lignedelivraison;
	@OneToMany(mappedBy="article")
	private List<AjoutteStock> tableauxdajoute;
	 public Articles() {
		super();
		// TODO Auto-generated constructor stub
	 					}
	


	


	public Articles(String nom_article, double prix_unitaire_article, String description_article, String photo,
			Categorie categorie_article) {
		super();
		this.nom_article = nom_article;
		this.prix_unitaire_article = prix_unitaire_article;
		this.description_article = description_article;
		this.photo = photo;
		this.categorie_article = categorie_article;
	}






	//getter and setter
	public String getNom_article() {
		return nom_article;
	}
	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}
	public double getPrix_unitaire_article() {
		return prix_unitaire_article;
	}
	public void setPrix_unitaire_article(double prix_unitaire_article) {
		this.prix_unitaire_article = prix_unitaire_article;
	}
	public String getDescription_article() {
		return description_article;
	}
	public void setDescription_article(String description_article) {
		this.description_article = description_article;
	}
	public Categorie getId_categorie() {
		return categorie_article;
	}
	public void setId_categorie(Categorie categorie) {
		this.categorie_article = categorie;
	}
	@Override
	public String toString() {
		return "Articles [id_article=" + id_article + ", nom_article=" + nom_article + ", prix_unitaire_article="
				+ prix_unitaire_article + ", description_article=" + description_article + ", categorie="
				+ categorie_article + "]";
	}
	public Long getId_article() {
		return id_article;
	}
	public void setId_article(Long id_article) {
		this.id_article = id_article;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public List<Mouvementsdustock> getTableauxdemouvementdeStock() {
		return tableauxdemouvementdeStock;
	}


	public void setTableauxdemouvementdeStock(List<Mouvementsdustock> tableauxdemouvementdeStock) {
		this.tableauxdemouvementdeStock = tableauxdemouvementdeStock;
	}


	public Categorie getCategorie_article() {
		return categorie_article;
	}


	public void setCategorie_article(Categorie categorie_article) {
		this.categorie_article = categorie_article;
	}


	public List<LignedecommandeSuperviseur> getTatbleaux_de_comande_superviseur() {
		return tatbleaux_de_comande_superviseur;
	}


	public void setTatbleaux_de_comande_superviseur(List<LignedecommandeSuperviseur> tatbleaux_de_comande_superviseur) {
		this.tatbleaux_de_comande_superviseur = tatbleaux_de_comande_superviseur;
	}


	public Collection<Lignedelivraision> getLignedelivraison() {
		return lignedelivraison;
	}


	public void setLignedelivraison(Collection<Lignedelivraision> lignedelivraison) {
		this.lignedelivraison = lignedelivraison;
	}
}
