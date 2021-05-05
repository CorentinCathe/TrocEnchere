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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/Inscription.css">

    <title>Create Account</title>
<body>
<div class="illu illu_left">
    <%@include file="assets/inscrptionIlluLeft.svg" %>
</div>
<section class="content">
    <div class="title">
        <h1>Mon Profil</h1>
    </div>
        <% Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");%>
        <ul>
            <li>pseudo <%= utilisateur.getPseudo() %> !</li>
            <li>nom <%= utilisateur.getNom() %> !</li>
            <li>prenom <%= utilisateur.getPrenom() %> !</li>
            <li>email <%= utilisateur.getEmail() %> !</li>
            <li>tel <%= utilisateur.getTel() %> !</li>
            <li>rue <%= utilisateur.getRue() %> !</li>
            <li>cp <%= utilisateur.getCP() %> !</li>
            <li>ville <%= utilisateur.getVille() %> !</li>
        </ul>
    </div>
    <div>
        <form action="${pageContext.request.contextPath}/majprofil" method="get">
            <input type="submit" value="majprofil">
        </form>
    </div>
</section>
<div class="illu illu_right">
    <%@include file="assets/inscriptionIlluRight.svg" %>
</div>
</body>
</html>
