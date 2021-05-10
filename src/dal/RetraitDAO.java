package dal;

import bo.RetraitBO;

import java.sql.SQLException;
import java.util.List;

public interface RetraitDAO {

    public RetraitBO insertRetrait(RetraitBO retrait) throws SQLException;

    public RetraitBO selectByArticleId(Integer articleId) throws SQLException;
}
