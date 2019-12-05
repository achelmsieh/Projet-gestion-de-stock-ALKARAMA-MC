package org.sid.dao;

import org.sid.entities.CommandeSuperviseur;
import org.sid.entities.Superviseur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuperviseurRepository extends JpaRepository<Superviseur, Long> {
	

@Query(" from Superviseur o order by o.date_creation_superviseur desc")
	
	public Page<Superviseur> listdesupervisuer(Pageable pegeable);
	
}
