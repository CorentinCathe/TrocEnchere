package bll;

import bo.ArticleVenduBO;
import bo.EnchereBO;
import dal.ArticleVenduDAO;
import dal.ArticleVenduDAOImpl;
import dal.DAOFactory;
import dal.EnchereDAOImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public ArticleVenduBO selectArticleById(int id) throws SQLException {
        ArticleVenduDAO aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.selectArticleById(id);
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

    public boolean updateArticle(ArticleVenduBO article) throws SQLException {
        ArticleVenduDAOImpl aDAO = DAOFactory.getArticleVenduDAO();
        return aDAO.update(article);
    }
}
