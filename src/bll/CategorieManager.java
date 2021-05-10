package bll;

import bo.CategorieBO;
import dal.CategorieDAO;
import dal.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class CategorieManager {
    public List<CategorieBO> selectAll() throws SQLException {
        CategorieDAO cDAO = DAOFactory.getCategorieDAO();
        return cDAO.selectAll();
    }
    public CategorieBO selectById(Integer id) throws SQLException {
        CategorieDAO cDAO = DAOFactory.getCategorieDAO();
        return cDAO.selectById(id);
    }
}
