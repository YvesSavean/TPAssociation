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
import EntityManager.AdherentEntityManager;
import EntityManager.ArticleEntityManager;

/**
 * Servlet implementation class Action
 * Servlet controleur récuperation de données et création de paramêtre
 */
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**Listes des objets pour l'acces aux données*/
	private AdherentEntityManager adherentManagers;
	private ArticleEntityManager articleManagers;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Action() {
        super();
    }

    /**
     * Initalisation des données
     */
    public void init(){
    	adherentManagers = new AdherentEntityManager();
    	articleManagers = new ArticleEntityManager();
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
	
	private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		/*Chargement des objets pour acceder aux données*/
		init();	
		/*Gestion des session*/
		//Si on vient de remplir le formulaire
		//Si on vient de remplir le formulaire de connection

		if ((request.getParameter("login")!=null) && (request.getParameter("mdp") != null)) {
			if(adherentManagers != null){
				if( adherentManagers.trouver(request.getParameter("login"))!= null){
					Adherent ad = adherentManagers.trouver(request.getParameter("login"));
					//Vérifier le mdp
					if (request.getParameter("mdp").equals(ad.getMotDePasse())){
						//On enregistre en session un attribut "login" cela signifie que la personne est connecté
						request.getSession(true).setAttribute("login", new String(request.getParameter("login")));
					}else{
						request.setAttribute("Erreur", "Connection refusé:  Mdp incorrect");	
					}
				}else{
					//On rajute dans un paramêtre un message d'erreur
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
		//Suivant le chemin retourné par le template on va créer un attribut page que le template utilisera pour savoir quoi choisir
		//les href du menu seront comme cela /action et on les récuperera avec les methode get path...
		if(request.getPathInfo() != null){
			request.setAttribute("Page", request.getPathInfo());
		}
		
		
		/*Gestion des données*/
		//On vérifie la page que l'on a visé
		if(request.getAttribute("Page")!= null){
			if (request.getAttribute("Page").equals("/commande")){
				System.out.println("Liste Commande");
				//TODO:Crée une liste de type commandes_articles
				//TODO:En utilisant la persistance appeller une méthode qui renvoit pour un id adhérent l'ensemble des articles commandés
				//TODO:Pour chaque id d'article récuperer trouver son nom et prenom avec l'appel d'une méthode de jpa
				//TODO: On enregistre dans une liste
				//TODO:Si pas null alors on enregistre cette liste dans un attribut
			}
			if (request.getAttribute("Page").equals("/articles")){
				System.out.println("Liste Articles");
				//Crée une liste de type commandes_articles
				List<Article> articles = new ArrayList<Article>();
				//En utilisant la persistance appeller une méthode qui renvoit tout les articles disponibles
				articles = articleManagers.ChercherArticles();
				//Si pas null ou vide alors on enregistre cette liste dans un attribut
				if(articles != null && !articles.isEmpty()){
					request.setAttribute("articles", articles);
				}	
			}
		}
		
		/*Gestion des actions*/
		//L'ensemble des actions particuliéres (création porfile,ajout article seront traité ici).
		//Cas on crée un compte page newAccount 
		if(request.getParameter("newCreate")!=null){
			//Vérifier que les champs obligatoire sont remplit (cas ou le html5 avec required ne marche pas sur le navigateur)
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
							if (request.getParameter("newCompAdresse")!=null || request.getParameter("newCompAdresse").equals(" ")){
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
		//Cas on annule le création d'un compte
		//Cas on supprime les commandes
		if(request.getParameter("annulerCmd")!=null){
			System.out.println("Annuler commande");
			//TODO: Supression de la liste des commandes pour un adhérent donnée renvoit une liste de code des articles supprimés
			//TODO: Maj du stock des articles indiqués en parametre
		}
		//Cas on rajoute une commande
		if(request.getParameter("addCmd")!=null){
			System.out.println("Ajout article dans commande");
			//TODO:Vérifier que le stock articles n'est pas null: methode checkArticle(int idarticle)
			//TODO:Si true : message d'erreur sinon on appelle une méthode addArtcle (id Adehrent, id article) qui rajoute l'article dans la liste des commandes de l'adherent
		}
		
		
		
		//Appel du template
		getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
		
		//Convertir template en JSTL OK
		//Finir les templates (utiliser JSTL + EL) OK
		//TODO:Améliorer site: 
			//TODO:Liens par défault
			//Bloquer accées direct jsp OK
			//Bouton annuler dans crée compte OK
		//JPA: mise en place de la structure OK
		//Completer code JPA créer compte: rajouter tyoe password + complément adresse
		//TODO:Implementer les méthodes jpa
		//TODO:Test HttpUnit optionel
		//TODO:Css optionel
		
	}
	
}
