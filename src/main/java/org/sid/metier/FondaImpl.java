package org.sid.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;
import org.sid.dao.AjoutteStockRepository;
import org.sid.dao.ArticleRepository;
import org.sid.dao.CategorieRepository;
import org.sid.dao.CommandeSupervisuerRepository;
import org.sid.dao.EmployesiegeRepository;
import org.sid.dao.LignedeCOmandeRepository;
import org.sid.dao.LignedeLivraisonRepository;
import org.sid.dao.LivraisonRepository;
import org.sid.dao.MouvementdustockRepository;
import org.sid.dao.SuperviseurRepository;
import org.sid.entities.AjoutteStock;
import org.sid.entities.Articles;
import org.sid.entities.Categorie;
import org.sid.entities.CommandeSuperviseur;
import org.sid.entities.Contenu;
import org.sid.entities.Emloyesiege;
import org.sid.entities.LignedecommandeSuperviseur;
import org.sid.entities.Lignedelivraision;
import org.sid.entities.Livraision;
import org.sid.entities.Mouvementsdustock;
import org.sid.entities.Superviseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Array;
@Service
@Transactional
public class FondaImpl implements IFonda {
	@Autowired
	private SuperviseurRepository superviseurrpository;
	@Autowired
	private CategorieRepository catrep;
	@Autowired
	private ArticleRepository artR;
	@Autowired
	private CommandeSupervisuerRepository commandeSupervisuerRepository;
	@Autowired
	private LignedeCOmandeRepository lignedecommandeRepository;
	@Autowired
	private LignedeLivraisonRepository lignedeLivraisonRepository;
	
	@Autowired
	private EmployesiegeRepository employesiegeRepository;
	@Autowired
	private LivraisonRepository livraisonRepository;
	@Autowired
	private MouvementdustockRepository mouvementdustockRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private AjoutteStockRepository ajoutteStockRepository;
	@Override
	public Superviseur consulterSuprviseur(Long idsupeviseur) {
		Superviseur S1=superviseurrpository.findById(idsupeviseur).orElse(null);
		if(S1==null) 
		throw new RuntimeException("le compte n'existe pas");

		
		return S1;
	}
	
	
	@Override
	public Categorie consltecategorie(Long idcategorie) {
		Categorie cat=catrep.findById(idcategorie).orElse(null);
		if(cat==null)
			throw new RuntimeException("categorie introuvable");
		return cat;
	}

	@Override
	public Articles Consulterproduit(Long idprod) {
		Articles art=artR.findById(idprod).orElse(null);
		if(art==null)
			throw new RuntimeException("Article introuvable");
		return art;
	}
	@Override
	public Emloyesiege consulterEmployer(Long idemployer) {
		Emloyesiege emp=employesiegeRepository.findById(idemployer).orElse(null);
		if(emp==null)
			throw new RuntimeException("le compte n'existe pas");
		return emp;
	}

	
	@Override
	public CommandeSuperviseur consulterCommande(Long idcommande) {
		CommandeSuperviseur cmd=commandeSupervisuerRepository.findById(idcommande).orElse(null);
		if(cmd==null)
			throw new RuntimeException("commande introuvable");
		return cmd;
	}

	
	

	

	


	
	@Override
	public void Livrer(Long idemp, CommandeSuperviseur cmd) {
		
	
		Superviseur sp=cmd.getSuperviseur();
		List<LignedecommandeSuperviseur> tableauxcmd=cmd.getTableaux_de_commande_superviseur();
		Emloyesiege emp=consulterEmployer(idemp);
		Livraision lvr= new Livraision(sp, null,emp, new Date());
		List<Lignedelivraision> tableauxdelivraison= new ArrayList<Lignedelivraision>();
		for(LignedecommandeSuperviseur L:tableauxcmd)
		{
			Lignedelivraision ligneliv=new Lignedelivraision(lvr, L.getArticle(),L.getQuantite());
			tableauxdelivraison.add(ligneliv);
			Mouvementsdustock lignedemouvement= new Mouvementsdustock(L.getArticle(), L.getQuantite(), new Date(), "sortie");
			
			mouvementdustockRepository.save(lignedemouvement);
			
		}
		lvr.setTatbleaux_de_ligne_livraisions(tableauxdelivraison);
		livraisonRepository.save(lvr);
		cmd.setStatus("Livrer");
	}





	@Override
	public void Commander(Long idsuper, List<Contenu> les_contenu) {
		Superviseur sp = consulterSuprviseur(idsuper);
		CommandeSuperviseur commande =new CommandeSuperviseur(new Date(), sp, "nonlivrer");
		List<LignedecommandeSuperviseur> tableauxdeligne=new ArrayList<LignedecommandeSuperviseur>();
		for(Contenu c:les_contenu)
		{	
			LignedecommandeSuperviseur l=new LignedecommandeSuperviseur(commande, Consulterproduit(c.getId()),c.getQuantite());
			Mouvementsdustock MvStock=new Mouvementsdustock(Consulterproduit(c.getId()), c.getQuantite(), new Date(), "sortie");
			mouvementdustockRepository.save(MvStock);
			lignedecommandeRepository.save(l);
			tableauxdeligne.add(l);
			
		}
		commande.setTableaux_de_commande_superviseur(tableauxdeligne);
		commandeSupervisuerRepository.save(commande);
	}


	@Override
	public void insereArticle(String nom, Long idcategorie, String photo, String description,double prix) {
		Categorie categorie=consltecategorie(idcategorie);
		Articles art=new Articles(nom, prix, description, photo,  categorie);
				
		artR.save(art);
		
	}


	@Override
	public void insereCategorie(String nom, String description) {
		Categorie cat=new Categorie(nom,description);
		categorieRepository.save(cat);
		
	}


	@Override
	public void insereSupervisuer(String nom, String prenom, String motpass ,String cordonne) {
		Superviseur sup=new Superviseur(nom, prenom, motpass, null, new Date(), cordonne, null)	;
		System.out.println(sup);
		superviseurrpository.save(sup);
	}

    public boolean exitdanslaliste(Long id, List<Mouvementsdustock> list)
    {
    	boolean exite= false;
    	for(Mouvementsdustock m : list)
    	{
    		if(m.getArticle().getId_article()==id)
    			exite=true;
    	}
    	return exite;
    }
	@Override
	public Page<Mouvementsdustock> listedemouvemntsdestock(int page, int size) {
		
		List<Mouvementsdustock> liste_noncomplet=mouvementdustockRepository.findAll(new  PageRequest(page, size)).getContent();
		List<Mouvementsdustock> liste_comp =new ArrayList<Mouvementsdustock>();
		for(Mouvementsdustock m:liste_noncomplet) {
		if(!exitdanslaliste(m.getArticle().getId_article(), liste_comp))
		{Mouvementsdustock nouveau=new Mouvementsdustock(m.getArticle(), 0, null,null) ;
		
		 liste_comp.add(nouveau);
		
		 for(Mouvementsdustock x:liste_noncomplet)
		 {
			 if((x.getArticle().equals(nouveau.getArticle())) && (x.getStatus().equals("entrer")))
			 {
				 nouveau.setQunatite(nouveau.getQunatite()+x.getQunatite());
			 }
			 if((x.getArticle().equals(nouveau.getArticle())) && (x.getStatus().equals("sortie")))
			 {
				 nouveau.setQunatite(nouveau.getQunatite()-x.getQunatite());
			 }
		 }
		}
			
		} 
/*		int start = pageable.getOffset();
		int end = (start + pageable.getPageSize()) > users.size() ? users.size() : (start + pageable.getPageSize());*/
		/*System.out.println("test");
		System.out.println(liste_noncomplet);
		System.out.println("hana");
		System.out.println(liste_comp);*/
		Page<Mouvementsdustock> pages = new PageImpl<Mouvementsdustock>(liste_comp,new PageRequest(page, size), 6L);
			 return pages;
/*	return (Page<Mouvementsdustock>) liste_comp;	 
*/	}


	
	@Override
	public Page<Mouvementsdustock> listemouvementproduit(int page, int size, Long id_article) {
		return mouvementdustockRepository.listMouvementdestockparartcile(id_article,new PageRequest(page, size));
	}


	@Override
	public Page<CommandeSuperviseur> listdescomandesupervisuer(int page, int size, Long id_superviseur) {
		
		return commandeSupervisuerRepository.listdecommandeparsuperviseur(id_superviseur, new PageRequest(page, size));
	
	}

	@Override
	public Page<Superviseur> listdesuperviseur(int page, int size) {
		
		return superviseurrpository.listdesupervisuer(new PageRequest(page, size));
	
	}

	@Override
	public Page<Articles> listarticle_meme_categorie( Long id_categorie, int page, int size)
	{
		return artR.lisetarticledansmencat( id_categorie, new PageRequest(page, size));
	}


	@Override
	public void SupprimerSuperviseur(Long id_Superviseur) {
		
		superviseurrpository.deleteById(id_Superviseur);
		
	}
	@Override
	public Page<Categorie> list_categorie(int page,int size)
	{
		return categorieRepository.listcategorie(new  PageRequest(page, size));
	}
	@Override
	public Page<Articles> list_article_par_cat(Long id_categorie,int page,int size)
	{
		return categorieRepository.listarticle_parcat(id_categorie, new PageRequest(page,size));
	}


	@Override
	public void Supprimerarticle(Long id_article) {
		try {
			artR.deleteById(id_article);
		} catch (Exception e) {
			throw new RuntimeException("l'article n'existe pas");
		}
		
		
	}
	@Override
	public Page<Livraision> listdelivraison(int page,int size)
	{
		return livraisonRepository.findAll(new PageRequest(page, size));
	}
	public Page<CommandeSuperviseur> listdeCommande(int page,int size)
	{
	   return commandeSupervisuerRepository.nonlivrerPage(new PageRequest(page, size));
	}


	@Override
	public Mouvementsdustock consulterMouvementdustock(Long idmvt) {
		return mouvementdustockRepository.findById(idmvt).orElse(null)
				;
	}


	@Override
	public void insererMouvementduStock(Articles article,int quantite) {
		mouvementdustockRepository.save(new Mouvementsdustock(article, quantite, new Date(),"entrer"));
		
	}


	@Override
	public Mouvementsdustock consulterMouvementdustockbyArticle(Articles articles) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int quntite_dans_stock(Long id_produit) {
		
		return artR.quantite_entrant(id_produit)-artR.quantite_sortant(id_produit);
	}


	@Override
	public Page<Mouvementsdustock> list_article(int page, int size) {
	return mouvementdustockRepository.findAll(new PageRequest(page, size));
		
	
	}


	@Override
	public void ajout_dans_lestock(Articles article, int quantite) {
		ajoutteStockRepository.save(new AjoutteStock(article, quantite));
		
	}


	@Override
	public void Supprimercategorie(Long id_categorie) {
		categorieRepository.deleteById(id_categorie);
		
	}


	@Override
	public Page<LignedecommandeSuperviseur> listedeligne_par_une_commande(Long id_commandesup, int page, int size) {
		
		return lignedecommandeRepository.listedeligne_par_une_commande(id_commandesup, new PageRequest(page, size));
	}


	@Override
	public String possible_delivrer(CommandeSuperviseur commande) {
		
		Boolean trouver;
		List<Mouvementsdustock> le_tous=listedemouvemntsdestock(0,130).getContent();
		List<LignedecommandeSuperviseur> demander=commande.getTableaux_de_commande_superviseur();
		for(LignedecommandeSuperviseur ligned:demander)
		{
			trouver=false;
			for(Mouvementsdustock lignel:le_tous)
			{
				if(lignel.getArticle().equals(ligned.getArticle()))
				{
					if(ligned.getQuantite()>lignel.getQunatite())
						{return "  la quantite insufisant du produit "+ligned.getArticle().getNom_article()+" dans le stock";}
					else 
					{trouver=true;}
					
				}
				
			}
			if(trouver==false)
			{
				return "le prouduit:"+ligned.getArticle().getNom_article()+"n'existe pas dans le stock";
			}
		}
		return "vous pouver la livrer";
	}


	@Override
	public void insereremployer(Emloyesiege employe) {
		employesiegeRepository.save(employe);
		
	}

	public boolean verification_superssion(Long id,String motdepasse)
	{ boolean value=false;
	Emloyesiege employe=consulterEmployer(id);
	if(motdepasse.equals(employe.getMot_passe_employerdesiege()))
	value=true;
	
	return value;
	}
	@Override
	public void Supprimeremploye(Long id_empl, String motdepasse) {
	if(verification_superssion(id_empl, motdepasse))	
		employesiegeRepository.deleteById(id_empl);

	}


	
	
}
