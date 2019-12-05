package org.sid.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.sid.dao.ArticleRepository;
import org.sid.dao.LignedeCOmandeRepository;
import org.sid.entities.Articles;
import org.sid.entities.Categorie;
import org.sid.entities.CommandeSuperviseur;
import org.sid.entities.Contenu;
import org.sid.entities.Emloyesiege;
import org.sid.entities.LignedecommandeSuperviseur;
import org.sid.entities.Livraision;
import org.sid.entities.Mouvementsdustock;
import org.sid.entities.Superviseur;
import org.sid.metier.IFonda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Fondacontroller {
	
	
	@Autowired 
	private IFonda Ifondametier;
	@RolesAllowed({"SUP"})
	@RequestMapping("/profilsup")
	public String profilsup()
	{
		return "profilsup";
	}
	@RolesAllowed({"SUP","ADM"})
	 @RequestMapping("/employersiege")
	 public String employersiege(Model model
			 ,@RequestParam(name="ajnom_employerdesiege",defaultValue="0")String ajnom 
	        ,@RequestParam(name="ajprenom_employerdesiege",defaultValue="0")String ajprenom
			,@RequestParam(name="ajdescription_employerdesiege",defaultValue="0")String ajdes
			,@RequestParam(name="ajmot_passe_employerdesiege",defaultValue="0")String ajmdp 
			,@RequestParam(name="spid_employerdesiegesup",defaultValue="0")Long spid 
			,@RequestParam(name="spmot_passe_employerdesiege",defaultValue="0")String spmdp 
)
	 {// ajoutte
		if(!ajnom.equals("0"))
		{
			Ifondametier.insereremployer(new Emloyesiege(ajnom, ajprenom, ajmdp, ajdes));
		}
	//supression
		if(!spmdp.equals("0"))
		{
			Ifondametier.Supprimeremploye(spid,spmdp);
		}
		 return "employersiege"; 
	 }
	@RolesAllowed({"ADM"})
	@RequestMapping("/livrer")
	public void livrer(Model model 
			,@RequestParam(name="id_CommandeSuperviseur",defaultValue="0")Long id_CommandeSuperviseur
			)
	{	CommandeSuperviseur commande=Ifondametier.consulterCommande(id_CommandeSuperviseur);
		if(Ifondametier.possible_delivrer(commande).equals("vous pouver la livrer"))
				{
			       Ifondametier.Livrer(1L, commande);
				}
		else
		{
			//lever exeption
		}
		

		
	}
	@RolesAllowed({"ADM"})
	@RequestMapping("/consulterCommande")
	public String consulterCommande(Model model 
			,@RequestParam(name="id_CommandedeSuperviseur",defaultValue="0")Long id_CommandedeSuperviseur) 
	
	
	{	
		try {
			
	if(!(id_CommandedeSuperviseur==0))
		{//clacluer si il est posssible de faire la livraison
		CommandeSuperviseur commande=Ifondametier.consulterCommande(id_CommandedeSuperviseur);
		String text=Ifondametier.possible_delivrer(commande);
		 model.addAttribute("text",text);
		//afficher
		
		model.addAttribute("commande",commande);
		
		Page<LignedecommandeSuperviseur> lesligne=Ifondametier.listedeligne_par_une_commande(id_CommandedeSuperviseur, 0, 3);
		
		model.addAttribute("listedeligne_par_une_commande",lesligne.getContent());
		
		}
		Page<CommandeSuperviseur> liscommandesupervisuer=Ifondametier.listdeCommande(0, 6);
		model.addAttribute("listcommmande",liscommandesupervisuer.getContent());
	} catch (Exception e) {
		// TODO: handle exception
	}
		return "consulterCommande";
	}
	
	@RolesAllowed({"ADM"})
	@RequestMapping("/ajoutecategorie")
	public String ajoutecategorie(Model model
			,@RequestParam(name="page1",defaultValue="0")int page1
			,@RequestParam(name="size1",defaultValue="5")int size1
			,@RequestParam(name="nom_categorie",defaultValue="non")String nom_categorie
			,@RequestParam(name="description_categorie",defaultValue="0")String description_categorie
			,@RequestParam(name="id_categorie",defaultValue="0")Long id_categorie)
	{
		try {
			
			if(!nom_categorie.equals("non"))
			{ System.out.println("je suis rentrer");
				Ifondametier.insereCategorie(nom_categorie, description_categorie);
				System.out.println("\n\n\n"+nom_categorie+"\n\n\n");
			}
			if(!id_categorie.equals(0L))
			{
				Ifondametier.Supprimercategorie(id_categorie);
			}
			Page<Categorie> pagedecategorie=Ifondametier.list_categorie(page1, size1);
			model.addAttribute("listcategorie",pagedecategorie.getContent());
			 int[] pages=new int[pagedecategorie.getTotalPages()];
			 model.addAttribute("pages",pages);
			 
				
			
				
		} catch (Exception e) {
			model.addAttribute("exception",e.getMessage());
		}return "ajoutecategorie";
		
	}
	@RolesAllowed({"SUP"})
	@RequestMapping("/Livraison")
	public String Livrer()
	{///hna mazal
		return "Livrer";
	}
	@RolesAllowed({"SUP"})
	@RequestMapping("/Poster_Commande")
	public String Poster_Comande(@RequestParam(name="nombre_de_ligne",defaultValue="0" )int  nombre_de_ligne ,@RequestParam Map<String,String> params) {
		List<Contenu> list_de_commande=new ArrayList<Contenu>();
		
		for(int i=0;i<=nombre_de_ligne;i++)
		{	
			list_de_commande.add(new Contenu(Long.valueOf(params.get("idarticle_"+i)),Integer.valueOf(params.get("quantite_"+i))));   
		}
		for (Contenu c:list_de_commande)
	System.out.println(c.toString());
		Ifondametier.Commander(1L, list_de_commande);
		return"Commande";
	}
	@RolesAllowed({"SUP"})
	@RequestMapping("/Commande")
	public String Commande(Model model
			,@RequestParam(name="page1",defaultValue="0")int page1
			,@RequestParam(name="size1",defaultValue="5")int size1
			,@RequestParam(name="id_categorie",defaultValue="0")Long id_categorie
			,@RequestParam(name="page2",defaultValue="0")int page2
			,@RequestParam(name="size2",defaultValue="5")int size2
			)
	{	
	try {
		
		Page<Categorie> pagedecategorie=Ifondametier.list_categorie(page1, size1);
		model.addAttribute("listcategorie",pagedecategorie.getContent());
		 int[] pages=new int[pagedecategorie.getTotalPages()];
		 model.addAttribute("pages",pages);
		 String nom_categorie=Ifondametier.consltecategorie(id_categorie).getNom_categorie();
			model.addAttribute("nom_categorie",nom_categorie);
			Page<Articles> listarticle=Ifondametier.list_article_par_cat(id_categorie, page2, size2);
			model.addAttribute("listearticleparcat",listarticle.getContent());
			
	} catch (Exception e) {
		model.addAttribute("exception",e.getMessage());
	}return "Commande";
		
	}
	


	
	@RolesAllowed({"ADM"})
	@RequestMapping("/ajouterStock")
	public String afficherlapage()
	{
		return "ajouterStock";
	}
//c'st un save apres un une autre methos qui convoque la page 
	@RolesAllowed({"ADM"})
	@RequestMapping("/saveStock")
	public String ajoutarticleStock(Model model,Long id_article, int quantite)
	{	try {
		Articles article=Ifondametier.Consulterproduit(id_article);
		Ifondametier.ajout_dans_lestock(article, quantite);
		Ifondametier.insererMouvementduStock(article, quantite);
		
	} catch (Exception e) {
	}
	return "ajouterStock";
	}
	@RolesAllowed({"ADM"})
	@RequestMapping("/consulterstock")
	public String stock(Model model
			,@RequestParam(name="page",defaultValue="0")int page
			,@RequestParam(name="size",defaultValue="6")int size)
	{
		Page<Mouvementsdustock> listdeStock=Ifondametier.listedemouvemntsdestock( page, size);
		
		
		model.addAttribute("listdeStock",listdeStock.getContent());
	
		return "Stock";
	}
	@RolesAllowed({"ADM"})
	@RequestMapping("/MouvementduStock")
	public String mouvement_du_stock(Model	model)
	{	Page<Livraision> listdelivraison=Ifondametier.listdelivraison(0, 6);
		Page<CommandeSuperviseur> liscommandesupervisuer=Ifondametier.listdeCommande(0, 6);
		Page<Mouvementsdustock> listdumovement=Ifondametier.listedemouvemntsdestock(0, 4);
		model.addAttribute("listdemovemntdestock",listdumovement.getContent());
		model.addAttribute("listlivraison",listdelivraison.getContent());
		model.addAttribute("listcommmande",liscommandesupervisuer.getContent());
		return "MouvementduStock";
	}
	@RolesAllowed({"ADM"})
	@RequestMapping("/Superviseur")
	public String supervisuer() {
		return "Superviseur";
	}
	@RolesAllowed({"ADM"})
	@RequestMapping("/Article")
	public String page() {
		return "Article";
	}
	@RolesAllowed({"ADM"})
	@RequestMapping("/ajoutSuperviseur")
	public String ajouterSuperviseur(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		try {
			Page<Superviseur> pagedeSupeviseur=Ifondametier.listdesuperviseur(page,size);
			 model.addAttribute("listesuperviseur",pagedeSupeviseur.getContent());
			 int[] pages=new int[pagedeSupeviseur.getTotalPages()];
			 model.addAttribute("pages",pages);
		} catch (Exception e) {
			model.addAttribute("exception",e.getMessage());
		}

		return "ajoutSuperviseur";
	}
	@RolesAllowed({"ADM"})
	@RequestMapping("/ajoutArticle")
	public String  ajoutArticle(Model model,
			@RequestParam(name="page1",defaultValue="0")int page1,
			@RequestParam(name="size1",defaultValue="3")int size1
			,@RequestParam(name="id_categorie",defaultValue="0")Long id_categorie,String nom_article,Long categorie_article,String description_article,@RequestParam(name="prix_unitaire_article",defaultValue="5d")double prix_unitaire_article
			,@RequestParam(name="page2",defaultValue="0")int page2
			,@RequestParam(name="size2",defaultValue="2")int size2) {
		try {
			model.addAttribute("id_categorie",id_categorie);
			if(nom_article!=null)
			{
				Ifondametier.insereArticle(nom_article, categorie_article, null, description_article, prix_unitaire_article);
			}
			Page<Categorie> pagedecategorie=Ifondametier.list_categorie(page1, size1);
			 model.addAttribute("listcategorie",pagedecategorie.getContent());
			 int[] pages1=new int[pagedecategorie.getTotalPages()];
			 System.out.println(pagedecategorie.toString()+"   "+pagedecategorie.getNumberOfElements());
			 model.addAttribute("pages1",pages1);
			 String nom_categorie=Ifondametier.consltecategorie(id_categorie).getNom_categorie();
				model.addAttribute("nom_categorie",nom_categorie);
				Page<Articles> listarticle=Ifondametier.list_article_par_cat(id_categorie, page2, size2);
				model.addAttribute("listearticleparcat",listarticle.getContent());
				 int[] pages2=new int[listarticle.getTotalPages()];
				 System.out.println(listarticle.toString()+"   "/*+listarticle.getNumberOfElements()*/);
				 model.addAttribute("pages2",pages2);
			 
		} catch (Exception e) {
			model.addAttribute("exception",e.getMessage());
		}
		return "ajoutArticle";
		
	}


	
		
		
		
		
	
	
	
	@RolesAllowed({"ADM"})
	@RequestMapping("/proposAD")
	public String proposAD() {
		return "a_proposAD";
	}
	@RolesAllowed({"SUP"})
	@RequestMapping("/proposSP")
	public String proposSP() {
		return "a_proposSP";
	}
//gestion suprviseur	
	@RolesAllowed({"ADM"})
 @RequestMapping("/consulterSuprviseur")
public String consulterSuperviseur(Model model, Long id_superviseur) {
	model.addAttribute("codeSuperviseur",id_superviseur);	
	 try {
			
			 Superviseur sp=Ifondametier.consulterSuprviseur(id_superviseur);
			 Page<CommandeSuperviseur> pageCommande=Ifondametier.listdescomandesupervisuer(0, 4, id_superviseur);
			model.addAttribute("listcomande",pageCommande.getContent());
			 model.addAttribute("superviseur",sp);
				 
		} catch (Exception e) {
			model.addAttribute("exception",e.getMessage());
			
		}
		
		return "superviseur";
      }
	@RolesAllowed({"ADM"})
 @RequestMapping("/consulterlistesupervisuer")
 public String consulterlesSuperviseur(Model model)
 {
	 model.addAttribute("listsuperviseur",Ifondametier.listdesuperviseur(0, 4));
	 return"consulterSuprviseur";
 }

//sauvgade dun superviseur
	@RolesAllowed({"ADM"})
 @RequestMapping("/savesuperviseur")
 public String enregistrer_superviseur(Model model,String nom,String prenom,String description,String motdepasse)
 {
	 try {
		 Ifondametier.insereSupervisuer(nom, prenom, motdepasse, description);
		
	} catch (Exception e) {
		model.addAttribute("exception",e.getMessage());
	}
	 try {
			Page<Superviseur> pagedeSupeviseur=Ifondametier.listdesuperviseur(0,10);
			 model.addAttribute("listesuperviseur",pagedeSupeviseur.getContent());
		} catch (Exception e) {
			model.addAttribute("exception",e.getMessage());
		}
	 return "ajoutSuperviseur";
 }
//gestion articles de meme que les superviseurs
	@RolesAllowed({"ADM"})
@RequestMapping("/consulterArticle")
public String consulterArticle(Model model, Long id_Article) {
	model.addAttribute("codeArticle",id_Article);	
	 try {
			
		Articles ar=Ifondametier.Consulterproduit(id_Article);
			Page<Articles> pagearticlepareille=Ifondametier.listarticle_meme_categorie(ar.getCategorie_article().getId_categorie(), 0, 4);
		    model.addAttribute("list_produit_dans_meme_categorie",pagearticlepareille.getContent());
		    System.out.println(pagearticlepareille.getNumberOfElements());
		    Page<Mouvementsdustock> pagemouvemetndestockpararticle=Ifondametier.listemouvementproduit(0, 4, id_Article);
			model.addAttribute("listcommande",pagemouvemetndestockpararticle.getContent());
			 model.addAttribute("article",ar);
				 
		} catch (Exception e) {
			model.addAttribute("exception",e.getMessage());
			
		}
		
		return "Article";
}
	@RolesAllowed("ADM")
//cosulter la list des superviseur
@RequestMapping("/consulterlistsuperviseur")
public void consulterListe(Model model)
{
	
}
// suprimer un superviseur
	@RolesAllowed({"ADM"})
@RequestMapping("/SuprimerSupervisuer")
public String Suprimersuperviseur(Long id_superviseur, Model model)
{Ifondametier.SupprimerSuperviseur(id_superviseur);
try {
	Page<Superviseur> pagedeSupeviseur=Ifondametier.listdesuperviseur(0,10);
	 model.addAttribute("listesuperviseur",pagedeSupeviseur.getContent());
} catch (Exception e) {
	model.addAttribute("exception",e.getMessage());
}
return "ajoutSuperviseur";}







	@RolesAllowed({"ADM"})
@RequestMapping("/Suprimer_article")
public String Suprimer_article(Long id_article,Model model)
{try {
	Page<Categorie> pagedecategorie=Ifondametier.list_categorie(0, 8);
	 model.addAttribute("listcategorie",pagedecategorie.getContent());
	Ifondametier.Supprimerarticle(id_article);
} catch (Exception e) {
	model.addAttribute("exception_supresion",e.getMessage());
}

return "ajoutArticle";
}
}