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
            <form id="inscription-form" action = "${pageContext.request.contextPath}/inscription" method="post">
                <div class="form-item">
                    <label for="username">Pseudo :</label>
                    <input type="text" placeholder="Pseudo" id="username" name="username" required autofocus/>
                </div>
                <div class="form-item">
                    <label for="firstName">Prénom :</label>
                    <input type="text" placeholder="Prénom" id="firstName" name="firstName" required />
                </div>
                <div class="form-item">
                    <label for="lastName">Nom :</label>
                    <input type="text" placeholder="Nom" id="lastName" name="lasteName" required />
                </div>
                <div class="form-item">
                    <label for="phoneNumber">Téléphone :</label>
                    <input type="tel" placeholder="Téléphone" id="phoneNumber" name="phoneNumber" required />
                </div>
                <div class="form-item">
                    <label for="mail">email :</label>
                    <input type="mail" placeholder="email" id="mail" name="mail" required />
                </div>
                <div class="form-item">
                    <label for="adresse">Adresse :</label>
                    <input type="text" placeholder="Adresse" id="adresse" name="adresse" required />
                </div>
                <div class="form-item">
                    <label for="codePostal">Code Postal :</label>
                    <input pattern="[0-9]{5}" type="text" id="codePostal" placeholder="Code Postal" required />
                </div>
                <div class="form-item">
                    <label for="city">Ville :</label>
                    <input type="text" placeholder="Ville" id="city" name="city" required />
                </div>
                <div class="form-item">
                    <label for="password">Mot de passe :</label>
                    <input type="password" placeholder="Mot de passe" id="password" name="password" required />
                </div>
                <div class="form-item">
                    <label for="confirmPassword">Confirmer mot de passe :</label>
                    <input type="password" placeholder="Confirmer mot de passe" id="confirmPassword" name="confirmPassword" required />
                </div>
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
