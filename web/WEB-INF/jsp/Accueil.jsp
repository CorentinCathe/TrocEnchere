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
        <h1 class="title">Liste des Articles en vente</h1>
        <div class="filter">
            <p>Filtres :</p>
            <form id="filter-form" action="${pageContext.request.contextPath}/accueil" method="get">
                <input type="text" placeholder="Le nom de l'article" id="articlename" name="articlename">
                <label for="categorieList">Catégorie : </label>
                <select name="categorieSelection" id="categorieList">
                    <option class="categorie" value="0" selected>Toutes</option>
                    <option class="categorie" value="1">Informatique</option>
                    <option class="categorie" value="2">Ammeublement</option>
                    <option class="categorie" value="3">Sport & Loisirs</option>
                    <option class="categorie" value="4">Vêtement</option>
                </select>
            </form>
            <button form="filter-form" class="search-button" type="submit" value="search">Rechercher</button>
        </div>
        <div class="articles-display">
            <ul class="list-article">
                <c:forEach items="${listeArticlesVendus}" var="article">
                    <li class="article">
                        <div class="article-name"><p>${article.nom}</p></div>
                        <div class="article-price"><p>Prix : ${article.prixVente} points</p></div>
                        <div class="article-date-fin"><p>Fin des enchères : ${article.dateFinEncheres}</p></div>
                        <div class="vendeur"><p>Vendeur : ${article.utilisateur.pseudo}</p></div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</section>
</body>
</html>


