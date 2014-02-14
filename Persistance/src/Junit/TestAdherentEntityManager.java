package Junit;
import static org.junit.Assert.*;

import javax.validation.constraints.AssertFalse;

import org.junit.Test;

import Bean.Adherent;
import EntityManager.AdherentEntityManager;


public class TestAdherentEntityManager {
	
	@Test
	public void testSuppressionAdherentId(){
		Adherent lAdherent = new Adherent("idioljkl","mdp","Savean","yves","155 Rue Général Bual","","44000","Nantes","France");
		AdherentEntityManager aem= new AdherentEntityManager();
		aem.creer(lAdherent);
		aem.supprimer(lAdherent);
		aem.close();
	}
	
	@Test
	public void testTrouverAdherent(){
		Adherent lAdherent = new Adherent("bbb","mdp","Savean","yves","155 Rue Général Bual","","44000","Nantes","France");
		AdherentEntityManager aem= new AdherentEntityManager();
		aem.creer(lAdherent);
		assertTrue("bbb"==aem.trouver("bbb").getIdentifiant());
		aem.supprimer(lAdherent);
		aem.close();
	}
	
	@Test
	public void testInsertionAdherent(){
		Adherent lAdherent = new Adherent("id","mdp","Savean","yves","155 Rue Général Bual","","44000","Nantes","France");
		AdherentEntityManager aem= new AdherentEntityManager();
		aem.creer(lAdherent);
		assertTrue(lAdherent==aem.trouver("id"));
		aem.supprimer(lAdherent);
		aem.close();
	}
	
	@Test
	public void testModificationAdherent(){
		Adherent lAdherent = new Adherent("id2","mdp","Savean","yves","155 Rue Général Bual","","44000","Nantes","France");
		AdherentEntityManager aem= new AdherentEntityManager();
		aem.creer(lAdherent);
		Adherent lAdherentMod = new Adherent("id2","mdp","Savean","TheBest","155 Rue Général Bual","","44000","Nantes","France");
		aem.modifier(lAdherentMod);
		assertTrue("TheBest"==aem.trouver("id2").getPrenom());
		aem.supprimer(lAdherent);
		aem.close();
	}
}
