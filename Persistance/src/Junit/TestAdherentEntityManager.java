package Junit;
import static org.junit.Assert.*;

import javax.validation.constraints.AssertFalse;

import org.junit.Test;

import Bean.Adherent;
import EntityManager.AdherentEntityManager;


public class TestAdherentEntityManager {
	
	@Test
	public void testSuppressionAdherentId(){
		Adherent lAdherent = new Adherent("idioljkl","mdp","Savean","yves","155 Rue G�n�ral Bual","","44000","Nantes","France");
		AdherentEntityManager aem= new AdherentEntityManager();
		aem.creer(lAdherent);
		aem.supprimer(lAdherent);
		aem.close();
	}
	
	@Test
	public void testTrouverAdherent(){
		Adherent lAdherent = new Adherent("bbb","mdp","Savean","yves","155 Rue G�n�ral Bual","","44000","Nantes","France");
		AdherentEntityManager aem= new AdherentEntityManager();
		aem.creer(lAdherent);
		assertTrue("bbb"==aem.trouver("bbb").getIdentifiant());
		aem.supprimer(lAdherent);
		aem.close();
	}
	
	@Test
	public void testInsertionAdherent(){
		Adherent lAdherent = new Adherent("id","mdp","Savean","yves","155 Rue G�n�ral Bual","","44000","Nantes","France");
		AdherentEntityManager aem= new AdherentEntityManager();
		aem.creer(lAdherent);
		assertTrue(lAdherent==aem.trouver("id"));
		aem.supprimer(lAdherent);
		aem.close();
	}
	
	@Test
	public void testModificationAdherent(){
		Adherent lAdherent = new Adherent("id2","mdp","Savean","yves","155 Rue G�n�ral Bual","","44000","Nantes","France");
		AdherentEntityManager aem= new AdherentEntityManager();
		aem.creer(lAdherent);
		Adherent lAdherentMod = new Adherent("id2","mdp","Savean","TheBest","155 Rue G�n�ral Bual","","44000","Nantes","France");
		aem.modifier(lAdherentMod);
		assertTrue("TheBest"==aem.trouver("id2").getPrenom());
		aem.supprimer(lAdherent);
		aem.close();
	}
	
	
	//Persistance (pour le site web):
	
	//Liste commandes
	
	//TODO: cr�er une m�thode qui renvoit pour un id adh�rent l'ensemble des articles command�es.
	//TODO: cr�er la classe Bean.commande_article avec comme variable: code, prix (trouv� dans articles), nom (trouv� dans articles).
	//TODO: cr�er pour ce Bean une m�thode qui permet � partir du code article rentr�e de trouver son prix et son nom.
	//TODO: cr�er une m�thode qui fait la supression de la liste des commandes pour un adh�rent donn�e et qui renvoit une liste des codes des articles supprim�s.
	//TODO: cr�er une m�thode qui fait la maj du stock des articles indiqu� en parametre (avec leur codes) (augmente de un le stock).
	
	//Liste Article:
	//TODO: cr�er une methode checkArticle(int idarticle) qui renvoit true si le stock de l'article = 0
	//TODO: cr�er une m�thode addArtcle (id Adehrent, id article) qui rajoute l'article dans la liste des commandes de l'adherent
	//TODO: cr�er une m�thode qui renvoit tout les articles disponibles
}
