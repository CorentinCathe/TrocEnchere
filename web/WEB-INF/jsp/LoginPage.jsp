<!DOCTYPE html>
<html lang="fr">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/LoginPage.css">

    <title>Login</title>

</head>
<body>
    <section class="loginContent">
        <div class="title">
            <h1>TROC ENCHERE</h1>
        </div>
        <div class="form">
            <form class="login-form">
                <input type="text" placeholder="username"/>
                <input type="password" placeholder="password"/>
                <button>login</button>
                <p class="message">Not registered? <a href="#">Create an account</a></p>
            </form>
        </div>
    </section>
    <div class="illu">
        <%@include file="./assets/illuLogin.svg" %>
    </div>
</body>
</html>
