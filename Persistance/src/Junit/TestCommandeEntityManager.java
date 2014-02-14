package Junit;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import Bean.Adherent;
import Bean.Commande;
import EntityManager.AdherentEntityManager;
import EntityManager.CommandeEntityManager;

public class TestCommandeEntityManager {

	@Test
	public void testInsertionCommande() {
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

		// insertion d'une commande
		// puis vérif
		// puis sa suppression
		CommandeEntityManager cem = new CommandeEntityManager();
		cem.creer(cmd);
		assertTrue(cmd == cem.trouver(cmd.getId()));
		cem.supprimer(cmd);
		cem.close();

		// suppresion de l'adhérent inséré et ferme aee
		aee.supprimer(adh);
		aee.close();
	}

}
