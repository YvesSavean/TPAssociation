package Service;

import java.util.Map;

import Bean.Commande;
import Bean.CommandeArticle;
import EntityManager.CommandeArticleEntityManager;
import EntityManager.CommandeEntityManager;

public class Service {
	public void Ajout(Commande laCommande, Map<Integer,CommandeArticle> lesLignesArticles){
		CommandeEntityManager cem = new CommandeEntityManager();
		CommandeArticleEntityManager caem = new CommandeArticleEntityManager();
		cem.creer(laCommande);
		for(CommandeArticle laLigne : lesLignesArticles.values()){
			caem.creer(laLigne);
		}
		cem.close();
		caem.close();
		
	}
	
	public void supprimer(Commande laCommande){
		CommandeEntityManager cem = new CommandeEntityManager();
		CommandeArticleEntityManager caem = new CommandeArticleEntityManager();
		for(CommandeArticle laLigne : caem.chercherCommandeArticleAdh(laCommande)){
			caem.supprimer(laLigne);
		}

		caem.close();
		cem.supprimer(cem.trouver(laCommande.getId()));

	}
}
