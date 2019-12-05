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
public class LignedecommandeSuperviseur implements Serializable {
	
	
	
	
	@Id @GeneratedValue
	private Long id_Lignedecommande;
	@ManyToOne
	@JoinColumn(name="IDcommande")
    private CommandeSuperviseur CommandeSuperviseur;
	
	@ManyToOne
    @JoinColumn(name="IDarticle")
	private Articles article;
	
	private int quantite;

	public Long getId_Lignedecommande() {
		return id_Lignedecommande;
	}

	public void setId_Lignedecommande(Long id_Lignedecommande) {
		this.id_Lignedecommande = id_Lignedecommande;
	}

	public CommandeSuperviseur getCommandeSuperviseur() {
		return CommandeSuperviseur;
	}

	public void setCommandeSuperviseur(CommandeSuperviseur commandeSuperviseur) {
		CommandeSuperviseur = commandeSuperviseur;
	}

	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public LignedecommandeSuperviseur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LignedecommandeSuperviseur(org.sid.entities.CommandeSuperviseur commandeSuperviseur, Articles article,
			int quantite) {
		super();
		CommandeSuperviseur = commandeSuperviseur;
		this.article = article;
		this.quantite = quantite;
	}



}