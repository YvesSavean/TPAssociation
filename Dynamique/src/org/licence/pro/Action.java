package org.licence.pro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Action
 * Servlet controleur r�cuperation de donn�es et cr�ation de param�tre
 */
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Action() {
        super();
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
		/*Gestion des session*/
		//Si on vient de remplir le formulaire
		if ((request.getParameter("login")!=null) && (request.getParameter("mdp") != null)) {
			if ((!request.getParameter("login").equals("")) && (!request.getParameter("mdp").equals(""))) {
			//TODO:A partir du jpa ici il faudra v�rifi� si la personne existe
			//On enregistre en session un attribut "login" cela signifie que la personne est conn�ect�
			request.getSession(true).setAttribute("login", new String(request.getParameter("login")));
			}
		}else{
			request.getSession().invalidate();
		}
		getServletContext().getRequestDispatcher("/part/template.jsp").forward(request, response);
	}
	
	//TODO:Sc�nario � faire
	//TODO:Template des pages (priorit� pour le login) +css
	//TODO:Gestion des connections
	//TODO:Gestion de la navigation (parametre page)
	//TODO:Gestion de donn�es en important JPA (appeller les m�thodes de requetes de la jpa).

}
