<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css" />
<title>Assoc ENM</title>
</head>
<body>
	<%-- Template general pour cahrger la vue--%>
	
	<%--Si l'utilisateur est connecté --%>
	<c:choose>
		<c:when test="${! empty sessionScope && ! empty sessionScope.login}">
			
			<%-- On charge le menu --%>
			<jsp:include page="/part/menu.jsp" /> 
			<%-- On vérifie l'existence d'un parametre page --%>
			<c:choose>
				<%-- Si on a pas le parametre: page d'acceuil --%>
				<c:when test="${empty Page}" >
					<jsp:include page="/part/accueil.jsp" /> 
				</c:when>
				<%--  Si on a le parametre: on choisit la page indiqué --%>
  				<c:otherwise>
					<jsp:include page="/part${Page}.jsp" /> 
					<%--Chaque partie de template vérifie l'existence de certain attribut--%>
				</c:otherwise>
			</c:choose>			
	
		<%--Si l'utilisateur n'est pas connecté --%>
		</c:when>
  		<c:otherwise>
			<%--Si on a choisit de créer un compte: on affiche la page de création d'un compte --%>
			<c:choose>
				<c:when test="${!empty Page && Page == '/newAccount'}">
					<jsp:include page="/part${Page}.jsp" /> 
				</c:when>
				<%--Sinon --%>
  				<c:otherwise>
					<jsp:include page="/part/login.jsp" /> 
				</c:otherwise>
			</c:choose>	
		</c:otherwise>
	</c:choose>	
	
	<%-- Gestion de erreurs: si une erreur existe alors on l'affiche dans une pop-up d'information --%>
	<c:if test="${!empty Erreur}">
		<script type="text/javascript">alert("${Erreur}");</script>
	</c:if>
</body>
</html>