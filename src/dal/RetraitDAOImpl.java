package dal;

import bo.ArticleVenduBO;
import bo.CategorieBO;
import bo.RetraitBO;
import bo.UtilisateurBO;

import java.sql.*;
import java.util.List;

public class RetraitDAOImpl implements RetraitDAO {

    public static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES (?,?,?,?)";
    public static final String SELECT_RETRAIT_BY_ARTICLE_ID = "SELECT * FROM RETRAITS r WHERE r.id_article = ?";
    public static final String UPDATE_RETRAIT = "UPDATE RETRAITS SET rue = ?,CP = ?,ville = ? WHERE id_article = ?";



    @Override
    public RetraitBO insertRetrait(RetraitBO retrait) throws SQLException {
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            System.out.println("test");
            PreparedStatement psmt = cnx.prepareStatement(INSERT_RETRAIT);
            psmt.setInt(1,retrait.getArticle().getId());
            psmt.setString(2, retrait.getRue());
            psmt.setString(3, retrait.getCP());
            psmt.setString(4, retrait.getVille());
            psmt.execute();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retrait;
    }

    @Override
    public RetraitBO selectByArticleId(Integer articleId) throws SQLException {
        RetraitBO retrait = null;
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_RETRAIT_BY_ARTICLE_ID);
            psmt.setInt(1, articleId);
            ResultSet rs = psmt.executeQuery();
            RetraitDAO rDAO = new RetraitDAOImpl();
            ArticleVenduDAO aDAO = new ArticleVenduDAOImpl();
            if(rs.next()){
                int idAV = rs.getInt(1);
                String rue = rs.getString(2);
                String cp = rs.getString(3);
                String ville = rs.getString(4);
                ArticleVenduBO article = aDAO.selectArticleById(idAV);
                retrait = new RetraitBO(article, rue, cp, ville);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("selectUserById");
            System.out.println(e.getMessage());
        }
        return retrait;
    }

    @Override
    public boolean update(RetraitBO retrait) throws SQLException {
        boolean res = false;
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(UPDATE_RETRAIT);
            psmt.setString(1,retrait.getRue());
            psmt.setString(2,retrait.getCP());
            psmt.setString(3,retrait.getVille());
            psmt.setInt(4,retrait.getArticle().getId());
            res = psmt.execute();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return !res;
    }
}
