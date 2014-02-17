package Junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import Bean.Adherent;
import Bean.Commande;
import Bean.CommandeArticle;
import Bean.CommandeArticleId;
import EntityManager.AdherentEntityManager;
import EntityManager.ArticleEntityManager;
import EntityManager.CommandeEntityManager;
import Service.Service;
public class TestCommandeEntityManager {

	@Test
	public void testInsertionCommande() {
		// pour que le test fonctionne, cet adhérent ne doit pas se trouver dans
		// la table Adherent
		CommandeEntityManager cem = new CommandeEntityManager();
		ArticleEntityManager aem = new ArticleEntityManager();
		
		// un adhérent
		Adherent adh = new Adherent();
		adh.setIdentifiant("KarlJohn");
		adh.setNomDeFamille("Karl");
		adh.setMotDePasse("Karl");
		adh.setPrenom("John");
		
		

		// insertion d'un adhérent pour l'associer à la commande
		AdherentEntityManager aee = new AdherentEntityManager();
		aee.creer(adh); // je le créee

		// une commande
		Commande cmd = new Commande();
		cmd.setId(6);
		cmd.setDateCommande(new Date());
		cmd.setlAdherent(adh);
		cem.close();
		
		
		//une ligne
		CommandeArticle laLigne = new CommandeArticle();
		CommandeArticleId laKey = new CommandeArticleId();
		laKey.setArticle(aem.trouver("aq").getCode());
		laKey.setCommande(cmd.getId());
		laLigne.setKey(laKey);
		laLigne.setQuantiteArticle(3);
		
		//ajout de la ligne dans commande
		 Map<Integer,CommandeArticle> lesLignesArticles = new HashMap<Integer,CommandeArticle>();
		 lesLignesArticles.put(1, laLigne);

		// insertion d'une commande
		// puis vérif
		// puis sa suppression
		Service leService = new Service();
		leService.Ajout(cmd,lesLignesArticles);
		leService.supprimer(cmd);

		// suppresion de l'adhérent inséré et ferme aee
		aee.supprimer(adh);
		aee.close();
	}
	
	@Test
	public void testRechercheCommandeDunAdh() {
		// pour que le test fonctionne, cet adhérent ne doit pas se trouver dans
		// la table Adherent

		// un adhérent
		Adherent adh = new Adherent();
		adh.setIdentifiant("KarlJohn");
		adh.setNomDeFamille("Karl");
		adh.setMotDePasse("Karl");
		adh.setPrenom("John");

		// insertion d'un adhérent pour l'associer à la commande
		AdherentEntityManager aee = new AdherentEntityManager();
		aee.creer(adh); // je le créee

		// une commande
		Commande cmd = new Commande();
		cmd.setId(4321);
		cmd.setDateCommande(new Date());
		cmd.setlAdherent(adh);

		// liste de commande pour cet adhérent
		ArrayList<Commande> listCA = new ArrayList<Commande>();
		listCA.add(cmd);
		
		// insertion d'une commande
		CommandeEntityManager cem = new CommandeEntityManager();
		cem.creer(cmd);
		
		//appel à la méthode puis vérif
		List<Commande> listTrouvee = cem.chercherCommandeDunAdherent(adh);	
		assertEquals(listCA, listTrouvee);
		cem.supprimer(cmd);
		cem.close();

		// suppresion de l'adhérent inséré et ferme aee
		aee.supprimer(adh);
		aee.close();
	}

}
