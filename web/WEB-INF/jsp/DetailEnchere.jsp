<%--
  Created by IntelliJ IDEA.
  User: maxou
  Date: 04/05/2021
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bo.UtilisateurBO" %>
<html>
<head>
    <title>Accueil</title>
</head>
<body>
<%@ include file="/WEB-INF/fragments/header.jspf" %>

<section class="content">
    <div class="container">
        <h1 class="title">Détails vente</h1>
        <div class="vente-info">
            <ul class="info">
                <li class="info"><p>${enchere.getArticle().getNom()}</p></li>
                <li class="info"><p>Description :</p>${enchere.getArticle().getDescription()} </li>
                <li class="info"><p>Catégorie :</p>${enchere.getArticle().getCategorie().getLibelle()}</li>
                <li class="info"><p>Meilleur offre :</p>${enchere.getMontant()}<p> par </p>${enchere.getUtilisateur().getPseudo()} </li>
                <li class="info"><p>Mise à prix :</p>${enchere.getArticle().getPrixInitial()}</li>
                <li class="info"><p>Fin de l'enchère :</p>${enchere.getArticle().getDateFinEncheres()}</li>
                <li class="info">
                    <p>Retrait :</p>
                    <ul>
                        <li>${retrait.getRue()}</li>
                        <li>${retrait.getCP()}</li>
                        <li>${retrait.getVille()}</li>
                    </ul>
                </li>
                <li class="info"><p>Vendeur :</p>${enchere.getArticle().getUtilisateur().getPseudo()}</li>
            </ul>
        </div>
        <div class="enchere-input">
            <p style="color: red">${message}</p>
            <form id="form" action="${pageContext.request.contextPath}/enchere" method="post">
                <input type="number" placeholder="offre" id="offre" name="offre" value="${enchere.getMontant()+10}"/>
                <button form="form" type="submit" title="encherir"  value="encherir"> Enchérir </button>
            </form>
        </div>
    </div>
</section>
</body>
</html>


