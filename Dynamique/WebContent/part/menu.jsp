<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <table border=1>
    	<tr>
    		<td><a href='<%=getServletContext().getContextPath()%>/Action/accueil'>Accueil</a></td>
    		<td><a href='<%=getServletContext().getContextPath()%>/Action/commande'>Commande</a></td>
    		<td><a href='<%=getServletContext().getContextPath()%>/Action/articles'>Articles</a></td>
    		<td colspan=5> Site des adherents de l'association</td>
    		<td>Adherent: ${sessionScope.login} 
    			<form action="?page=accueil" method="post" id="formLogout" >
					<input type="submit" name="logout" value="Déconnexion" />
				</form>
			</td>
    	</tr>
    	
    
    
    </table>