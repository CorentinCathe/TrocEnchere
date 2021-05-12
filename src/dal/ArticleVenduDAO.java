package dal;

import bo.ArticleVenduBO;

import java.sql.SQLException;
import java.util.List;

public interface ArticleVenduDAO {

    public List<ArticleVenduBO> selectAll() throws SQLException;

    public List<ArticleVenduBO> selectByUtilisateurId(Integer id) throws SQLException;

    public List<ArticleVenduBO> selectByCategorieId(Integer id) throws SQLException;

    public ArticleVenduBO selectArticleById(Integer id) throws SQLException;

    public List<ArticleVenduBO> selectByName(String name) throws SQLException;

    public List<ArticleVenduBO> selectByNameAndCategorie(String name, Integer idCategorie) throws SQLException;

    public List<ArticleVenduBO> selectAtLeastOneBet(Integer idUser) throws SQLException;

    public List<ArticleVenduBO> selectWon(Integer idUser) throws SQLException;

    public List<ArticleVenduBO> selectAllSell(Integer idUser) throws SQLException;

    public List<ArticleVenduBO> selectAllNotStartedSell(Integer idUser) throws SQLException;

    public List<ArticleVenduBO> selectAllFinishedSell(Integer idUser) throws SQLException;

    public ArticleVenduBO insert(ArticleVenduBO article) throws SQLException;

    public boolean update(ArticleVenduBO article) throws SQLException;
}
