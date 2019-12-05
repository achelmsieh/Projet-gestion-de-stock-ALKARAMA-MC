package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AjoutteStock implements Serializable{
	
	@Id @GeneratedValue
	private Long id_ajjoute;
	
	@ManyToOne
	@JoinColumn(name="id_article")
	private Articles article;
	
	private int qunatite;

	public Long getId_ajjoute() {
		return id_ajjoute;
	}

	public void setId_ajjoute(Long id_ajjoute) {
		this.id_ajjoute = id_ajjoute;
	}

	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

	public int getQunatite() {
		return qunatite;
	}

	public void setQunatite(int qunatite) {
		this.qunatite = qunatite;
	}

	public AjoutteStock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AjoutteStock(Articles article, int qunatite) {
		super();
		this.article = article;
		this.qunatite = qunatite;
	}
	
}
