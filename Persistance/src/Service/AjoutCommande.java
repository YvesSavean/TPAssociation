package Service;

import java.util.Map;

import Bean.Commande;
import Bean.CommandeArticle;
import EntityManager.CommandeArticleEntityManager;
import EntityManager.CommandeEntityManager;

public class AjoutCommande {
	
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
	

}
