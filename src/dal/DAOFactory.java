package dal;

public class DAOFactory {
    public static UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurDAO();
    }
}
