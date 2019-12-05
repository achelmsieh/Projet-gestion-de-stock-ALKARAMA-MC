package org.sid.entities;

import java.io.Serializable;
import java.net.PasswordAuthentication;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Superviseur implements Serializable {
	
	
	@Id @GeneratedValue
	private Long   id_superviseur;
	private String nom_superviseur;
	private String prenom_superviseur;
	private String mot_passe_superviseur;
	@OneToMany(mappedBy="superviseur")
	private List<CommandeSuperviseur> commandes_superviseur;
	

	@Temporal(TemporalType.DATE)
	private java.util.Date date_creation_superviseur;
	private String description_superviseur;
	@OneToMany (mappedBy="superviseur")
	private List<Livraision> livraisons;
	public Superviseur(String nom_superviseur, String prenom_superviseur, String mot_passe_superviseur,
			List<CommandeSuperviseur> commandes_superviseur, java.util.Date date_creation_superviseur,
			String description_superviseur, List<Livraision> livraisons) {
		super();
		this.nom_superviseur = nom_superviseur;
		this.prenom_superviseur = prenom_superviseur;
		this.mot_passe_superviseur = mot_passe_superviseur;
		this.commandes_superviseur = commandes_superviseur;
		this.date_creation_superviseur = date_creation_superviseur;
		this.description_superviseur = description_superviseur;
		this.livraisons = livraisons;
	}
	public Superviseur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId_superviseur() {
		return id_superviseur;
	}
	public void setId_superviseur(Long id_superviseur) {
		this.id_superviseur = id_superviseur;
	}
	public String getNom_superviseur() {
		return nom_superviseur;
	}
	public void setNom_superviseur(String nom_superviseur) {
		this.nom_superviseur = nom_superviseur;
	}
	public String getPrenom_superviseur() {
		return prenom_superviseur;
	}
	public void setPrenom_superviseur(String prenom_superviseur) {
		this.prenom_superviseur = prenom_superviseur;
	}
	public String getMot_passe_superviseur() {
		return mot_passe_superviseur;
	}
	public void setMot_passe_superviseur(String mot_passe_superviseur) {
		this.mot_passe_superviseur = mot_passe_superviseur;
	}
	public List<CommandeSuperviseur> getCommandes_superviseur() {
		return commandes_superviseur;
	}
	public void setCommandes_superviseur(List<CommandeSuperviseur> commandes_superviseur) {
		this.commandes_superviseur = commandes_superviseur;
	}
	public java.util.Date getDate_creation_superviseur() {
		return date_creation_superviseur;
	}
	public void setDate_creation_superviseur(java.util.Date date_creation_superviseur) {
		this.date_creation_superviseur = date_creation_superviseur;
	}
	public String getDescription_superviseur() {
		return description_superviseur;
	}
	public void setDescription_superviseur(String description_superviseur) {
		this.description_superviseur = description_superviseur;
	}
	public List<Livraision> getLivraisons() {
		return livraisons;
	}
	public void setLivraisons(List<Livraision> livraisons) {
		this.livraisons = livraisons;
	}

	 
	
	
	
}