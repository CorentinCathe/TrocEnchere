package dal;

import bo.CategorieBO;
import bo.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategorieDAOImpl implements CategorieDAO {

    public static final String SELECT_CATEGORIE_BY_ID = "SELECT * FROM CATEGORIES WHERE id = ?";


    @Override
    public CategorieBO selectById(Integer id) throws SQLException {
        CategorieBO categorie = null;
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_ID);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();

            if(rs.next()){
                String libelle = rs.getString(2);
                categorie = new CategorieBO(id, libelle);
                rs.close();
            }

        } catch (Exception e) {
            System.out.println("SelectCategorieById");
            System.out.println(e.getMessage());
        }
        return categorie;
    }
}
