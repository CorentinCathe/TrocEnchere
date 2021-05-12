<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxou
  Date: 12/05/2021
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section class="content">
    <div class="container">
        <h1 class="title">Modifier la vente</h1>
        <div class="new-sale">
            <form id="update-form" action="${pageContext.request.contextPath}/modifierVente" method="post">
                <input type="hidden" name="articleId" value="${articleToUpdate.id}">
                <div class="form-item">
                    <label for="articleName">Article : </label>
                    <input type="text" name="articleName" id="articleName" value="${articleToUpdate.nom}" required>
                </div>
                <div class="form-item">
                    <label for="description">Description : </label>
                    <input type="text" name="description" id="description" value="${articleToUpdate.description}" >
                </div>
                <div class="form-item">
                    <label for="categorieList">Catégorie : </label>
                    <select name="categorieSelection" id="categorieList" required>
                        <c:forEach items="${listCat}" var="var">
                            <option class="categorie" value=${var.id} <c:if test="${var.id == articleToUpdate.categorie.id}"> selected </c:if>  >${var.libelle}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-item">
                    <label for="photo">Photo de l'article </label>
                    <input type="button" name="upload" id="photo" value="upload">
                </div>
                <div class="form-item">
                    <label for="initialPrice">Mise à prix : </label>
                    <input type="number" name="initialPrice" id="initialPrice" value="${articleToUpdate.prixInitial}" required>
                </div>
                <div class="form-item">
                    <label for="debutEnchere">Début de l'enchère : </label>
                    <input type="date" name="debutEnchere" id="debutEnchere" value="${articleToUpdate.dateDebutEncheres}" required>
                </div>
                <div class="form-item">
                    <label for="finEnchere">Fin de l'enchère : </label>
                    <input type="date" name="finEnchere" id="finEnchere" value="${articleToUpdate.dateFinEncheres}"  required>
                </div>
                <div class="retrait">
                    <p>Retrait</p>
                    <div class="form-item">
                        <label for="rue">Rue : </label>
                        <input type="text" name="rue" id="rue" value="${user.rue}">
                    </div>
                    <div class="form-item">
                        <label for="cp">Code Postal : </label>
                        <input type="text" name="cp" id="cp" value="${user.CP}">
                    </div>
                    <div class="form-item">
                        <label for="ville">Ville : </label>
                        <input type="text" name="ville" id="ville" value="${user.ville}">
                    </div>
                </div>
            </form>
            <form id="cancel" action="${pageContext.request.contextPath}/accueil" method="get"></form>
            <div class="buttonGroupe">
                <button form="update-form" type="submit" title="update" value="update">Modifier</button>
                <button form="cancel" type="submit" title="cancel" value="cancel">Annuler</button>
            </div>
        </div>
    </div>

</section>
</body>
</html>
