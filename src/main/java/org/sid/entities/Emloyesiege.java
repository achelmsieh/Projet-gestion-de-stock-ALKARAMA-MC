package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Emloyesiege implements Serializable {

	
	
	@Id @GeneratedValue 
	private Long id_employerdesiege;
	private String Nom_employerdesiege;
	private String prenom_employerdesiege;
	private String mot_passe_employerdesiege;
	
	private String Description_employedesiege;
	@OneToMany (mappedBy="employer_siege")
	private Collection<Livraision> livraisons;
	
	
	
	
	public Emloyesiege() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Emloyesiege(String nom_employerdesiege, String prenom_employerdesiege, String mot_passe_employerdesiege,
	String coordonee_employersiege) {
		super();
		Nom_employerdesiege = nom_employerdesiege;
		this.prenom_employerdesiege = prenom_employerdesiege;
		this.mot_passe_employerdesiege = mot_passe_employerdesiege;
	
		this.Description_employedesiege=coordonee_employersiege;
	}
	public Long getId_employerdesiege() {
		return id_employerdesiege;
	}
	public void setId_employerdesiege(Long id_employerdesiege) {
		this.id_employerdesiege = id_employerdesiege;
	}
	public String getNom_employerdesiege() {
		return Nom_employerdesiege;
	}
	public void setNom_employerdesiege(String nom_employerdesiege) {
		Nom_employerdesiege = nom_employerdesiege;
	}
	public String getPrenom_employerdesiege() {
		return prenom_employerdesiege;
	}
	public void setPrenom_employerdesiege(String prenom_employerdesiege) {
		this.prenom_employerdesiege = prenom_employerdesiege;
	}
	public String getMot_passe_employerdesiege() {
		return mot_passe_employerdesiege;
	}
	public void setMot_passe_employerdesiege(String mot_passe_employerdesiege) {
		this.mot_passe_employerdesiege = mot_passe_employerdesiege;
	}

	
	@Override
	public String toString() {
		return "Emloyesiege [id_employerdesiege=" + id_employerdesiege + ", Nom_employerdesiege=" + Nom_employerdesiege
				+ ", prenom_employerdesiege=" + prenom_employerdesiege + ", mot_passe_employerdesiege="
				+ mot_passe_employerdesiege + ", date_creation_employe_siege=" + "]";
	}
	
	
	public String getCoordonee_employersiege() {
		return Description_employedesiege;
	}
	public void setCoordonee_employersiege(String coordonee_employersiege) {
		this.Description_employedesiege = coordonee_employersiege;
	}

}
