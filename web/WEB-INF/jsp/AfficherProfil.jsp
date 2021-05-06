<%@ page import="bo.Utilisateur" %>
<!DOCTYPE html>
<html lang="fr">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" type="image/png" href="./assets/shopping-cart.png"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/AfficherProfil.css">

    <title>Create Account</title>
<body>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<section class="content">
    <div class="container">
        <h1 class="title">Mon Profil</h1>
        <% Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");%>
        <ul class="list-info">
            <li class="info"><p>pseudo :</p>: ${user.getPseudo()} </li>
            <li class="info"><p>nom :</p>${user.getNom()} </li>
            <li class="info"><p>prenom :</p>${user.getPrenom()} </li>
            <li class="info"><p>email :${user.getEmail()} </li>
            <li class="info"><p>tel :</p>${user.getTel()} </li>
            <li class="info"><p>rue :</p>${user.getRue()} </li>
            <li class="info"><p>cp :</p>${user.getCP()} </li>
            <li class="info"><p>ville :</p>${user.getVille()} </li>
        </ul>
        <div>
            <form action="${pageContext.request.contextPath}/majprofil" method="get">
                <button class="maj-button" type="submit" value="majprofil">Mettre à jour</button>
            </form>
        </div>
    </div>
    <div class="illu">
        <%@include file="assets/illuInfProfil.svg" %>
    </div>
    </div>
</section>
</body>
</html>
