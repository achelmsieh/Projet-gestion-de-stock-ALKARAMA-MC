package org.sid.dao;



import org.sid.entities.Mouvementsdustock;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MouvementdustockRepository extends JpaRepository<Mouvementsdustock, Long>{
	
	
@Query("select o from Mouvementsdustock o where o.article.id_article=:x order by o.date_du_Mouvementsdustock desc")
	public Page<Mouvementsdustock> listMouvementdestockparartcile(@Param("x") Long id_article,Pageable pegeable);
	
	
	
}
