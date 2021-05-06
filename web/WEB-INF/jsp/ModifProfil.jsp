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
    <div class="form">
<%--        <%= request.getParameter("pwVerification")%>--%>
<%--        <%= request.getParameter("newPwVerification")%>--%>
        <form id="inscription-form" action = "${pageContext.request.contextPath}/majprofil" method="post">
            <input type="text" placeholder="Pseudo" id="username" name="username" value="${user.getPseudo()}" required readonly/>
            <input type="text" placeholder="Prénom" id="firstName" name="firstName" value="${user.getPrenom()}" required />
            <input type="text" placeholder="Nom" id="lastName" name="lastName" value="${user.getNom()}" required />
            <input type="tel" placeholder="Téléphone" id="phoneNumber" name="phoneNumber" value="${user.getTel()}" required />
            <input type="email" placeholder="email" id="mail" name="mail" value="${user.getEmail()}" required readonly />
            <input type="text" placeholder="Adresse" id="adresse" name="adresse" value="${user.getRue()}" required />
            <input pattern="[0-9]{5}" type="text" placeholder="Code Postal" name="cp" value="${user.getCP()}" required />
            <input type="text" placeholder="Ville" id="city" name="city" value="${user.getVille()}" required />
            <input type="password" placeholder="Mot de passe" id="password" name="password" required />
            <input type="password" placeholder="Mot de passe" id="newpassword" name="newpassword" />
            <input type="password" placeholder="Confirmer mot de passe" id="confirmPassword" name="confirmPassword" />
        </form>
    </div>
    <div class="buttonGroupe">
        <button form="inscription-form" type="submit" title="create"  value="create"> Créer </button>
        <button form="inscription-form" type="submit" title="cancel" value="cancel"> Annuler</button>
    </div>
</section>
<div class="illu illu_right">
    <%@include file="assets/inscriptionIlluRight.svg" %>
</div>
</body>
</html>
