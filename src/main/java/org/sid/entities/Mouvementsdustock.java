package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Mouvementsdustock implements Serializable{
	
	
	
	@Id @GeneratedValue
	private Long id_Mouvementdustock;
	@ManyToOne
	@JoinColumn(name="id_article")
	private Articles article;
	private int qunatite;
	@Temporal(TemporalType.DATE)
	private java.util.Date  date_du_Mouvementsdustock;
	
	private String status;
	
	
	public Mouvementsdustock() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Mouvementsdustock(Articles article, int qunatite, Date date_du_Mouvementsdustock, String status) {
		super();
		this.article = article;
		this.qunatite = qunatite;
		this.date_du_Mouvementsdustock = date_du_Mouvementsdustock;
		this.status = status;
	}



	public Long getId_Mouvementdustock() {
		return id_Mouvementdustock;
	}
	public void setId_Mouvementdustock(Long id_Mouvementdustock) {
		this.id_Mouvementdustock = id_Mouvementdustock;
	}
	
	public int getQunatite() {
		return qunatite;
	}
	public void setQunatite(int qunatite) {
		this.qunatite = qunatite;
	}
	public Date getDate_du_Mouvementsdustock() {
		return date_du_Mouvementsdustock;
	}
	public void setDate_du_Mouvementsdustock(Date date_du_Mouvementsdustock) {
		this.date_du_Mouvementsdustock = date_du_Mouvementsdustock;
	}
	
	

	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
