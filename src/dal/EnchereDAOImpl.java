package dal;

import bo.ArticleVenduBO;
import bo.CategorieBO;
import bo.EnchereBO;
import bo.UtilisateurBO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EnchereDAOImpl implements EnchereDAO {

    public static final String SELECT_ALL_ENCHERE = "SELECT * FROM ENCHERES";
    public static final String SELECT_ALL_BY_ARTICLE = "SELECT * from ENCHERES e where e.id_article = ?";
    static final String SELECT_ALL_BY_USER = "SELECT * from ENCHERES e where e.id_utilisateur = ?";
    static final String SELECT_ONE_BY_ID = "SELECT * from ENCHERES e where e.id = ?";
    public static final String INSERT_ENCHERE = "INSERT INTO ENCHERES VALUES (?,?,?,?)";
    public static final String SELECT_ALL_ENCHERE_INFO = "SELECT a.id, a.nom,e.date,a.description,a.date_debut_encheres,a.date_fin_encheres,a.prix_initial,a.id_utilisateur as idVendeur, a.id_categorie,e.id_utilisateur as idEncherisseur, e.montant, u2.pseudo as vendeur, u.pseudo as encherisseur FROM ENCHERES e, ARTICLES_VENDUS a, UTILISATEURS u, UTILISATEURS u2 WHERE e.montant = (SELECT MAX(e2.montant) FROM ENCHERES e2 WHERE e2.id_article = e.id_article) and u2.id =  a.id_utilisateur and u.id = e.id_utilisateur and e.id_article = a.id and a.id = ?";
    public static final String SELECT_LAST_ENCHERE_BY_ARTICLE = "SELECT MAX(e.montant) as montant from ENCHERES e where e.id_article = ?";

    @Override
    public List<EnchereBO> selectAll() {
        List<EnchereBO> listEnchere = new ArrayList<EnchereBO>();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ALL_ENCHERE);
            boolean isResultSet = psmt.execute();
            if (isResultSet) {
                ResultSet rs = psmt.getResultSet();
                ArticleVenduDAO aDAO = new ArticleVenduDAOImpl();
                UtilisateurDAO uDAO = new UtilisateurDAOImpl();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    Date date = rs.getDate(2);
                    int montant = rs.getInt(3);
                    int articleId = rs.getInt(4);
                    int utilisateurId = rs.getInt(5);
                    UtilisateurBO user = uDAO.selectUserById(utilisateurId);
                    ArticleVenduBO article = aDAO.selectArticleById(articleId);
                    EnchereBO en = new EnchereBO(id, date, montant, article, user);
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
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ALL_BY_ARTICLE);
            psmt.setInt(1, id_article);
            boolean isResultSet = psmt.execute();
            if (isResultSet) {
                ResultSet rs = psmt.getResultSet();
                ArticleVenduDAO aDAO = new ArticleVenduDAOImpl();
                UtilisateurDAO uDAO = new UtilisateurDAOImpl();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    Date date = rs.getDate("date");
                    int montant = rs.getInt("montant");
                    int utilisateurId = rs.getInt("id_utilisateur");
                    UtilisateurBO user = uDAO.selectUserById(utilisateurId);
                    ArticleVenduBO article = aDAO.selectArticleById(id_article);
                    System.out.println(article);
                    EnchereBO en = new EnchereBO(id, date, montant, article, user);
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
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ALL_BY_USER);
            psmt.setInt(1, id_user);
            boolean isResultSet = psmt.execute();
            if (isResultSet) {
                ResultSet rs = psmt.getResultSet();
                ArticleVenduDAO aDAO = new ArticleVenduDAOImpl();
                UtilisateurDAO uDAO = new UtilisateurDAOImpl();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    Date date = rs.getDate("date");
                    int montant = rs.getInt("montant");
                    int articleId = rs.getInt("id_article");
                    UtilisateurBO user = uDAO.selectUserById(id_user);
                    ArticleVenduBO article = aDAO.selectArticleById(articleId);
                    EnchereBO en = new EnchereBO(id, date, montant, article, user);
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
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ONE_BY_ID);
            psmt.setInt(1, id);
            boolean isResultSet = psmt.execute();
            if (isResultSet) {
                ResultSet rs = psmt.getResultSet();
                ArticleVenduDAO aDAO = new ArticleVenduDAOImpl();
                UtilisateurDAO uDAO = new UtilisateurDAOImpl();
                while (rs.next()) {
                    Date date = rs.getDate("date");
                    int montant = rs.getInt("montant");
                    int articleId = rs.getInt("id_article");
                    int utilisateurId = rs.getInt("id_utilisateur");
                    UtilisateurBO user = uDAO.selectUserById(utilisateurId);
                    ArticleVenduBO article = aDAO.selectArticleById(articleId);
                    enchere = new EnchereBO(id, date, montant, article, user);
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
            PreparedStatement psmt = cnx.prepareStatement(INSERT_ENCHERE,Statement.RETURN_GENERATED_KEYS);

            psmt.setDate(1, enchereBO.getDate());
            psmt.setInt(2, enchereBO.getMontant());
            psmt.setInt(3, enchereBO.getArticle().getId());
            psmt.setInt(4, enchereBO.getUtilisateur().getId());
            psmt.execute();
            try (ResultSet generatedKeys = psmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    res = generatedKeys.getInt(1);
                    enchereBO.setId(res);
                } else {
                    throw new SQLException("SQL Problem");
                }
            }catch (Exception e){
                System.out.println("on est dans insert ench??re mais dans le catch 1");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("on est dans insert ench??re mais dans le catch 2");
            System.out.println(e.getMessage());
        }
        return enchereBO;
    }

    @Override
    public EnchereBO selectAllEncherInfo(int id_article) {

        EnchereBO enchere = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ALL_ENCHERE_INFO);
            psmt.setInt(1, id_article);
            boolean isResultSet = psmt.execute();
            if (isResultSet) {
                ResultSet rs = psmt.getResultSet();
                ArticleVenduDAO aDAO = new ArticleVenduDAOImpl();
                UtilisateurDAO uDAO = new UtilisateurDAOImpl();
                CategorieDAO cDAO = new CategorieDAOImpl();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    Date date = rs.getDate("date");
                    int montant = rs.getInt("montant");
                    String pseudoVendeur = rs.getString("vendeur");
                    String pseudoEncherisseur = rs.getString("encherisseur");
                    String nomArticle = rs.getString("nom");
                    String descriptionArticle = rs.getString("description");
                    Date dateDebutEnchere = rs.getDate("date_debut_encheres");
                    Date dateFinEnchere = rs.getDate("date_fin_encheres");
                    int prixInitial = rs.getInt("prix_initial");
                    int vendeurId = rs.getInt("idVendeur");
                    int idCategorie = rs.getInt("id_categorie");
                    int enchereUtilisateurId = rs.getInt("idEncherisseur");
                    CategorieBO categorie = cDAO.selectById(idCategorie);
                    UtilisateurBO vendeur = new UtilisateurBO(vendeurId,pseudoVendeur,null,null,null,null,null,null,null,null,0,false);
                    UtilisateurBO encherisseur = new UtilisateurBO(enchereUtilisateurId,pseudoEncherisseur,null,null,null,null,null,null,null,null,0,false);
                    ArticleVenduBO article = new ArticleVenduBO(id_article,nomArticle,descriptionArticle,dateDebutEnchere,dateFinEnchere,prixInitial,0,vendeur,categorie);
                    EnchereBO en = new EnchereBO(id, date, montant, article, encherisseur);
                    enchere = en;
                }
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return enchere;
    }

    @Override
    public int selectLastMontantByIdArticle(int id_article) {
        int montantEnchere = 0;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_LAST_ENCHERE_BY_ARTICLE);
            psmt.setInt(1, id_article);
            boolean isResultSet = psmt.execute();
            if (isResultSet) {
                ResultSet rs = psmt.getResultSet();
                rs.next();
                montantEnchere = rs.getInt("montant");
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return montantEnchere;
    }
}
