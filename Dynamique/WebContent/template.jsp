<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
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
	<%-- C'est au template de determiner quel portion de page montrer suivant 
	que l'on est co ou non --%>
	
	<%--Si l'utilisateur est connect� --%>
	<c:choose>
		<!-- Faire un c:out -->
		<c:when test="${!empty sessionScope} && ${!empty sessionScope.login}">
		<%--if ((request.getSession(false) != null) && (request.getSession(false).getAttribute("login") != null)){--%>
			
			<%-- On charge le menu --%>
			<jsp:include page="/part/menu.jsp" /> 
			<%-- On v�rifie l'existence d'un parametre page --%>
			<% if (request.getAttribute("Page")==null){ %>
				<%-- Si non page d'acceuil --%>
				<jsp:include page="/part/accueil.jsp" /> 
				<%-- Si oui on choisit la page indiqu� --%>
			<%}else{%>
				<jsp:include page="/part${Page}.jsp" /> 
				<%--Chaque partie de template devra v�rifier l'existence de certain attribut pour voir si il dispose des donn�es --%>
			<% } %>
			
		<%--Si l'utilisateur n'est pas connect� --%>
		
		</c:when>
  		<c:otherwise>
		
			<%--Si on a choisit de cr�er un compte: on affiche la page de cr�ation d'un compte --%>
			<% if (request.getAttribute("Page")!=null && request.getAttribute("Page").equals("/newAccount")){ %>
				<jsp:include page="/part${Page}.jsp" /> 
			<%--Sinon --%>
			<% }else{ %>
				<jsp:include page="/part/login.jsp" /> 
				<%-- Gestion de erreurs: si une erreur existe alors on a affiche une pop-up d'information --%>
				<%if (request.getAttribute("Erreur")!=null){ %>
					<script type="text/javascript">alert("<%= request.getAttribute("Erreur") %>");</script>
				<% } %>
			<% } %>
		</c:otherwise>
	</c:choose>	
</body>
</html>