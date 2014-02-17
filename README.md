TPAssociation
=============

Configuration :

Base de données :
Lancer Derby sur le pc
puis configurer derby sur eclipse window preference
puis new Derby sur eclipse perspective Database Development >
  name : DerbyBDDAssociation
  data base : DerbyBDDAssociation
  host : localhost
  port : 1527
  login : sony
  mdp : sony
  schéma : sony

Les trois méthodes restantes à faire pour les traitements sur le site dans commandeEntityManager:

public void ajoutArticleCommande(String codeArticle,String Login){
    //Ajouter un article dans une commande_article mais avant créer la commande si besoin
}
-------> Ajout Commande et ses CommandesArticle
Attention : L'id commande non automatique

Map<Integer,CommandeArticle> lesLignesArticles = new HashMap<Integer,CommandeArticle>();
lesLignesArticles.put(1, laLigne);
Service leService = new Service();
leService.Ajout(cmd,lesLignesArticles);

public void viderCommande(String login){
    //Supression de toutes les commandes (puis des commandes_articles liés) ou le login est égal à celui en parametres
}
------> Supprime la commande donnée en paramêtre (ainsi que les commandeArticle)
Service leService = new Service();
leService.supprimer(cmd);

public List(String) listeCommande(String login){
    //Listes de tous les codes articles des commandes_articles liés aux commandes où le login est égal à celui en paramètres
}
------> Pour trouver les commandes d'un adhérent :
List<Commande> listDeSesCommandes = cem.chercherCommandeDunAdherent(adh);
------> Pour trouver les articles d'une commande : 
List<CommandeArticle> listCommandeArticle = caem.chercherCommandeArticleAdh(laCommande)

