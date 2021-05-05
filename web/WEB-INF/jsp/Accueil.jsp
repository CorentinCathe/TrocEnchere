<%--
  Created by IntelliJ IDEA.
  User: maxou
  Date: 04/05/2021
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bo.Utilisateur"%>

<html>
<head>
    <title>Accueil</title>
</head>
<body>
    <% Boolean resultIsConnected = (Boolean) request.getAttribute("isConnected");%>

    <header>ENI Enchères</header>
    <div class="connexion">
        <p><%= resultIsConnected.toString() %>  </p>
        <c:choose>
            <c:when test= "<%= resultIsConnected.equals(true) %>" >
                <% Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user"); %>>
                <p>Bienvenu <%= utilisateur.getPseudo() %> !</p>
                <form action="${pageContext.request.contextPath}/accueil" method="post">
                    <% request.getSession().setAttribute("user", null);%>
                    <input type="submit" value="Se déconnecter" name="disconnect">
                </form>
            </c:when>
            <c:otherwise>
                <p>Vous n'êtes pas connecté !</p>
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <input type="submit" value="S'inscrire - Se connecter">
                </form>
            </c:otherwise>
        </c:choose>

    </div>
    <div class="listesArticleEnVente">
        <p>Liste des enchères</p>
    </div>
</body>
</html>
