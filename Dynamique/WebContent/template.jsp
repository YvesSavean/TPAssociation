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
		<c:when test="${! empty sessionScope && ! empty sessionScope.login}">
			
			<%-- On charge le menu --%>
			<jsp:include page="/part/menu.jsp" /> 
			<%-- On v�rifie l'existence d'un parametre page --%>
			<c:choose>
				<c:when test="${empty Page}" >
					<%-- Si non page d'acceuil --%>
					<jsp:include page="/part/accueil.jsp" /> 
					<%-- Si oui on choisit la page indiqu� --%>
				</c:when>
  				<c:otherwise>
					<jsp:include page="/part${Page}.jsp" /> 
					<%--Chaque partie de template devra v�rifier l'existence de certain attribut pour voir si il dispose des donn�es --%>
				</c:otherwise>
			</c:choose>			
	
		<%--Si l'utilisateur n'est pas connect� --%>
		</c:when>
  		<c:otherwise>
  		
			<%--Si on a choisit de cr�er un compte: on affiche la page de cr�ation d'un compte --%>
			<c:choose>
				<c:when test="${!empty Page && Page == '/newAccount'}">
					<jsp:include page="/part${Page}.jsp" /> 
				</c:when>
				<%--Sinon --%>
  				<c:otherwise>
					<jsp:include page="/part/login.jsp" /> 
					<%-- Gestion de erreurs: si une erreur existe alors on a affiche une pop-up d'information --%>
					<c:if test="${!empty Erreur}">
						<script type="text/javascript">alert("${Erreur}");</script>
					</c:if>
				</c:otherwise>
			</c:choose>	
			
		</c:otherwise>
	</c:choose>	
</body>
</html>