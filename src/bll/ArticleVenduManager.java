package bll;

import bo.ArticleVenduBO;
import dal.ArticleVenduDAOImpl;
import dal.DAOFactory;

import java.sql.SQLException;
import java.util.List;

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

    public List<ArticleVenduBO>  selectByName(String name) throws SQLException {
        ArticleVenduDAOImpl aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.selectByName(name);
    }
    public List<ArticleVenduBO>  selectByNameAndCategorie(String name, int categorie) throws SQLException {
        ArticleVenduDAOImpl aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.selectByNameAndCategorie(name, categorie);
    }

    public List<ArticleVenduBO>  selectAtLeastOneBet(Integer idUser) throws SQLException {
        ArticleVenduDAOImpl aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.selectAtLeastOneBet(idUser);
    }

    public List<ArticleVenduBO>  selectWon(Integer idUser) throws SQLException {
        ArticleVenduDAOImpl aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.selectWon(idUser);
    }
    public List<ArticleVenduBO>  selectAllSell(Integer idUser) throws SQLException {
        ArticleVenduDAOImpl aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.selectAllSell(idUser);
    }
    public List<ArticleVenduBO>  selectAllNotStartedSell(Integer idUser) throws SQLException {
        ArticleVenduDAOImpl aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.selectAllNotStartedSell(idUser);
    }
    public List<ArticleVenduBO>  selectAllFinishedSell(Integer idUser) throws SQLException {
        ArticleVenduDAOImpl aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.selectAllFinishedSell(idUser);
    }
      
    public ArticleVenduBO insertArticle(ArticleVenduBO article) throws SQLException {
        ArticleVenduDAOImpl aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.insert(article);
    }
}
