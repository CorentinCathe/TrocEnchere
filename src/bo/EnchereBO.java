package bo;

import jdk.jshell.execution.Util;

import java.sql.Date;

public class EnchereBO {
    private int id;
    private Date date;
    private int montant;
    private ArticleVenduBO article;
    private UtilisateurBO utilisateur;

    public EnchereBO(int id, Date date, int montant, ArticleVenduBO article, UtilisateurBO utilisateur) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.article = article;
        this.utilisateur = utilisateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public ArticleVenduBO getArticle() {
        return article;
    }

    public void setArticle(ArticleVenduBO article) {
        this.article = article;
    }

    public UtilisateurBO getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurBO utilisateur) {
        this.utilisateur = utilisateur;
    }
}
