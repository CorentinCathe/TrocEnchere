package bll;


import bo.EnchereBO;
import dal.DAOFactory;
import dal.EnchereDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class EnchereManager {

    public List<EnchereBO> selectAll() throws SQLException {
        EnchereDAOImpl eDAO = DAOFactory.getEnchereDAO();
        return eDAO.selectAll();
    }

    public List<EnchereBO> selectByArticleId(int id) throws SQLException {
        EnchereDAOImpl eDAO = DAOFactory.getEnchereDAO();
        return eDAO.selectAllByIdArticle(id);
    }

    public List<EnchereBO> selectAllByUserId(int id) throws SQLException {
        EnchereDAOImpl eDAO = DAOFactory.getEnchereDAO();
        return eDAO.selectAllByUserId(id);
    }

    public EnchereBO selectOneById(int id) throws SQLException {
        EnchereDAOImpl eDAO = DAOFactory.getEnchereDAO();
        return eDAO.selectOneById(id);
    }

    public EnchereBO insertEnchere(EnchereBO enchereBO) throws SQLException {
        EnchereDAOImpl eDAO = DAOFactory.getEnchereDAO();
        return eDAO.insertEnchere(enchereBO);
    }

}
