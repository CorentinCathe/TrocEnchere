<%--
  Created by IntelliJ IDEA.
  User: maxou
  Date: 04/05/2021
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.Locale" %>
<%@ page import="java.io.*,java.time.LocalDate" %>
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
                <input type="text" placeholder="Le nom de l'article" id="articlename" name="articlename" value=${articlename}>
                <label for="categorieList">Catégorie : </label>
                <select name="categorieSelection" id="categorieList">
                    <option class="categorie" value="0" selected>Toutes</option>
                    <c:forEach items="${listCat}" var="cat">
                        <option class="categorie" value=${cat.id} <c:if test="${ cat.id == categorieSelection }"> selected</c:if>>${cat.libelle}</option>
                    </c:forEach>
                </select>
                <c:if test= "${connected != null && connected}">
                    <div>
                        <div>
                            <input type="radio" value="achat" name="radio" id="radioAchat" onclick="disableButton()" <c:if test="${ radio == \"achat\" }"> checked</c:if>>
                            <label for="radioAchat">Achat</label>
                            <input type="checkbox" class="check1" value="ouverte" name="checkAchatOuverte" id="checkAchatOuverte" <c:if test="${ checkAchatOuverte != null }"> checked</c:if>>
                            <label for="checkAchatOuverte">enchères ouvertes</label>
                            <input type="checkbox" class="check1" value="mesEncheres" name="checkAchatmesEncheres" id="checkAchatmesEncheres" <c:if test="${ checkAchatmesEncheres != null }"> checked</c:if>>
                            <label for="checkAchatmesEncheres">mes enchères</label>
                            <input type="checkbox" class="check1" value="mesEncheresWin" name="checkAchatWin" id="checkAchatWin" <c:if test="${ checkAchatWin != null }"> checked</c:if>>
                            <label for="checkAchatWin">mes enchères remportées</label>
                        </div>
                        <div>
                            <input type="radio" value="vente" name="radio" id="radioVente" onclick="disableButton()" <c:if test="${ radio == \"vente\" }"> checked</c:if>>
                            <label for="radioVente">Vente</label>
                            <input type="checkbox" class="check2" value="enCours" name="checkVenteEnCours" id="checkVenteEnCours" <c:if test="${ checkVenteEnCours != null }"> checked</c:if>>
                            <label for="checkVenteEnCours">mes ventes en cours</label>
                            <input type="checkbox" class="check2" value="notStarted" name="checkVenteNotStarted" id="checkVenteNotStarted" <c:if test="${ checkVenteNotStarted != null }"> checked</c:if>>
                            <label for="checkVenteNotStarted">ventes non débutées</label>
                            <input type="checkbox" class="check2" value="mesVentesFinish" name="checkVenteFinish" id="checkVenteFinish" <c:if test="${ checkVenteFinish != null }"> checked</c:if>>
                            <label for="checkVenteFinish">ventes terminés</label>
                        </div>
                    </div>
                </c:if>
            </form>
            <button form="filter-form" class="search-button" type="submit" value="search">Rechercher</button>
        </div>
        <div class="articles-display">
            <ul class="list-article">
                <c:forEach items="${listeArticlesVendus}" var="article">
                    <li class="article">
                        <div class="article-name"><c:if test= "${connected != null && connected}"><a <c:if test="${ article.dateDebutEncheres > localDate}"> href="modifierVente"</c:if> <c:if test="${ article.dateDebutEncheres <= localDate}"> href="DetailVente"</c:if> target="_blank"></c:if><p>${article.nom}</p><c:if test= "${connected != null && connected}"></a></c:if></div>
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

<script language="JavaScript">
    const isDisable = (elem) =>{
        const listNode = document.querySelectorAll(elem);
        listNode.forEach(e => {
            e.disabled = true;
            e.checked = false
        });
    }

    const isEnable = (elem) =>{
        const listNode = document.querySelectorAll(elem);
        listNode.forEach(e => e.disabled = false);
    }

    const switchCheckBox = (radioInput , checkBoxOne , checkBoxTwo) => {
        if(document.getElementById(radioInput).checked){
            isDisable(checkBoxTwo);
            isEnable(checkBoxOne);
        }else{
            isDisable(checkBoxOne);
            isEnable(checkBoxTwo);
        }
    }

    // Pour le premier chargement
    switchCheckBox("radioAchat",".check1",".check2");

    const disableButton = () =>{
        switchCheckBox("radioAchat",".check1",".check2");
    }
</script>
