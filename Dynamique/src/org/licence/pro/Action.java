package org.licence.pro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Action
 * Servlet controleur récuperation de données et création de paramêtre
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
		System.out.println(request.getParameter("login") + request.getParameter("mdp"));
		if ((request.getParameter("login")!=null) && (request.getParameter("mdp") != null)) {
			if ((!request.getParameter("login").equals("")) && (!request.getParameter("mdp").equals(""))) {
			//TODO:A partir du jpa ici il faudra vérifié si la personne existe
			//On enregistre en session un attribut "login" cela signifie que la personne est connéecté
			request.getSession(true).setAttribute("login", new String(request.getParameter("login")));
			}
		}else{
			request.getSession().invalidate();
		}
		getServletContext().getRequestDispatcher("/part/template.jsp").forward(request, response);
	}
	
	//TODO:Template des pages (priorité pour le login) +css
	//TODO:Gestion des connections
	//TODO:Gestion de la navigation (parametre page)
	//TODO:Gestion de données en important JPA

}
