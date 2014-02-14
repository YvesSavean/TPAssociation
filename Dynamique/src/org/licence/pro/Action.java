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
 * Servlet controleur r�cuperation de donn�es et cr�ation de param�tre
 */
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**Listes des objets pour l'acces aux donn�es*/
	private AdherentEntityManager adherentManagers;
	private ArticleEntityManager articleManagers;
	//private CommandeEntityManager commandeManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Action() {
        super();
    }

    /**
     * Initalisation des donn�es
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
		
		/*Chargement des objets pour acceder aux donn�es*/
		init();	
		/*Gestion des session*/
		//Si on vient de remplir le formulaire
		//Si on vient de remplir le formulaire de connection

		if ((request.getParameter("login")!=null) && (request.getParameter("mdp") != null)) {
			if(adherentManagers != null){
				if( adherentManagers.trouver(request.getParameter("login"))!= null){
					Adherent ad = adherentManagers.trouver(request.getParameter("login"));
					//V�rifier le mdp
					if (request.getParameter("mdp").equals(ad.getMotDePasse())){
						//On enregistre en session un attribut "login" cela signifie que la personne est connect�
						request.getSession(true).setAttribute("login", new String(request.getParameter("login")));
					}else{
						request.setAttribute("Erreur", "Connection refus�:  Mdp incorrect");	
					}
				}else{
					//On rajute dans un param�tre un message d'erreur
					request.setAttribute("Erreur", "Connection refus�: Identifiant inconnu");
				}		
			}else{
				request.setAttribute("Erreur", "Connection refus�: Probl�me avec la BDD");
			}
		}
		
		if(request.getParameter("logout")!=null){
			request.getSession().invalidate();
		}
		
		/*Gestion des pages*/
		//Suivant le chemin retourn� par le template on va cr�er un attribut page que le template utilisera pour savoir quoi choisir
		//les href du menu seront comme cela /action et on les r�cuperera avec les methode get path...
		if(request.getPathInfo() != null){
			request.setAttribute("Page", request.getPathInfo());
		}
		
		
		/*Gestion des donn�es*/
		//On v�rifie la page que l'on a vis�
		if(request.getAttribute("Page")!= null){
			if (request.getAttribute("Page").equals("/commande")){
				System.out.println("Liste Commande");
				//Liste pour r�cuperer les codes de tous les articles command�s
				List<String> articlesCommandes = new ArrayList<String>();
				//Liste pour r�cuperer l'ensemble des articles command�s
				List<Article> articles = new ArrayList<Article>();
				//En utilisant la persistance appeller une m�thode qui renvoit pour un login adh�rent l'ensemble des codes articles command�s
				//articlesCommandes = commandeManager.listeCommande(request.getSession(true).getAttribute("login");
				//Test
				articlesCommandes.add("aq");
				articlesCommandes.add("ec");
				//Pour chaque code d'article r�cuperer trouver son code le prix et le nom avec l'appel d'une m�thode de jpa
				for (String code: articlesCommandes){
					Article a = articleManagers.trouver(code);
					//On enregistre dans une liste
					articles.add(a);
				}
				//Si pas null alors on enregistre cette liste dans un attribut
				if(articles != null && !articles.isEmpty()){
					request.setAttribute("articlesCommandes", articles);
				}	
			}
			if (request.getAttribute("Page").equals("/articles")){
				System.out.println("Liste Articles");
				//Cr�e une liste de type commandes_articles
				List<Article> articles = new ArrayList<Article>();
				//En utilisant la persistance appeller une m�thode qui renvoit tout les articles disponibles
				articles = articleManagers.ChercherArticles();
				//Si pas null ou vide alors on enregistre cette liste dans un attribut
				if(articles != null && !articles.isEmpty()){
					request.setAttribute("articles", articles);
				}	
			}
		}
		
		/*Gestion des actions*/
		//L'ensemble des actions particuli�res (cr�ation porfile,ajout article seront trait� ici).
		//Cas on cr�e un compte page newAccount 
		if(request.getParameter("newCreate")!=null){
			//V�rifier que les champs obligatoire sont remplit (cas ou le html5 avec required ne marche pas sur le navigateur)
			if ((!request.getParameter("newLogin").equals("")) && (!request.getParameter("mdp").equals("")) 
					&& (!request.getParameter("mdpconfirm").equals("")) && (!request.getParameter("newNom").equals(""))
					&& (!request.getParameter("newPrenom").equals("")) && (!request.getParameter("newAdresse").equals(""))
					&& (!request.getParameter("newCodepostal").equals("")) && (!request.getParameter("newVille").equals(""))){
					//V�rifier que le login qui est l'identifiant d'un adherent n'existe pas d�ja: recherche avec une m�thode de la JPA
					if(adherentManagers.trouver(request.getParameter("newLogin"))== null){
						//V�rifier que les mdp sont indentiques
						if(request.getParameter("mdpconfirm").equals(request.getParameter("mdp"))){
							//Cr�ation d'un objet adherent qu'on remplit avec nos donn�es
							Adherent adh = new Adherent();
							
							//Donn�es obligatoires
							adh.setIdentifiant(request.getParameter("newLogin"));
							adh.setMotDePasse(request.getParameter("mdp"));
							adh.setPrenom(request.getParameter("newPrenom"));
							adh.setNomDeFamille(request.getParameter("newNom"));
							adh.setAdresse(request.getParameter("newAdresse"));
							adh.setCodePostal(request.getParameter("newCodepostal"));
							adh.setVille(request.getParameter("newVille"));
							adh.setPays(request.getParameter("newPays"));
							
							//Donn�es optionelles
							if (request.getParameter("newCompAdresse")!=null || request.getParameter("newCompAdresse").equals(" ")){
								adh.setComplementAdresse(request.getParameter("newCompAdresse"));
							}
							//Puis appel d'une m�thode dans le jpa pour ajouter un adh�rent
							adherentManagers.creer(adh);
						}else{
							request.setAttribute("Erreur", "Le mot de passe de confirmation n'est pas indentique au mdp");
						}
					}else{
						request.setAttribute("Erreur", "Le login est d�ja utilis�");
					}
			}else{
				request.setAttribute("Erreur", "Veuillez remplir tout les champs");
			}
		}
		//Cas on annule le cr�ation d'un compte
		//Cas on supprime les commandes
		if(request.getParameter("annulerCmd")!=null){
			System.out.println("Annuler commande");
			//Liste pour r�cuperer les codes de tous les articles command�s (n�cessaire pour faire la maj du stock
			List<String> articlesCommandes = new ArrayList<String>();
			//Recuperer l'ensembles des codes des articles command�s
			//List<String> articlesCommandes = commandeManager.listeCommande(request.getSession(true).getAttribute("login"));
			//Si la liste de commandes n'est pas vide ou null
			if(articlesCommandes != null && !articlesCommandes.isEmpty()){
				//Supression de la liste des commandes pour un adh�rent donn�e
				//commandeManager.viderCommande(request.getSession(true).getAttribute("login"));
				//Maj du stock des articles indiqu�s en parametres
				for (String code: articlesCommandes){
					//articleManagers.incrementeStock(code);
				}
			}
			
		}
		//Cas on rajoute une commande
		if(request.getParameter("addCmd")!=null){
			System.out.println("Ajout article dans commande");
			//Article concern�
			Article art = new Article();
			//V�rifier si l'article choisit existe
			if(articleManagers.trouver(request.getParameter("addCmd"))!= null){
				art = articleManagers.trouver(request.getParameter("addCmd"));
				//V�rifier que le stock articles n'est pas �gal � zero
				if(art.getStock() > 0){
					//On decremente son stock (appel d'une m�thode de jpa)
					//articleManagers.decrementeStock(request.getParameter("addCmd"));
					//On rajoute cette article dans la liste des commandes 
					//commandeManager.ajoutArticleCommande(request.getParameter("addCmd"));
				}else{
					request.setAttribute("Erreur", "L'article n'est plus disponible");
				}
			}
			
			
			//TODO:Si true : message d'erreur sinon on appelle une m�thode addArtcle (id Adehrent, id article) qui rajoute l'article dans la liste des commandes de l'adherent
		}
		
		
		
		//Appel du template
		getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
		
		//Convertir template en JSTL OK
		//Finir les templates (utiliser JSTL + EL) OK
		//TODO:Am�liorer site: 
			//TODO:Liens par d�fault
			//Bloquer acc�es direct jsp OK
			//Bouton annuler dans cr�e compte OK
		//JPA: mise en place de la structure OK
		//Completer code JPA cr�er compte: rajouter tyoe password + compl�ment adresse
		//Implementer les m�thodes jpa
		
	}
	
}
