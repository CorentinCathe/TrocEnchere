package bo;

import java.sql.Date;

public class Enchere {
    private int id;
    private Date date;
    private int montant;
    private int idArticle;
    private int idUtilisateur;

    public Enchere(int id, Date date, int montant, int idArticle, int idUtilisateur) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.idArticle = idArticle;
        this.idUtilisateur = idUtilisateur;
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

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
