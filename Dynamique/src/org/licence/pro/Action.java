package org.licence.pro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Adherent;
import EntityManager.AdherentEntityManager;

/**
 * Servlet implementation class Action
 * Servlet controleur r�cuperation de donn�es et cr�ation de param�tre
 */
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**Listes des objets pour l'acces aux donn�es*/
	private AdherentEntityManager adherentManagers;
       
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
				//TODO:Cr�e une liste de type commandes_articles
				//TODO:En utilisant la persistance appeller une m�thode qui renvoit pour un id adh�rent l'ensemble des articles command�s
				//TODO:Pour chaque id d'article r�cuperer trouver son nom et prenom avec l'appel d'une m�thode de jpa
				//TODO: On enregistre dans une liste
				//TODO:Si pas null alors on enregistre cette liste dans un attribut
			}
			if (request.getAttribute("Page").equals("/articles")){
				System.out.println("Liste Articles");
				//TODO:Cr�e une liste de type commandes_articles
				//TODO:En utilisant la persistance appeller une m�thode qui renvoit tout les articles disponibles
				//TODO:On enregistre dans une liste
				//TODO:Si pas null alors on enregistre cette liste dans un attribut
			}
		}
		
		/*Gestion des actions*/
		//L'ensemble des actions particuli�res (cr�ation porfile,ajout article seront trait� ici).
		//Cas on cr�e un compte page newAccount 
		if(request.getParameter("newCreate")!=null){
			System.out.println("Cr�ation compte");
			//TODO : V�rifier que les champs obligatoire sont remplit
			//TODO : V�rifier que les mdp sont indentiques
			//TODO : V�rifier que le champ code postal est correct (optionel)
			//TODO : Si ok, JPA ajout d'un compte dans le bdd
		}
		//Cas on annule le cr�ation d'un compte
		//Cas on supprime les commandes
		if(request.getParameter("annulerCmd")!=null){
			System.out.println("Annuler commande");
			//TODO: Supression de la liste des commandes pour un adh�rent donn�e renvoit une liste de code des articles supprim�s
			//TODO: Maj du stock des articles indiqu�s en parametre
		}
		//Cas on rajoute une commande
		if(request.getParameter("addCmd")!=null){
			System.out.println("Ajout article dans commande");
			//TODO:V�rifier que le stock articles n'est pas null: methode checkArticle(int idarticle)
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
		//TODO:Compeleter code JPA cr�er compte
		//TODO:Test HttpUnit optionel
		//TODO:Css optionel
		
	}
	
}
