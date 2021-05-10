package dal;

import bo.CategorieBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAOImpl implements CategorieDAO {

    public static final String SELECT_CATEGORIE_BY_ID = "SELECT * FROM CATEGORIES WHERE id = ?";
    public static final String SELECT_ALL = "SELECT * FROM CATEGORIES";

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
            System.out.println(e.getMessage());
        }
        return categorie;
    }

    @Override
    public List<CategorieBO> selectAll() throws SQLException {
        List<CategorieBO> categorieList = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_ALL);
            ResultSet rs = psmt.executeQuery();

            while(rs.next()){
                Integer id = rs.getInt(1);
                String libelle = rs.getString(2);
                categorieList.add(new CategorieBO(id, libelle));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return categorieList;
    }

}
