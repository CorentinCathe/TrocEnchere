<%@ page import="bo.Utilisateur" %>
<!DOCTYPE html>
<html lang="fr">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" type="image/png" href="./assets/shopping-cart.png" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/AfficherProfil.css">

    <title>Create Account</title>
<body>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<section class="content">
    <div class="title">
        <h1>Mon Profil</h1>
    </div>
        <% Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");%>
        <ul class="list-info">
            <li class="info">pseudo :<%= utilisateur.getPseudo() %> !</li>
            <li class="info">nom :<%= utilisateur.getNom() %> !</li>
            <li class="info">prenom :<%= utilisateur.getPrenom() %> !</li>
            <li class="info">email :<%= utilisateur.getEmail() %> !</li>
            <li class="info">tel :<%= utilisateur.getTel() %> !</li>
            <li class="info">rue :<%= utilisateur.getRue() %> !</li>
            <li class="info">cp :<%= utilisateur.getCP() %> !</li>
            <li class="info">ville :<%= utilisateur.getVille() %> !</li>
        </ul>
    </div>
    <div>
        <form action="${pageContext.request.contextPath}/majprofil" method="get">
            <button class="maj-button" type="submit" value="majprofil">Mettre à jour</button>
        </form>
    </div>
    <div class="illu">
        <%@include file="assets/illuInfProfil.svg" %>
    </div>
</section>
</body>
</html>
