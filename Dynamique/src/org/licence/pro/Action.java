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
<<<<<<< HEAD
		//Si on vient de remplir le formulaire
=======
		//Si on vient de remplir le formulaire de connection
>>>>>>> d8951848a894393b4234f690599836e4cc3a70bc
		if ((request.getParameter("login")!=null) && (request.getParameter("mdp") != null)) {
			if(adherentManagers != null){
				if( adherentManagers.trouver(request.getParameter("login"))!= null){
					Adherent ad = adherentManagers.trouver(request.getParameter("login"));
					//V�rifier le mdp
					if (request.getParameter("mdp").equals(ad.getMotDePasse())){
						//On enregistre en session un attribut "login" cela signifie que la personne est connect�
						request.getSession(true).setAttribute("login", new String(request.getParameter("login")));
					}else{
						request.setAttribute("Erreur", "Mdp incorrect");	
					}
				}else{
					//On rajute dans un param�tre un message d'erreur
					request.setAttribute("Erreur", "Identifiant inconnu");
				}		
			}else{
				request.setAttribute("Erreur", "Probl�me avec la BDD");
			}
		}
		
		if(request.getParameter("logout")!=null){
			System.out.println("D�connexion");
			request.getSession().invalidate();
		}
		
		/*Gestion des pages*/
		//Suivant le chemin retourn� par le template on va cr�er un attribut page que le template utilisera pour savoir quoi choisir
		//les href du menu seront comme cela /action et on les r�cuperera avec les methode get path...
		System.out.println(request.getPathInfo());
		if(request.getPathInfo() != null){
			request.setAttribute("Page", request.getPathInfo());
		}
		
		
		/*Gestion des donn�es*/
		//TODO: suivant la page vis� on affichera certaine donn�e issue de l'appelle des m�thode de la JPA 
		
		/*Gestion des actions*/
		//L'ensemble des actions particuli�res (cr�ation porfile,ajout article seront trait� ici).
		if(request.getParameter("Create")!=null){
			System.out.println("Cr�ation compte");
			//TODO : JPA ajout d'un compte dans le bdd
		}
		
		
		
		getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
	}
<<<<<<< HEAD
	
	//TODO:Sc�nario � faire
	//TODO:Template des pages (priorit� pour le login) +css
	//TODO:Gestion des connections
	//TODO:Gestion de la navigation (parametre page)
	//TODO:Gestion de donn�es en important JPA (appeller les m�thodes de requetes de la jpa).

=======
>>>>>>> d8951848a894393b4234f690599836e4cc3a70bc
}
