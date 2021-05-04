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

    <title>Create Account</title>
<body>
    <section class="header">
        <div class="logo">TROC ENCHERE</div>
    </section>
    <section class="content">
        <div class="title">
            <h1>Mon Profil</h1>
        </div>
        <div class="form">
            <form class="inscription-form" action = "${pageContext.request.contextPath}/inscription" method="post">
                <input type="text" placeholder="username or email" id="username" name="username" required autofocus/>
                <input type="text" placeholder="Prénom" id="firstName" name="firstName" required />
                <input type="tel" placeholder="Téléphone" id="phoneNumber" name="phoneNumber" required />
                <input pattern="[0-9]{5}" type="text" placeholder="Code Postal" required />
                <input type="password" placeholder="Mot de passe" id="password" name="password" required />
                <input type="text" placeholder="lastName" id="lastName" name="lasteName" required />
                <input type="mail" placeholder="email" id="mail" name="mail" required />
                <input type="text" placeholder="Adresse" id="adresse" name="adresse" required />
                <input type="text" placeholder="Ville" id="city" name="city" required />
                <input type="password" placeholder="Confirmer mot de passe" id="confirmPassword" name="confirmPassword" required />

            </form>
        </div>
    </section>
</body>
</html>
