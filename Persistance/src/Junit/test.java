package Junit;
import Bean.Adherent;
import EntityManager.AdherentEntityManager;


public class test {
	public static void main(String args[]){
		AdherentEntityManager aem= new AdherentEntityManager();
		Adherent lAdherent = new Adherent("id","mdp","yves","Savean","155 Rue G�n�ral Bual","","44000","Nantes","France");
		aem.creer(lAdherent);
		for(Adherent unAdherent : aem.ChercherAdherents()){
			System.out.println(unAdherent.getNomDeFamille());
		}
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
