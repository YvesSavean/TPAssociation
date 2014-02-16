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

Les trois méthodes réstantes à faire pour les traitements sur le site dans commandeEntityManager:

public void ajoutArticleCommande(String codeArticle,String Login){
    //Ajouter un article dans une commande_article mais avant créer la commande si besoin
}

public void viderCommande(String login){
    //Supression de toutes les commandes (puis des commandes_articles liés) ou le login est égal à celui en parametres
}

public List<String> listeCommande(String login){
    //Listes de toutes les codes articles des commandes_articles liés aux commandes où le login est égal à celui en paramètres
}
