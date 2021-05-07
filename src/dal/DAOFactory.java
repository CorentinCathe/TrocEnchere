package dal;

public class DAOFactory {

    public static UtilisateurDAOImpl getUtilisateurDAO() {
        return new UtilisateurDAOImpl();
    }
    public static ArticleVenduDAOImpl getArticleVenduDAO() {
        return new ArticleVenduDAOImpl();
    }

    public static CategorieDAO getCategorieDAO() {
        return new CategorieDAOImpl();
    }

}
