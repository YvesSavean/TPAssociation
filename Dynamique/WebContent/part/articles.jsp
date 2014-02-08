<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
	<h1>Article</h1>
	<Table border=1>
		<tr>
			<th>Code</th>
			<th>Nom</th>
			<th>Prix</th>
			<th>Stock</th>
			<th></th>
		</tr>
		<!-- Si on a l'attribut commande si on a pour ce login des commandes -->
		<c:choose>
			<%-- Pas encore testé peut etre foireux --%>
			<c:when test="${!empty articles}">
				<c:forEach items="${articles}" var="article" varStatus="status">
					<tr>
   						<td>${article.code}</td>
   						<td>${article.nom}</td>
   						<td>${article.prix}</td>
   						<td><a href='?addArticle=${article.code}'>Commander</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>Il n'existe aucun articles pour le moment!</p>
			</c:otherwise>
		</c:choose>
	</Table>