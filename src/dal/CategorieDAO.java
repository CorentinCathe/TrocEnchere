package dal;

import bo.CategorieBO;

import java.sql.SQLException;

public interface CategorieDAO {

    public CategorieBO selectById(Integer id) throws SQLException;
}
