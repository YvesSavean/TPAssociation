<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<body>
	<h1>Creation de compte</h1>
	<!--Création du formulaire-->
	<form action="<%=getServletContext().getContextPath()%>/Action" method="post">
		<table>
			<tr>
				<td>Indentifiant</td>
				<td><input type="text" name="newLogin" required/></td>
			</tr>
			<tr>
				<td>Mot de passe</td>
				<td><input type="password" name="mdp" required/></td>
			</tr>
			<tr>
				<td>Mot de Passe (Confirmation)</td>
				<td><input type="password" name="mdpconfirm" required/></td>
			</tr>
			<tr>
				<td>Nom:</td>
				<td><input type="text" name="newNom" required/></td>
			</tr>
			<tr>
				<td>Prenom:</td>
				<td><input type="text" name="newPrenom" required/></td>
			</tr>
			<tr>
				<td>Adresse:</td>
				<td><input type="text" name="newAdresse" required/></td>
			</tr>
			<tr>
				<td>Complement Adresse:</td>
				<td><input type="text" name="newCompAdresse"/></td>
			</tr>
			<tr>
				<td>Code Postal:</td>
				<td><input type="text" name="newCodepostal" required/></td>
			</tr>
			<tr>
				<td>Ville:</td>
				<td><input type="text" name="newVille" required/></td>
			</tr>
			<tr>
				<td>Pays:</td>
				<td>
					<SELECT name="newPays" size="1">
					<OPTION>FRANCE (FR)</OPTION>
					<OPTION>ROYAUME UNI (UK)</OPTION>
					<OPTION>ALLEMAGNE (AL)</OPTION>
					<OPTION>ESPAGNE (ES)</OPTION>
					<OPTION>ITALIE (IT)</OPTION>
					</SELECT>
				</td>
			</tr>
			<tr>
				<td><input type="submit" name="newCreate" value="Enregistrer"/></td>
				<td><a href='<%=getServletContext().getContextPath()%>/Action'>Retour</a></td>
			</tr>
		</table>
	</form>
	
</body>
