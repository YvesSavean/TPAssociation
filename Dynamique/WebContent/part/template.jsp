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
	<%-- Template de vue: va en fonction de parametre afficher tel ou tel portion de page--%>
	<%if (request.getSession(false) != null && request.getSession(false).getAttribute("login") != null ){%>
	<%-- Si parametre création : chargement de AddAccount.jsp --%>
	
	<%-- On vérifie l'existence d'un parametre page --%>
	<%-- Si non page d'acceuil --%>
	<%-- Si oui on choisit la page indiqué --%>
	<%}else{ %>
	<%-- Cas session login inexistante: chargement de login.jsp --%>
		<jsp:include page="/part/login.jsp" /> 
	<%} %>
</body>
</html>