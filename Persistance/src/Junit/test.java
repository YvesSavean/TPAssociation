package Junit;
import Bean.Adherent;
import EntityManager.AdherentEntityManager;


public class test {
	public static void main(String args[]){
		AdherentEntityManager aem= new AdherentEntityManager();
		Adherent lAdherent = new Adherent("id","mdp","yves","Savean","155 Rue Général Bual","","44000","Nantes","France");
		aem.creer(lAdherent);
		for(Adherent unAdherent : aem.ChercherAdherents()){
			System.out.println(unAdherent.getNomDeFamille());
		}
		aem.close();
	}
	
	
	//Persistance (pour le site web):
	
	//Liste commandes
	
	//TODO: créer une méthode qui renvoit pour un id adhérent l'ensemble des articles commandées.
	//TODO: créer la classe Bean.commande_article avec comme variable: code, prix (trouvé dans articles), nom (trouvé dans articles).
	//TODO: créer pour ce Bean une méthode qui permet à partir du code article rentrée de trouver son prix et son nom.
	//TODO: créer une méthode qui fait la supression de la liste des commandes pour un adhérent donnée et qui renvoit une liste des codes des articles supprimés.
	//TODO: créer une méthode qui fait la maj du stock des articles indiqué en parametre (avec leur codes) (augmente de un le stock).
	
	//Liste Article:
	//TODO: créer une methode checkArticle(int idarticle) qui renvoit true si le stock de l'article = 0
	//TODO: créer une méthode addArtcle (id Adehrent, id article) qui rajoute l'article dans la liste des commandes de l'adherent
	//TODO: créer une méthode qui renvoit tout les articles disponibles
}
