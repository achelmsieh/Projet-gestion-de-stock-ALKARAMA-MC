package org.sid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CommandeSuperviseur implements Serializable {
	
	
	
	@Id @GeneratedValue
	private Long id_CommandeSuperviseur;
	
	
	@OneToMany(mappedBy="CommandeSuperviseur")
	private List<LignedecommandeSuperviseur> tableaux_de_commande_superviseur;
	
	@Temporal(TemporalType.DATE)
	private Date date_Commande;
	
	@ManyToOne
	@JoinColumn(name="IDSuperviseur")
	private Superviseur superviseur;
	 
	private String Status="nonlivrer";

	public Long getId_CommandeSuperviseur() {
		return id_CommandeSuperviseur;
	}

	public void setId_CommandeSuperviseur(Long id_CommandeSuperviseur) {
		this.id_CommandeSuperviseur = id_CommandeSuperviseur;
	}

	public List<LignedecommandeSuperviseur> getTableaux_de_commande_superviseur() {
		return tableaux_de_commande_superviseur;
	}

	public void setTableaux_de_commande_superviseur(List<LignedecommandeSuperviseur> tableaux_de_commande_superviseur) {
		this.tableaux_de_commande_superviseur = tableaux_de_commande_superviseur;
	}

	public Date getDate_Commande() {
		return date_Commande;
	}

	public void setDate_Commande(Date date_Commande) {
		this.date_Commande = date_Commande;
	}

	public Superviseur getSuperviseur() {
		return superviseur;
	}

	public void setSuperviseur(Superviseur superviseur) {
		this.superviseur = superviseur;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public CommandeSuperviseur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommandeSuperviseur(Date date_Commande, Superviseur superviseur, String status) {
		super();
		this.date_Commande = date_Commande;
		this.superviseur = superviseur;
		Status = status;
		
	}

}	