package org.sid.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Livraision implements Serializable{

	
	
	
	@Id @GeneratedValue
	private Long id_Livraision;
	
	@ManyToOne
	@JoinColumn(name="id_superviseur")
	private Superviseur   superviseur;
	
	@OneToMany(mappedBy="Livraision")
	private Collection<Lignedelivraision> tatbleaux_de_ligne_livraisions;

	@ManyToOne
	@JoinColumn(name="id_employesiege")
	private Emloyesiege  employer_siege;
	
	@Temporal(TemporalType.DATE)
	private Date  date_livraison;

	public Livraision() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Livraision(Superviseur superviseur, Collection<Lignedelivraision> tatbleaux_de_ligne_livraisions,
			Emloyesiege emploiyer_siege, Date date_livraison) {
		super();
		this.superviseur = superviseur;
		this.tatbleaux_de_ligne_livraisions = tatbleaux_de_ligne_livraisions;
		employer_siege = emploiyer_siege;
		this.date_livraison = date_livraison;
	}

	public Superviseur getSuperviseur() {
		return superviseur;
	}

	public void setSuperviseur(Superviseur superviseur) {
		this.superviseur = superviseur;
	}

	public Collection<Lignedelivraision> getTatbleaux_de_ligne_livraisions() {
		return tatbleaux_de_ligne_livraisions;
	}

	public void setTatbleaux_de_ligne_livraisions(Collection<Lignedelivraision> tatbleaux_de_ligne_livraisions) {
		this.tatbleaux_de_ligne_livraisions = tatbleaux_de_ligne_livraisions;
	}

	
	public Date getDate_livraison() {
		return date_livraison;
	}

	public void setDate_livraison(Date date_livraison) {
		this.date_livraison = date_livraison;
	}

	public Long getId_Livraision() {
		return id_Livraision;
	}

	public void setId_Livraision(Long id_Livraision) {
		this.id_Livraision = id_Livraision;
	}

	public Emloyesiege getEmployer_siege() {
		return employer_siege;
	}

	public void setEmployer_siege(Emloyesiege employer_siege) {
		this.employer_siege = employer_siege;
	}
}



