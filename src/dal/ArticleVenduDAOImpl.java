package dal;

import bo.ArticleVenduBO;
import bo.CategorieBO;
import bo.UtilisateurBO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleVenduDAOImpl implements ArticleVenduDAO {

    public static final String SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES_VENDUS";
    public static final String SELECT_BY_NAME = "SELECT * FROM ARTICLES_VENDUS a WHERE a.nom LIKE ? ";
    public static final String SELECT_BY_NAME_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS a WHERE a.nom LIKE ? and a.id_categorie = ? ";
    public static final String SELECT_BY_UTILISATEUR_ID = "SELECT * FROM ARTICLES_VENDUS a WHERE a.id_utilisateur = ?";
    public static final String SELECT_BY_CATEGORIE_ID = "SELECT * FROM ARTICLES_VENDUS a WHERE a.id_categorie = ?";
    public static final String SELECT_BY_ARTICLE_ID = "SELECT * FROM ARTICLE_VENDUS a WHERE a.id = ?";

    @Override
    public List<ArticleVenduBO> selectAll() throws SQLException {
        List<ArticleVenduBO> listeArticlesVendus = new ArrayList<>();

        try(Connection cnx = ConnectionProvider.getConnection();){
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
            boolean isResultSet = psmt.execute();

            if(isResultSet) {
                ResultSet rs = psmt.getResultSet();
                UtilisateurDAOImpl uDAO = new UtilisateurDAOImpl();
                CategorieDAO cDAO = new CategorieDAOImpl();
                while(rs.next()){
                    int id = rs.getInt(1);
                    String nom = rs.getString(2);
                    String description = rs.getString(3);
                    Date dateDebut = rs.getDate(4);
                    Date dateFin = rs.getDate(5);
                    int prixInitial = rs.getInt(6);
                    int prixVente = rs.getInt(7);
                    int utilisateurId = rs.getInt(8);
                    int categorieId = rs.getInt(9);
                    ArticleVenduBO av = new ArticleVenduBO(id, nom, description, dateDebut, dateFin, prixInitial, prixVente);
                    UtilisateurBO user = uDAO.selectUserById(utilisateurId);
                    CategorieBO categorie = cDAO.selectById(categorieId);
                    av.setCategorie(categorie);
                    av.setUtilisateur(user);
                    listeArticlesVendus.add(av);
                }
                System.out.println("SELECT ALL");
                rs.close();
            } else {
                System.out.println("Result Not set !");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listeArticlesVendus;
    }

    @Override
    public List<ArticleVenduBO> selectByUtilisateurId(Integer id) throws SQLException {
        List<ArticleVenduBO> listArticlesVendus = new ArrayList<>();

        try(Connection cnx = ConnectionProvider.getConnection();){
            PreparedStatement psmt = cnx.prepareStatement(SELECT_BY_UTILISATEUR_ID);
            psmt.setInt(1,id);
            boolean isResultSet = psmt.execute();

            if(isResultSet) {
                ResultSet rs = psmt.getResultSet();
                UtilisateurDAOImpl uDAO = new UtilisateurDAOImpl();
                CategorieDAO cDAO = new CategorieDAOImpl();

                while(rs.next()){
                    int idAV = rs.getInt(1);
                    String nom = rs.getString(2);
                    String description = rs.getString(3);
                    Date dateDebut = rs.getDate(4);
                    Date dateFin = rs.getDate(5);
                    int prixInitial = rs.getInt(6);
                    int prixVente = rs.getInt(7);
                    int utilisateurId = rs.getInt(8);
                    int categorieId = rs.getInt(9);
                    ArticleVenduBO av = new ArticleVenduBO(idAV, nom, description, dateDebut, dateFin, prixInitial, prixVente);
                    UtilisateurBO user = uDAO.selectUserById(utilisateurId);
                    CategorieBO categorie = cDAO.selectById(categorieId);
                    av.setCategorie(categorie);
                    av.setUtilisateur(user);
                    listArticlesVendus.add(av);
                }
                System.out.println("SELECT USER");
                rs.close();
            } else {
                System.out.println("Result Not set !");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listArticlesVendus;
    }

    @Override
    public List<ArticleVenduBO> selectByCategorieId(Integer idCategorie) throws SQLException {
        List<ArticleVenduBO> listArticlesVendus = new ArrayList<>();

        try(Connection cnx = ConnectionProvider.getConnection();){
            PreparedStatement psmt = cnx.prepareStatement(SELECT_BY_CATEGORIE_ID);
            psmt.setInt(1,idCategorie);
            boolean isResultSet = psmt.execute();

            if(isResultSet) {
                ResultSet rs = psmt.getResultSet();
                UtilisateurDAOImpl uDAO = new UtilisateurDAOImpl();
                CategorieDAO cDAO = new CategorieDAOImpl();

                while(rs.next()){
                    int idAV = rs.getInt(1);
                    String nom = rs.getString(2);
                    String description = rs.getString(3);
                    Date dateDebut = rs.getDate(4);
                    Date dateFin = rs.getDate(5);
                    int prixInitial = rs.getInt(6);
                    int prixVente = rs.getInt(7);
                    int utilisateurId = rs.getInt(8);
                    int categorieId = rs.getInt(9);
                    ArticleVenduBO av = new ArticleVenduBO(idAV, nom, description, dateDebut, dateFin, prixInitial, prixVente);
                    UtilisateurBO user = uDAO.selectUserById(utilisateurId);
                    CategorieBO categorie = cDAO.selectById(categorieId);
                    av.setCategorie(categorie);
                    av.setUtilisateur(user);
                    listArticlesVendus.add(av);
                }
                System.out.println("SELECT CAT");
                rs.close();
            } else {
                System.out.println("Result Not set !");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listArticlesVendus;
    }

    @Override
    public ArticleVenduBO selectArticleById(Integer id) throws SQLException {
        ArticleVenduBO article = null;
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_BY_ARTICLE_ID);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            UtilisateurDAO uDAO = new UtilisateurDAOImpl();
            CategorieDAO cDAO = new CategorieDAOImpl();
            if(rs.next()){
                int idAV = rs.getInt(1);
                String nom = rs.getString(2);
                String description = rs.getString(3);
                Date dateDebut = rs.getDate(4);
                Date dateFin = rs.getDate(5);
                int prixInitial = rs.getInt(6);
                int prixVente = rs.getInt(7);
                int utilisateurId = rs.getInt(8);
                int categorieId = rs.getInt(9);
                article = new ArticleVenduBO(idAV, nom, description, dateDebut, dateFin, prixInitial, prixVente);
                UtilisateurBO user = uDAO.selectUserById(utilisateurId);
                CategorieBO categorie = cDAO.selectById(categorieId);
                article.setCategorie(categorie);
                article.setUtilisateur(user);
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("selectUserById");
            System.out.println(e.getMessage());
        }
        return article;
    }
  
    @Override
    public List<ArticleVenduBO> selectByName(String name) throws SQLException {
        List<ArticleVenduBO> listeArticlesVendus = new ArrayList<>();

        try(Connection cnx = ConnectionProvider.getConnection();){
            PreparedStatement psmt = cnx.prepareStatement(SELECT_BY_NAME);
            psmt.setString(1,'%'+name+'%');
            boolean isResultSet = psmt.execute();

            if(isResultSet) {
                ResultSet rs = psmt.getResultSet();
                UtilisateurDAOImpl uDAO = new UtilisateurDAOImpl();
                CategorieDAO cDAO = new CategorieDAOImpl();
                while(rs.next()){
                    int id = rs.getInt(1);
                    String nom = rs.getString(2);
                    String description = rs.getString(3);
                    Date dateDebut = rs.getDate(4);
                    Date dateFin = rs.getDate(5);
                    int prixInitial = rs.getInt(6);
                    int prixVente = rs.getInt(7);
                    int utilisateurId = rs.getInt(8);
                    int categorieId = rs.getInt(9);
                    ArticleVenduBO av = new ArticleVenduBO(id, nom, description, dateDebut, dateFin, prixInitial, prixVente);
                    UtilisateurBO user = uDAO.selectUserById(utilisateurId);
                    CategorieBO categorie = cDAO.selectById(categorieId);
                    av.setCategorie(categorie);
                    av.setUtilisateur(user);
                    listeArticlesVendus.add(av);
                }
                System.out.println("SELECT BY NAME");
                rs.close();
            } else {
                System.out.println("Result Not set !");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listeArticlesVendus;
    }

    @Override
    public List<ArticleVenduBO> selectByNameAndCategorie(String name,Integer idCategorie) throws SQLException {
        List<ArticleVenduBO> listeArticlesVendus = new ArrayList<>();
        try(Connection cnx = ConnectionProvider.getConnection();){
            PreparedStatement psmt = cnx.prepareStatement(SELECT_BY_NAME_CATEGORIE);
            psmt.setString(1,'%'+name+'%');
            psmt.setInt(2,idCategorie);
            boolean isResultSet = psmt.execute();

            if(isResultSet) {
                ResultSet rs = psmt.getResultSet();
                UtilisateurDAOImpl uDAO = new UtilisateurDAOImpl();
                CategorieDAO cDAO = new CategorieDAOImpl();
                while(rs.next()){
                    int id = rs.getInt(1);
                    String nom = rs.getString(2);
                    String description = rs.getString(3);
                    Date dateDebut = rs.getDate(4);
                    Date dateFin = rs.getDate(5);
                    int prixInitial = rs.getInt(6);
                    int prixVente = rs.getInt(7);
                    int utilisateurId = rs.getInt(8);
                    int categorieId = rs.getInt(9);
                    ArticleVenduBO av = new ArticleVenduBO(id, nom, description, dateDebut, dateFin, prixInitial, prixVente);
                    UtilisateurBO user = uDAO.selectUserById(utilisateurId);
                    CategorieBO categorie = cDAO.selectById(categorieId);
                    av.setCategorie(categorie);
                    av.setUtilisateur(user);
                    listeArticlesVendus.add(av);
                }
                System.out.println("SELECT NAME CAT");
                rs.close();
            } else {
                System.out.println("Result Not set !");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listeArticlesVendus;
    }
}
