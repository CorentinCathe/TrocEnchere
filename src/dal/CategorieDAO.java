package dal;

import bo.CategorieBO;

import java.sql.SQLException;
import java.util.List;

public interface CategorieDAO {

    public List<CategorieBO> selectAll() throws SQLException;
    public CategorieBO selectById(Integer id) throws SQLException;
}
