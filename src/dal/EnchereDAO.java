package dal;

import bo.Enchere;

import java.util.List;

public interface EnchereDAO {
    public List<Enchere> selectAll();
    public List<Enchere> selectAllByIdArticle(int id);
    public Enchere selectAllByUsername(int id);
    public Enchere selectOneById(int id);
    public Enchere insertEnchere(Enchere enchere);
}
