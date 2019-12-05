package org.sid.dao;

import org.sid.entities.CommandeSuperviseur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommandeSupervisuerRepository extends JpaRepository<CommandeSuperviseur, Long> {
@Query(" from CommandeSuperviseur as o where o.superviseur.id_superviseur=:x order by date_Commande desc")
public Page<CommandeSuperviseur> listdecommandeparsuperviseur(@Param("x") Long id_superviseur,Pageable pegeable);

@Query(" from CommandeSuperviseur as o where o.Status='nonlivrer' order by date_Commande desc")
public Page<CommandeSuperviseur> nonlivrerPage(Pageable pegeable);

	

}
