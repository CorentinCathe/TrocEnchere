package dal;

import bo.ArticleVenduBO;
import bo.EnchereBO;
import bo.UtilisateurBO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class EnchereDAOImpl implements EnchereDAO{

    public static final String SELECT_ALL_ENCHERE = "SELECT * FROM ENCHERES";
    public static final String SELECT_ALL_BY_ARTICLE = "SELECT * from ENCHERES e where e.id_article = ?";
    public static final String INSERT_ENCHERE = "INSERT INTO ENCHERES VALUES (?,?,?,?)";
    public static final String UPDATEUSER = "UPDATE UTILISATEURS SET nom = ?,prenom = ?,tel = ?,rue = ?,cp = ?,ville = ?,mdp = ? WHERE pseudo = ? or email = ?";

    @Override
    public List<EnchereBO> selectAll() {
        List<EnchereBO> listEnchere = new ArrayList<EnchereBO>();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ALL_ENCHERE);
            boolean isResultSet = psmt.execute();
            if (isResultSet){
                ResultSet rs = psmt.getResultSet();
                ArticleVenduDAO aDAO = new ArticleVenduDAOImpl();
                UtilisateurDAO uDAO = new UtilisateurDAOImpl();
                while(rs.next()){
                    int id = rs.getInt(1);
                    Date date = rs.getDate(2);
                    int montant = rs.getInt(3);
                    int articleId = rs.getInt(4);
                    int utilisateurId = rs.getInt(5);
                    UtilisateurBO user = uDAO.selectUserById(utilisateurId);
                    ArticleVenduBO article = aDAO.selectArticleById(articleId);
                    EnchereBO en = new EnchereBO(id,date,montant,article,user);
                    listEnchere.add(en);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listEnchere;
    }

    @Override
    public List<EnchereBO> selectAllByIdArticle(int id_article) {
        List<EnchereBO> listEnchere = new ArrayList<EnchereBO>();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ALL_BY_ARTICLE);
            psmt.setInt(1,id_article);
            boolean isResultSet = psmt.execute();
            if (isResultSet){
                ResultSet rs = psmt.getResultSet();
                ArticleVenduDAO aDAO = new ArticleVenduDAOImpl();
                UtilisateurDAO uDAO = new UtilisateurDAOImpl();
                while(rs.next()){
                    int id = rs.getInt("id");
                    Date date = rs.getDate("date");
                    int montant = rs.getInt("montant");
                    int utilisateurId = rs.getInt("id_utilisateur");
                    UtilisateurBO user = uDAO.selectUserById(utilisateurId);
                    ArticleVenduBO article = aDAO.selectArticleById(id_article);
                    System.out.println(article);
                    EnchereBO en = new EnchereBO(id,date,montant,article,user);
                    listEnchere.add(en);
                }
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listEnchere;
    }

    @Override
    public List<EnchereBO> selectAllByUserId(int id_user) {

        List<EnchereBO> listEnchere = new ArrayList<EnchereBO>();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ALL_BY_ARTICLE);
            psmt.setInt(1,id_user);
            boolean isResultSet = psmt.execute();
            if (isResultSet){
                ResultSet rs = psmt.getResultSet();
                ArticleVenduDAO aDAO = new ArticleVenduDAOImpl();
                UtilisateurDAO uDAO = new UtilisateurDAOImpl();
                while(rs.next()){
                    int id = rs.getInt("id");
                    Date date = rs.getDate("date");
                    int montant = rs.getInt("montant");
                    int articleId = rs.getInt("id_article");
                    UtilisateurBO user = uDAO.selectUserById(id_user);
                    ArticleVenduBO article = aDAO.selectArticleById(articleId);
                    EnchereBO en = new EnchereBO(id,date,montant,article,user);
                    listEnchere.add(en);
                }
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listEnchere;
    }


    @Override
    public EnchereBO selectOneById(int id) {
        EnchereBO enchere = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ALL_BY_ARTICLE);
            psmt.setInt(1,id);
            boolean isResultSet = psmt.execute();
            if (isResultSet){
                ResultSet rs = psmt.getResultSet();
                ArticleVenduDAO aDAO = new ArticleVenduDAOImpl();
                UtilisateurDAO uDAO = new UtilisateurDAOImpl();
                while(rs.next()){
                    Date date = rs.getDate("date");
                    int montant = rs.getInt("montant");
                    int articleId = rs.getInt("id_article");
                    int utilisateurId = rs.getInt("id_utilisateur");
                    UtilisateurBO user = uDAO.selectUserById(utilisateurId);
                    ArticleVenduBO article = aDAO.selectArticleById(articleId);
                    enchere = new EnchereBO(id,date,montant,article,user);
                }
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return enchere;
    }

    @Override
    public EnchereBO insertEnchere(EnchereBO enchereBO) {

        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            int res;
            PreparedStatement psmt = cnx.prepareStatement(INSERT_ENCHERE);
            psmt.setDate(1,enchereBO.getDate());
            psmt.setInt(2,enchereBO.getMontant());
            psmt.setInt(3,enchereBO.getArticle().getId());
            psmt.setInt(4,enchereBO.getUtilisateur().getId());
            psmt.execute();
            try(ResultSet generatedKeys = psmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    res = generatedKeys.getInt(1);
                    System.out.println(res);
                    enchereBO.setId(res);
                } else {
                    throw new SQLException("SQL Problem");
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return enchereBO;
    }
}
