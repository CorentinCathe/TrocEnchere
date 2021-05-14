package bll;

import bo.RetraitBO;
import dal.DAOFactory;
import dal.RetraitDAO;

import java.sql.SQLException;

public class RetraitManager {

    public RetraitBO insertRetrait(RetraitBO retrait) throws SQLException {
        RetraitDAO rDAO = DAOFactory.getRetraitDAO();
        return rDAO.insertRetrait(retrait);
    }

    public RetraitBO selectRetraitByArticleId(Integer id) throws SQLException {
        RetraitDAO rDAO = DAOFactory.getRetraitDAO();
        return rDAO.selectByArticleId(id);
    }

    public boolean update(RetraitBO retrait) throws SQLException {
        RetraitDAO rDAO = DAOFactory.getRetraitDAO();
        return rDAO.update(retrait);
    }

}
