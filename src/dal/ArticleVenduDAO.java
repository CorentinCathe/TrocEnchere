package dal;

import bo.ArticleVenduBO;

import java.sql.SQLException;
import java.util.List;

public interface ArticleVenduDAO {

    public List<ArticleVenduBO> selectAll() throws SQLException;

    public List<ArticleVenduBO> selectByUtilisateurId(Integer id) throws SQLException;

    public List<ArticleVenduBO> selectByCategorieId(Integer id) throws SQLException;

}
