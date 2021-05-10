package dal;

public class DAOFactory {

    public static UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurDAO();
    }
    public static ArticleVenduDAOImpl getArticleVenduDAO() {
        return new ArticleVenduDAOImpl();
    }
    public static EnchereDAOImpl getEnchereDAO() {return new EnchereDAOImpl();}
    public static CategorieDAO getCategorieDAO() {
        return new CategorieDAOImpl();
    }

}
