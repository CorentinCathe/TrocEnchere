<%--
  Created by IntelliJ IDEA.
  User: maxou
  Date: 04/05/2021
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bo.Utilisateur" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/Accueil.css">
<html>
<head>
    <title>Accueil</title>
</head>
<body>
<header>

    <div class="logo">Troc Enchere</div>
    <section class="menu-container">
     <% Boolean resultIsConnected = (Boolean) request.getSession().getAttribute("connected");%>
        <c:choose>
            <c:when test= "<%= resultIsConnected!=null && resultIsConnected%>" >
                <% Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user"); %>
            <ul class="menu">
                <li class="menu-btn">
                    <form action="${pageContext.request.contextPath}/enchere" method="post">
                        <% request.getSession().setAttribute("user", null);%>
                        <button type="submit" value="Enchères" name="enchere">Enchères</button>
                    </form>
                </li>
                <li class="menu-btn">
                    <form action="${pageContext.request.contextPath}/profil" method="post">
                        <% request.getSession().setAttribute("user", null);%>
                        <button type="submit" value="Profil" name="profil">Profil</button>
                    </form>
                </li>
                <li class="menu-btn">
                    <form action="${pageContext.request.contextPath}/vendre" method="post">
                        <% request.getSession().setAttribute("user", null);%>
                        <button type="submit" value="Vendre article" name="vendre">Vendre article</button>
                    </form>
                </li>
                <li class="menu-btn">
                    <form action="${pageContext.request.contextPath}/accueil" method="post">
                        <% request.getSession().setAttribute("user", null);%>
                        <button type="submit" value="Se déconnecter" name="disconnect">Se déconnecter</button>
                    </form>
                </li>
            </ul>
        </c:when>
        <c:otherwise>
        <ul class="menu">
            <li class="menu-btn">
                <form action="${pageContext.request.contextPath}/inscription" method="get">
                    <button type="submit" value="s'inscrire">S'inscrire</button>
                </form>
            </li>
            <li class="menu-btn">
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <button type="submit" value="Se connecter">Se connecter</button>
                </form>
            </li>
        </ul>
        </c:otherwise>
    </c:choose>
    </section>
</header>
</body>
</html>
