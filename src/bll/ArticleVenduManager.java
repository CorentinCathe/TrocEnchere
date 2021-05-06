package bll;

import bo.ArticleVenduBO;
import bo.Utilisateur;
import dal.ArticleVenduDAO;
import dal.ArticleVenduDAOImpl;
import dal.DAOFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ArticleVenduManager {

    public List<ArticleVenduBO> selectAll() throws SQLException {
        ArticleVenduDAOImpl aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.selectAll();
    }

    public List<ArticleVenduBO> selectByUtilisateurId(int id) throws SQLException {
        ArticleVenduDAOImpl aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.selectByUtilisateurId(id);
    }

    public List<ArticleVenduBO>  selectByCategorieId(int id) throws SQLException {
        ArticleVenduDAOImpl aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.selectByCategorieId(id);
    }

}
