package dal;

public class DAOFactory {

    public static UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurDAO();
    }
    public static ArticleVenduDAOImpl getArticleVenduDAO() {
        return new ArticleVenduDAOImpl();
    }

    public static CategorieDAO getCategorieDAO() {
        return new CategorieDAOImpl();
    }

}
