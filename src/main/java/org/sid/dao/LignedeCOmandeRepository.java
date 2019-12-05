package org.sid.dao;


import org.sid.entities.LignedecommandeSuperviseur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LignedeCOmandeRepository extends JpaRepository<LignedecommandeSuperviseur, Long>{
	@Query(" from LignedecommandeSuperviseur as l where l.CommandeSuperviseur.id_CommandeSuperviseur=:x order by l.quantite desc")
	public Page<LignedecommandeSuperviseur> listedeligne_par_une_commande(@Param("x") Long id_commande,  Pageable pegeable);

}
