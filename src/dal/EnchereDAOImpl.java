package dal;

import bo.Enchere;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class EnchereDAOImpl implements EnchereDAO{

    public static final String SELECTALLENCHERE = "SELECT * FROM ENCHERES";
    public static final String SELECTPASSWORD = "SELECT mdp from UTILISATEURS where pseudo = ? or email = ?";
    public static final String INSERTUSER = "INSERT INTO UTILISATEURS VALUES (?,?,?,?,?,?,?,?,?,0,'true')";
    public static final String UPDATEUSER = "UPDATE UTILISATEURS SET nom = ?,prenom = ?,tel = ?,rue = ?,cp = ?,ville = ?,mdp = ? WHERE pseudo = ? or email = ?";

    @Override
    public List<Enchere> selectAll() {  return null; }

    @Override
    public List<Enchere> selectAllByIdArticle(int id) {
        return null;
    }

    @Override
    public Enchere selectAllByUsername(int id) {
        return null;
    }

    @Override
    public Enchere selectOneById(int id) {
        return null;
    }

    @Override
    public Enchere insertEnchere(Enchere enchere) {
        return null;
    }
}
