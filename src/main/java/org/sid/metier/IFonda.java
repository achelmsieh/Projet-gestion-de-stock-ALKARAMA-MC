package org.sid.metier;

import java.util.List;


import org.sid.entities.Articles;
import org.sid.entities.Categorie;
import org.sid.entities.CommandeSuperviseur;
import org.sid.entities.Contenu;
import org.sid.entities.Emloyesiege;
import org.sid.entities.LignedecommandeSuperviseur;
import org.sid.entities.Livraision;
import org.sid.entities.Mouvementsdustock;
import org.sid.entities.Superviseur;
import org.springframework.data.domain.Page;

public interface IFonda {
	
	public void Livrer(Long idemp,CommandeSuperviseur cmd);
	public void Commander(Long idsuper , List<Contenu> les_contenu);
	
	public Superviseur consulterSuprviseur(Long idsupeviseur);
	public Emloyesiege consulterEmployer(Long idemployer);
	public Articles Consulterproduit(Long idprod);
	public Categorie consltecategorie(Long idcategorie);
	public CommandeSuperviseur consulterCommande(Long idcommande);
	public Mouvementsdustock consulterMouvementdustock(Long idmvt);
	public Mouvementsdustock consulterMouvementdustockbyArticle(Articles articles );
	
	public int quntite_dans_stock(Long id_produit);
	public void ajout_dans_lestock(Articles article,int quantite);
	
	public void insereArticle(String nom, Long idcategorie, String photo, String description,double prix);
	public void insereCategorie(String nom,String description);
	public  void insereSupervisuer(String nom,String prenom ,String motpass,String cordonne);
	public void insererMouvementduStock(Articles article,int quantite );
	public void insereremployer(Emloyesiege employe);
	
	public Page<Articles> list_article_par_cat(Long id_categorie,int page,int size);
	public Page<Mouvementsdustock> listedemouvemntsdestock(int page ,int size);
	public Page<Mouvementsdustock> listemouvementproduit(int page ,int size ,Long id_produit);
	public Page<CommandeSuperviseur> listdescomandesupervisuer(int page, int size, Long id_superviseur);
	public Page<Superviseur> listdesuperviseur(int page, int size);
	public Page<Articles> listarticle_meme_categorie(Long id_article, int page, int size);
	public Page<Categorie> list_categorie(int page,int size);
	public Page<Livraision> listdelivraison(int page,int size);
	public Page<CommandeSuperviseur> listdeCommande(int page,int size);
	public Page<Mouvementsdustock> list_article(int page,int size);
	public Page<LignedecommandeSuperviseur> listedeligne_par_une_commande(Long id_commandesup, int page ,int  size);
	
	public void SupprimerSuperviseur(Long id_Superviseur);
	public void Supprimerarticle(Long id_article);
	public void Supprimercategorie(Long id_categorie);
	public void Supprimeremploye(Long id_empl,String motdepasse);
	
	
	public String possible_delivrer(CommandeSuperviseur commande);
}

