package org.sid.entities;

public class Contenu {

	private Long id;
	private int quantite;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	@Override
	public String toString() {
		return "Contenu [id=" + id + ", quantite=" + quantite + "]";
	}
	public Contenu(Long id, int quantite) {
		super();
		this.id = id;
		this.quantite = quantite;
	}
	public Contenu() {
		super();
		// TODO Auto-generated constructor stub
	}
}
