package dal;

import bo.EnchereBO;

import java.util.List;

public interface EnchereDAO {
    public List<EnchereBO> selectAll();
    public List<EnchereBO> selectAllByIdArticle(int id);
    public List<EnchereBO> selectAllByUserId(int id);
    public EnchereBO selectOneById(int id);
    public EnchereBO insertEnchere(EnchereBO enchereBO);
    public EnchereBO selectAllEncherInfo(int id);
    public int selectLastMontantByIdArticle(int id);
}
