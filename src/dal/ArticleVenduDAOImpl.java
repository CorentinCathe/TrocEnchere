package dal;

import bo.ArticleVenduBO;
import bo.CategorieBO;
import bo.UtilisateurBO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleVenduDAOImpl implements ArticleVenduDAO {

    public static final String SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES_VENDUS where date_fin_encheres>=GETDATE()";
    public static final String SELECT_BY_NAME = "SELECT * FROM ARTICLES_VENDUS a WHERE date_fin_encheres>=GETDATE() and a.nom LIKE ? ";
    public static final String SELECT_BY_NAME_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS a WHERE date_fin_encheres>=GETDATE() and a.nom LIKE ? and a.id_categorie = ? ";
    public static final String SELECT_BY_UTILISATEUR_ID = "SELECT * FROM ARTICLES_VENDUS a WHERE date_fin_encheres>=GETDATE() and a.id_utilisateur = ?";
    public static final String SELECT_BY_CATEGORIE_ID = "SELECT * FROM ARTICLES_VENDUS a WHERE date_fin_encheres>=GETDATE() and  a.id_categorie = ?";
    public static final String SELECT_BY_ARTICLE_ID = "SELECT * FROM ARTICLES_VENDUS a WHERE a.id = ?";
    public static final String SELECT_ARTICLES_AT_LEAST_ONE_BET = "SELECT DISTINCT a.*,e.id_article,e.id_utilisateur FROM ARTICLES_VENDUS a, ENCHERES e WHERE date_fin_encheres>=GETDATE() and e.id_article = a.id and e.id_utilisateur = ?";
    public static final String SELECT_ARTICLES_WON = "SELECT DISTINCT a.*,e.id_article,e.id_utilisateur FROM ARTICLES_VENDUS a, ENCHERES e WHERE e.id_utilisateur = ? and a.date_fin_encheres<GETDATE() and e.montant = (SELECT MAX(e2.montant) FROM ENCHERES e2 WHERE e2.id_article = e.id_article) and e.id_article = a.id";
    public static final String SELECT_ALL_SELL_ARTICLE = "SELECT DISTINCT a.* FROM ARTICLES_VENDUS a WHERE a.id_utilisateur = ? and date_debut_encheres<=GETDATE() and date_fin_encheres>=GETDATE()";
    public static final String SELECT_SELL_ARTICLE_NOT_STARTED = "SELECT DISTINCT a.* FROM ARTICLES_VENDUS a WHERE a.id_utilisateur = ? and date_debut_encheres>GETDATE()";
    public static final String SELECT_SELL_ARTICLE_FINISHED = "SELECT DISTINCT a.* FROM ARTICLES_VENDUS a WHERE a.id_utilisateur = ? and date_fin_encheres<GETDATE()";
    public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS VALUES (?,?,?,?,?,?,?,?)";
    public static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, id_categorie = ? WHERE id = ?";

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

    @Override
    public List<ArticleVenduBO> selectAtLeastOneBet(Integer idUser) throws SQLException {
        List<ArticleVenduBO> listeArticlesVendus = new ArrayList<>();

        try(Connection cnx = ConnectionProvider.getConnection();){
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ARTICLES_AT_LEAST_ONE_BET);
            psmt.setInt(1,idUser);
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
                System.out.println("SELECT AT LEAST ONE BET");
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
    public List<ArticleVenduBO> selectWon(Integer idUser) throws SQLException {
        List<ArticleVenduBO> listeArticlesVendus = new ArrayList<>();

        try(Connection cnx = ConnectionProvider.getConnection();){
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ARTICLES_WON);
            psmt.setInt(1,idUser);
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
                System.out.println("SELECT WON");
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
    public List<ArticleVenduBO> selectAllSell(Integer idUser) throws SQLException {
        List<ArticleVenduBO> listeArticlesVendus = new ArrayList<>();

        try(Connection cnx = ConnectionProvider.getConnection();){
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ALL_SELL_ARTICLE);
            psmt.setInt(1,idUser);
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
                System.out.println("SELECT ALL SELLS");
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
    public List<ArticleVenduBO> selectAllNotStartedSell(Integer idUser) throws SQLException {
        List<ArticleVenduBO> listeArticlesVendus = new ArrayList<>();

        try(Connection cnx = ConnectionProvider.getConnection();){
            PreparedStatement psmt = cnx.prepareStatement(SELECT_SELL_ARTICLE_NOT_STARTED);
            psmt.setInt(1,idUser);
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
                System.out.println("SELECT NOT STARTED");
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
    public List<ArticleVenduBO> selectAllFinishedSell(Integer idUser) throws SQLException {
        List<ArticleVenduBO> listeArticlesVendus = new ArrayList<>();

        try(Connection cnx = ConnectionProvider.getConnection();){
            PreparedStatement psmt = cnx.prepareStatement(SELECT_SELL_ARTICLE_FINISHED);
            psmt.setInt(1,idUser);
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
                System.out.println("SELECT NOT FINISHED");
                rs.close();
            } else {
                System.out.println("Result Not set !");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listeArticlesVendus;
    }

    public ArticleVenduBO insert(ArticleVenduBO article) throws SQLException {
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            int res;
            PreparedStatement psmt = cnx.prepareStatement(INSERT_ARTICLE,Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1,article.getNom());
            psmt.setString(2,article.getDescription());
            psmt.setDate(3,article.getDateDebutEncheres());
            psmt.setDate(4,article.getDateFinEncheres());
            psmt.setInt(5,article.getPrixInitial());
            psmt.setInt(6, article.getPrixVente());
            psmt.setInt(7,article.getUtilisateur().getId());
            psmt.setInt(8,article.getCategorie().getId());
            psmt.execute();
            try(ResultSet generatedKeys = psmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    res = generatedKeys.getInt(1);
                    System.out.println(res);
                    article.setId(res);
                } else {
                    throw new SQLException("SQL Problem");
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return article;
    }

    @Override
    public boolean update(ArticleVenduBO article) throws SQLException {
        boolean res = false;
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            System.out.println("on est dans le update impl");
            PreparedStatement psmt = cnx.prepareStatement(UPDATE_ARTICLE);
            psmt.setString(1,article.getNom());
            psmt.setString(2,article.getDescription());
            psmt.setDate(3,article.getDateDebutEncheres());
            psmt.setDate(4,article.getDateFinEncheres());
            psmt.setInt(5,article.getPrixInitial());
            psmt.setInt(6,article.getCategorie().getId());
            psmt.setInt(7,article.getId());
            res = psmt.execute();
        }catch (Exception e) {
            System.out.println("C'est l'exception");
            System.out.println(e.getMessage());
        }
        return !res;
    }


}