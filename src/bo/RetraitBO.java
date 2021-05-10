package bo;

public class RetraitBO {

    private ArticleVenduBO article;
    private String rue;
    private String CP;
    private String ville;

    public RetraitBO(ArticleVenduBO article, String rue, String CP, String ville) {
        this.article = article;
        this.rue = rue;
        this.CP = CP;
        this.ville = ville;
    }

    public RetraitBO( String rue, String CP, String ville) {
        this.article = new ArticleVenduBO();
        this.rue = rue;
        this.CP = CP;
        this.ville = ville;
    }

    public ArticleVenduBO getArticle() {
        return article;
    }

    public void setArticle(ArticleVenduBO article) {
        this.article = article;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "RetraitBO{" +
                "article=" + article +
                ", rue='" + rue + '\'' +
                ", CP='" + CP + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
