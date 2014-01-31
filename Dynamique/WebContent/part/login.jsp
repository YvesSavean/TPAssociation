<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
    
	<h1>Login</h1>
	<form action="" method="post" id="formLogin" >
		    <p>Identifiant:<input type="text" name="login" id="login" ></p>
		    <p>Mdp:<input type="password" name="mdp" id="mdp" ><p>
		    <p><input type="submit" value="OK" /><p>
	</form>
	<p>Pas encore inscrit? <a href='<%=getServletContext().getContextPath()%>/Action/newAccount'>Crée votre compte ici!</a>
