package dal.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.ArticleVenduBO;
import bo.CategorieBO;
import bo.EnchereBO;
import bo.UtilisateurBO;

import java.util.regex.Pattern;

public abstract class SecurityImpl {

    //return true if is dangerous
    public static boolean checkIfIsDangerous(String word){
        String attribute = word.toLowerCase();
        boolean result = false;
        if(attribute.contains("<") || attribute.contains("script") || attribute.contains(" ") || attribute.contains(";") ){
            result = true;
        }else if(attribute.contains("delete") || attribute.contains("update") || attribute.contains("from") ){
            result= true;
        }
        return result;
    }

    public static boolean checkIfIsDangerousRue(String word){
        String attribute = word.toLowerCase();
        boolean result = false;
        if(attribute.contains("<") || attribute.contains("script") || attribute.contains(";") ){
            result = true;
        }else if(attribute.contains("delete") || attribute.contains("update") || attribute.contains("from") ){
            result= true;
        }
        return result;
    }

    //return true if password is safe
    public static boolean checkIfPasswordSafe(String password){
        return Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W])(?!.* ).{8,})", password);
    }

    public static String hashPassword(String plainTextPassword){
        return BCrypt.withDefaults().hashToString(10,plainTextPassword.toCharArray());
    }
    public static boolean checkPass(String plainPassword, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword);
        return result.verified;
    }
    
    public static boolean checkUserSafe(UtilisateurBO user){
        boolean test = true;
        if (checkIfIsDangerous(user.getPseudo()))
            test=false;
        if (checkIfIsDangerous(user.getNom()))
            test=false;
        if (checkIfIsDangerous(user.getPrenom()))
            test=false;
        if (checkIfIsDangerous(user.getEmail()))
            test=false;
        if (checkIfIsDangerous(user.getTel()))
            test=false;
        if (checkIfIsDangerousRue(user.getRue()))
            test=false;
        if (checkIfIsDangerous(user.getCP()))
            test=false;
        if (checkIfIsDangerous(user.getVille()))
            test=false;
        if (!checkIfPasswordSafe(user.getMdp()))
            test=false;
        return test;
    }
    public static boolean checkCategorieSafe(CategorieBO categorie){
        boolean test = true;
        if (checkIfIsDangerous(Integer.toString(categorie.getId())))
            test=false;
        if (checkIfIsDangerous(categorie.getLibelle()))
            test=false;
        return test;
    }
    public static boolean checkArticleSafe(ArticleVenduBO article){
        boolean test = true;
        if (checkIfIsDangerous(article.getNom()))
            test=false;
        if (checkIfIsDangerous(article.getDescription()))
            test=false;
        if (checkIfIsDangerous(article.getDateDebutEncheres().toString()))
            test=false;
        if (checkIfIsDangerous(article.getDateFinEncheres().toString()))
            test=false;
        if (checkIfIsDangerous(Integer.toString(article.getPrixInitial())))
            test=false;
        if (checkIfIsDangerous(Integer.toString(article.getPrixVente())))
            test=false;
        if (checkCategorieSafe(article.getCategorie()))
            test=false;
        if (checkUserSafe(article.getUtilisateur()))
            test=false;
        return test;
    }

    public static boolean checkEnchereSafe(EnchereBO enchere){
        boolean test = true;
        if (checkIfIsDangerous(Integer.toString(enchere.getId())))
            test=false;
        if (checkIfIsDangerous(enchere.getDate().toString()))
            test=false;
        if (checkIfIsDangerous(Integer.toString(enchere.getMontant())))
            test=false;
        if (checkUserSafe(enchere.getUtilisateur()))
            test=false;
        if (checkArticleSafe(enchere.getArticle()))
            test=false;
        return test;
    }
}