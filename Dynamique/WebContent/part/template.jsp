<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css" />
<title>Assoc ENM</title>
</head>
<body>
	<%-- Template de vue--%>
	<%-- On charge le header --%>
	
	<%-- C'est au template de determiner quel portion de page montrer suivant que l'on est co ou non --%>
	<%--Si l'utilisateur est connecté --%>
	<%if ((request.getSession(false) != null) && (request.getSession(false).getAttribute("login") != null)){%>
	<%-- On charge le menu --%>
		
	<%-- On vérifie l'existence d'un parametre page --%>
	
	<%-- Si non page d'acceuil --%>
	<jsp:include page="/part/accueil.jsp" /> 
	<%-- Si oui on choisit la page indiqué --%>
	
	<%--Chaque partie de template devra vérifier l'existence de certain attribut pour voir si il dispose des données --%>
	
	<%--Si l'utilisateur n'est pas connecté --%>
	<%}else{ %>
		<%--Si on a choisit de créer un compte: on affiche la page de création d'un compte --%>
		<% if (request.getAttribute("Page")!=null && request.getAttribute("Page").equals("/newAccount")){ %>
			<jsp:include page="/part${Page}.jsp" /> 
		<%--Sinon --%>
		<% }else{ %>
			<jsp:include page="/part/login.jsp" /> 
		<% } %>
	<%} %>
</body>
</html>