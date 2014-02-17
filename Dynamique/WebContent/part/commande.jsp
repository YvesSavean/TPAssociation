<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

	<h1>Commandes</h1>
	<Table border=1>
		<tr>
			<th>Code</th>
			<th>Nom</th>
			<th>Prix</th>
		</tr>
		<!-- Si on a l'attribut commande si on a pour ce login des commandes -->
		<c:choose>
			<c:when test="${!empty articlesCommandes}">
				<c:forEach items="${articlesCommandes}" var="articlecommande" varStatus="status">
					<tr>
   						<td>${articlecommande.code}</td>
   						<td>${articlecommande.nom}</td>
   						<td>${articlecommande.prix}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>Vous n'avez commandé aucun articles!</p>
			</c:otherwise>
		</c:choose>
	</Table>
		<a href='?annulerCmd=true'>Annuler la commande</a>
