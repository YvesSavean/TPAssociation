package Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.Adherent;
import Bean.Article;
import Bean.Commande;
import Bean.CommandeArticle;
import Bean.CommandeArticleId;
import EntityManager.AdherentEntityManager;
import EntityManager.ArticleEntityManager;
import EntityManager.CommandeArticleEntityManager;
import EntityManager.CommandeEntityManager;

public class Traitement {
	// Ajouter un article dans une commande_article mais avant créer la commande
	// si besoin
	public void ajoutArticleCommande(String codeArticle, String login) {
		// Le service
		Service leService = new Service();
		// La commande
		Commande cmd;
		// Les différents EntityManagers
		AdherentEntityManager aem = new AdherentEntityManager();
		ArticleEntityManager arem = new ArticleEntityManager();
		CommandeEntityManager cem = new CommandeEntityManager();
		CommandeArticleEntityManager caem = new CommandeArticleEntityManager();
		// Les parametres
		Adherent adh = aem.trouver(login);
		Article art = arem.trouver(codeArticle);
		// Recherche pour trouver les commandes de l'adhérent
		List<Commande> listeDeSesCommandes = cem
				.chercherCommandeDunAdherent(adh);
		// Si il a déjà passé des commandes
		if (listeDeSesCommandes != null && !listeDeSesCommandes.isEmpty()) {
			// on récupère la commande managed
			// le premier de la liste, car plusieurs commandes ne sont pas
			// gérées dans le projet Dynamique
			cmd = cem.trouver(listeDeSesCommandes.get(0).getId());
		} else {
			// Si il n'a pas déjà passé de commande
			// Instanciation d'une nouvelle commande
			cmd = new Commande();
			int nombreAleatoire = 10 + (int) (Math.random() * ((1000 - 10) + 1));
			cmd.setId(nombreAleatoire); // problème pour l'id : je veux bien
										// laisser jpa le gerer mais je ne sais
										// pas cmt je le recupère après
			cmd.setDateCommande(new Date());
			cmd.setlAdherent(adh);
		}
		// Instanciation d'une nouvelle ligne de la commande
		CommandeArticle laLigne = new CommandeArticle();
		CommandeArticleId laKey = new CommandeArticleId();
		laKey.setArticle(art.getCode());
		laKey.setCommande(cmd.getId());
		laLigne.setKey(laKey);
		laLigne.setQuantiteArticle(1); // 1 par défaut car non géré dans le
										// projet Dynamique
		// Instanciation de la ligne commande
		Map<Integer, CommandeArticle> lesLignesArticles = new HashMap<Integer, CommandeArticle>();
		lesLignesArticles.put(1, laLigne); // il n'y a qu'une ligne
		// Si il a déjà passé des commandes
		if (listeDeSesCommandes != null && !listeDeSesCommandes.isEmpty()) {
			// On ajoute la ligne
			for (CommandeArticle ligne : lesLignesArticles.values()) {
				caem.creer(ligne);
			}
			// Je ferme les EntityManagers pour la commande et commandeArticle
			// pour être au même stade de l'autre cas
			cem.close();
			caem.close();
		} else {
			// Si il n'a pas déjà passé de commande
			// Je ferme les EntityManagers car ils vont être réouvert dans la
			// méthode Ajout
			cem.close();
			caem.close();
			// Insertion d'une commande et de la ligne
			leService.Ajout(cmd, lesLignesArticles);
		}
		// Je ferme les autres EntityManagers
		aem.close();
		arem.close();
	}

	// Supression de toutes les commandes (puis des commandes_articles liés) ou
	// le login est égal à celui en paramètre
	public void viderCommande(String login) {
		// Les différents EntityManagers
		AdherentEntityManager aem = new AdherentEntityManager();
		CommandeEntityManager cem = new CommandeEntityManager();
		Service leService = new Service();
		// Pour trouver les commandes d'un adhérent
		List<Commande> listeDeSesCommandes = cem
				.chercherCommandeDunAdherent(aem.trouver(login));
		// Je ferme les EntityManagers car ils vont être réouvert dans la
		// méthode supprimer
		cem.close();
		aem.close();
		// Parcours de listeDeSesCommandes et destruction
		for (Commande c : listeDeSesCommandes) {
			leService.supprimer(c);
		}
	}

	// Liste de tous les codes articles des commandes_articles liés aux
	// commandes où le login est égal à celui en paramètre
	public List<String> listeCommande(String login) {
		// Les différents EntityManagers
		CommandeEntityManager cem = new CommandeEntityManager();
		CommandeArticleEntityManager caem = new CommandeArticleEntityManager();
		AdherentEntityManager aem = new AdherentEntityManager();
		// Liste en résultat
		List<String> listeRes = new ArrayList<String>();
		// Pour trouver les commandes d'un adhérent
		List<Commande> listeDeSesCommandes = cem
				.chercherCommandeDunAdherent(aem.trouver(login));
		// Parcours de listeDeSesCommandes
		for (Commande c : listeDeSesCommandes) {
			// Pour trouver les articles de la commande c
			List<CommandeArticle> listeContenu = caem
					.chercherCommandeArticleAdh(c);
			for (CommandeArticle ca : listeContenu) {
				listeRes.add(ca.getKey().getArticle());
			}
		}
		aem.close();
		caem.close();
		cem.close();
		return listeRes;
	}
}