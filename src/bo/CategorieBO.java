package bo;

public class CategorieBO {

    private Integer id;
    private String libelle;

    public CategorieBO(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public CategorieBO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "CategorieBO{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
