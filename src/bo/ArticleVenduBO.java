package bo;

import java.sql.Date;

public class ArticleVenduBO {

    private Integer id;
    private String nom;
    private String description;
    private Date dateDebutEncheres;
    private Date dateFinEncheres;
    private int prixInitial;
    private int prixVente;
    private Utilisateur utilisateur;
    private CategorieBO categorie;

    public ArticleVenduBO(Integer id, String nom, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente, Utilisateur utilisateur, CategorieBO categorie) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
    }

    public ArticleVenduBO(Integer id, String nom, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.utilisateur = new Utilisateur();
        this.categorie = new CategorieBO();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(Date dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public Date getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(Date dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(int prixInitial) {
        this.prixInitial = prixInitial;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public CategorieBO getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieBO categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "ArticleVenduBO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateDebutEncheres=" + dateDebutEncheres +
                ", dateFinEncheres=" + dateFinEncheres +
                ", prixInitial=" + prixInitial +
                ", prixVente=" + prixVente +
                ", utilisateur=" + utilisateur +
                ", categorie=" + categorie +
                '}';
    }
}
