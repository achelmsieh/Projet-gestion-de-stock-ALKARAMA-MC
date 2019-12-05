package org.sid;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sid.dao.ArticleRepository;
import org.sid.dao.CategorieRepository;
import org.sid.dao.CommandeSupervisuerRepository;
import org.sid.dao.LignedeCOmandeRepository;
import org.sid.dao.RoleRepository;
import org.sid.dao.SuperviseurRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Articles;
import org.sid.entities.Categorie;
import org.sid.entities.CommandeSuperviseur;
import org.sid.entities.LignedecommandeSuperviseur;
import org.sid.entities.Superviseur;
import org.sid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class AchraftuvafinirApplication implements CommandLineRunner {
@Autowired
	private CategorieRepository catR;
@Autowired
	private ArticleRepository ArR;
@Autowired
private SuperviseurRepository sp;
@Autowired
private CommandeSupervisuerRepository cs;
@Autowired
private LignedeCOmandeRepository lcs;
@Autowired
private RoleRepository rolerepository ;
@Autowired 
UserRepository userreposritopry;
	public static void main(String[] args) {
		SpringApplication.run(AchraftuvafinirApplication.class, args);
	  }

@Override
public void run(String... args) throws Exception {
	
}
	
}