<!DOCTYPE html>
<html lang="fr">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" type="image/png" href="./assets/shopping-cart.png" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/LoginPage.css">

    <title>Login</title>

</head>
<body>
    <section class="loginContent">
        <div class="title">
            <h1>TROC ENCHERE</h1>
        </div>
        <div class="form">
            <form class="login-form" action = "${pageContext.request.contextPath}/login" method="post">
                <p style="color: red">${message}</p>
                <input type="text" placeholder="username or email" id="username" name="username" required autofocus/>
                <input type="password" placeholder="password" id="password" name="password" required/>
                <button type="submit" title="Login"> Login</button>
                <p class="message">Not registered? <a href="${pageContext.request.contextPath}/inscription">Create an account</a></p>
            </form>
        </div>
    </section>
    <div class="illu">
        <%@include file="./assets/illuLogin.svg" %>
    </div>
</body>
</html>
