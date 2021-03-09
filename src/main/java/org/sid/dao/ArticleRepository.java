package org.sid.dao;

import org.sid.entities.Articles;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ArticleRepository extends JpaRepository<Articles, Long> {

	@Query(" from Articles as a where a.categorie_article.id_categorie=:x order by id_article desc")
	public Page<Articles> lisetarticledansmencat(@Param("x") Long id_categorie,  Pageable pegeable);

	@Query("select sum(t.qunatite) from Articles as a join a.tableauxdemouvementdeStock t where t.status='sortie' and a.id_article=:x")
	public int quantite_sortant(@Param("x") Long id_categorie);
	
	@Query("select sum(t.qunatite) from Articles as a join a.tableauxdemouvementdeStock t where t.status='entrer' and a.id_article=:x")
	public int quantite_entrant(@Param("x") Long id_categorie);
}
/*@Query("select a.id_article ,a.nom_article ,((select sum(t.qunatite) from Articles as a join a.tableauxdemouvementdeStock t where t.status='entrer' )-(select sum(t.qunatite) from Articles as a join a.tableauxdemouvementdeStock t where t.status='sortie' )) as quantite from Articles as a order by id_article desc")
public Page<Articles> quantiearticle(  Pageable pegeable);
}*/
//@Query("select a.id_article ,a.nom_article ,((select sum(t.qunatite) from Articles as a join a.tableauxdemouvementdeStock t where t.status='entrer' )-(select sum(t.qunatite) from Articles as a join a.tableauxdemouvementdeStock t where t.status='sortie' )) as quantite from Articles as a order by id_article desc")
//hola