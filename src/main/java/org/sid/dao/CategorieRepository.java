package org.sid.dao;

import org.sid.entities.Articles;
import org.sid.entities.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategorieRepository extends JpaRepository<Categorie,Long > {
//selection de tous les articles
@Query(" from Categorie c order by  c.id_categorie desc") 
public Page<Categorie> listcategorie(Pageable pegeable);
//cosultation des articles par categorie
@Query(" select articles from Categorie  o where o.id_categorie=:x ")
public Page<Articles> listarticle_parcat(@Param("x") Long id_categorie,Pageable pegeable);

}
