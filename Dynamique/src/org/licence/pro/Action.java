package org.licence.pro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Adherent;
import Bean.Article;
import Bean.CommandeArticle;
import EntityManager.AdherentEntityManager;
import EntityManager.ArticleEntityManager;

/**
 * Servlet implementation class Action
 * Servlet controleur/traitement: récuperation des données depuis la JPA et création de paramêtre qu'on envoit à la vue
 */
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**Listes des objets pour l'acces aux données*/
	private AdherentEntityManager adherentManagers;
	private ArticleEntityManager articleManagers;
	//TODO a activer: private CommandeEntityManager commandeManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Action() {
        super();
    }

    /**
     * Initalisation des EntityManagers utilisé pour manipuler les donnees
     */
    public void init(){
    	adherentManagers = new AdherentEntityManager();
    	articleManagers = new ArticleEntityManager();
    	//TODO a activer: commandeManager = new CommandeManager();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	
	/**
	 * Methode qui réalise tout les traitements 
	 */
	private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		/*Chargement des objets pour acceder aux données*/
		init();	
		
		
		/*Gestion des session*/
		//Si on vient de remplir le formulaire de connection
		if ((request.getParameter("login")!=null) && (request.getParameter("mdp") != null)) {
			if(adherentManagers != null){
				//Si le login correspond bien à un adhérent
				if( adherentManagers.trouver(request.getParameter("login"))!= null){
					Adherent ad = adherentManagers.trouver(request.getParameter("login"));
					//On vérifie le mdp
					if (request.getParameter("mdp").equals(ad.getMotDePasse())){
						//On enregistre en session un attribut "login" cela signifie que la personne est connecté
						request.getSession(true).setAttribute("login", new String(request.getParameter("login")));
					}else{
						//On rajoute dans un paramêtre un message d'erreur: Sera affiché par la vue pour informer l'utilisateur
						request.setAttribute("Erreur", "Connection refusé:  Mdp incorrect");	
					}
				}else{
					request.setAttribute("Erreur", "Connection refusé: Identifiant inconnu");
				}		
			}else{
				request.setAttribute("Erreur", "Connection refusé: Probléme avec la BDD");
			}
		}
		
		if(request.getParameter("logout")!=null){
			request.getSession().invalidate();
		}
		
		/*Gestion des pages*/
		//Suivant le chemin retourné par le template, on va créer un attribut page que le template utilisera
		//les href du menu sont comme cela ".../accueil" et on les récuperera avec les methode get path...
		if(request.getPathInfo() != null){
			request.setAttribute("Page", request.getPathInfo());
		}
		
		
		/*Gestion des données*/
		//On vérifie la page que l'on a visé
		if(request.getAttribute("Page")!= null){
			
			//On vise la page commande
			if (request.getAttribute("Page").equals("/commande")){
				//Liste pour récuperer les codes de tous les articles commandés
				List<String> articlesCommandes = new ArrayList<String>();
				//Liste pour récuperer l'ensemble des articles commandés
				List<Article> articles = new ArrayList<Article>();
				
				//En utilisant la persistance,on appelle une méthode qui renvoit pour un login adhérent l'ensemble des codes articles commandés
				//TODO a activer:articlesCommandes = commandeManager.listeCommande(request.getSession(true).getAttribute("login");
				//Test temporaire à virer
				articlesCommandes.add("aq");
				articlesCommandes.add("ec");
				
				//Pour chaque code d'article récuperé on cherche l'article qui le concerne avec la JPA pour avoir toutes les infos
				for (String code: articlesCommandes){
					Article a = articleManagers.trouver(code);
					//Si a n'est pas null
					if(a != null){
						//On enregistre dans une liste
						articles.add(a);
					}
				}
				
				//Si la liste des articles retrouvés n'est pas null alors on enregistre cette liste dans un attribut
				if(articles != null && !articles.isEmpty()){
					request.setAttribute("articlesCommandes", articles);
				}	
			}
			
			//On vise la page des articles
			if (request.getAttribute("Page").equals("/articles")){
				//On crée une liste de type commandes_articles
				List<Article> articles = new ArrayList<Article>();
				//En utilisant la persistance appeller une méthode qui renvoit tout les articles disponibles
				articles = articleManagers.ChercherArticles();
				//Si articles n'est pas null ou vide alors on enregistre cette liste dans un attribut
				if(articles != null && !articles.isEmpty()){
					request.setAttribute("articles", articles);
				}	
			}
		}
		
		
		/*Gestion des actions*/
		//L'ensemble des actions particuliéres (création porfile,ajout article) seront traité ici.
		
		//Cas on crée un compte (page newAccount) 
		if(request.getParameter("newCreate")!=null){
			//Vérifier que les champs obligatoire sont remplit (au cas ou le html5 avec required ne marche pas sur le navigateur)
			if ((!request.getParameter("newLogin").equals("")) && (!request.getParameter("mdp").equals("")) 
					&& (!request.getParameter("mdpconfirm").equals("")) && (!request.getParameter("newNom").equals(""))
					&& (!request.getParameter("newPrenom").equals("")) && (!request.getParameter("newAdresse").equals(""))
					&& (!request.getParameter("newCodepostal").equals("")) && (!request.getParameter("newVille").equals(""))){
					//Vérifier que le login qui est l'identifiant d'un adherent n'existe pas déja: recherche avec une méthode de la JPA
					if(adherentManagers.trouver(request.getParameter("newLogin"))== null){
						//Vérifier que les mdp sont indentiques
						if(request.getParameter("mdpconfirm").equals(request.getParameter("mdp"))){
							//Création d'un objet adherent qu'on remplit avec nos données
							Adherent adh = new Adherent();
							
							//Données obligatoires
							adh.setIdentifiant(request.getParameter("newLogin"));
							adh.setMotDePasse(request.getParameter("mdp"));
							adh.setPrenom(request.getParameter("newPrenom"));
							adh.setNomDeFamille(request.getParameter("newNom"));
							adh.setAdresse(request.getParameter("newAdresse"));
							adh.setCodePostal(request.getParameter("newCodepostal"));
							adh.setVille(request.getParameter("newVille"));
							adh.setPays(request.getParameter("newPays"));
							
							//Données optionelles
							if (request.getParameter("newCompAdresse")!=null || !request.getParameter("newCompAdresse").equals(" ")){
								adh.setComplementAdresse(request.getParameter("newCompAdresse"));
							}
							
							//Puis appel d'une méthode dans le jpa pour ajouter un adhérent
							adherentManagers.creer(adh);
						}else{
							request.setAttribute("Erreur", "Le mot de passe de confirmation n'est pas indentique au mdp");
						}
					}else{
						request.setAttribute("Erreur", "Le login est déja utilisé");
					}
			}else{
				request.setAttribute("Erreur", "Veuillez remplir tout les champs");
			}
		}
		
		//Cas on supprime les commandes
		if(request.getParameter("annulerCmd")!=null){
			//Liste pour récuperer les codes de tous les articles commandés (nécessaire pour faire la maj du stock)
			List<String> articlesCommandes = new ArrayList<String>();
			//Récuperer l'ensembles des codes des articles commandés
			//TODO a activer:List<String> articlesCommandes = commandeManager.listeCommande(request.getSession(true).getAttribute("login"));
			//Si la liste de commandes n'est pas vide ou null
			if(articlesCommandes != null && !articlesCommandes.isEmpty()){
				//Supression de la liste des commandes pour un adhérent donnée
				//TODO a activer:commandeManager.viderCommande(request.getSession(true).getAttribute("login"));
				//Maj du stock des articles indiqués en parametres
				for (String code: articlesCommandes){
					//TODO a activer:articleManagers.incrementeStock(code);
				}
			}
			
		}
		
		//Cas on rajoute une commande
		if(request.getParameter("addCmd")!=null){
			System.out.println("Ajout article dans commande");
			//Article concerné
			Article art = new Article();
			//Vérifier si l'article choisit existe
			if(articleManagers.trouver(request.getParameter("addCmd"))!= null){
				art = articleManagers.trouver(request.getParameter("addCmd"));
				//Vérifier que le stock articles n'est pas égal à zero
				if(art.getStock() > 0){
					//On decremente son stock (appel d'une méthode de jpa)
					//TODO a activer:articleManagers.decrementeStock(request.getParameter("addCmd"));
					//On rajoute cette article dans la liste des commandes 
					//TODO a activer:commandeManager.ajoutArticleCommande(request.getParameter("addCmd"));
				}else{
					request.setAttribute("Erreur", "L'article n'est plus disponible");
				}
			}
		}
		
		
		
		//Appel du template
		getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);	
	}
	
}
