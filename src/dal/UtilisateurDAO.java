package dal;

import bo.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UtilisateurDAO {

    public static final String SELECTUSER = "SELECT * FROM UTILISATEURS WHERE pseudo = ? or email = ?";
    public static final String SELECTPASSWORD = "SELECT mdp from UTILISATEURS where pseudo = ? or email = ?";
    public static final String INSERTUSER = "INSERT INTO UTILISATEURS VALUES (?,?,?,?,?,?,?,?,?,0,'true')";
    public static final String UPDATEUSER = "UPDATE UTILISATEURS SET nom = ?,prenom = ?,tel = ?,rue = ?,cp = ?,ville = ?,mdp = ? WHERE pseudo = ? or email = ?";


    public Utilisateur selectUserByUsername(String username) {
        Utilisateur user=null;
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(SELECTUSER);
            psmt.setString(1,username);
            psmt.setString(2,username);
            ResultSet rs = psmt.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String rue = rs.getString("rue");
                String cp = rs.getString("CP");
                String ville = rs.getString("ville");
                String mdp = rs.getString("mdp");
                int credit = rs.getInt("credit");
                boolean admin = rs.getBoolean("admin");
                user = new Utilisateur(id, username, nom, prenom, email, tel, rue, cp, ville, mdp, credit, admin);
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("selectUserByUsername");
            System.out.println(e.getMessage());
        }
        return user;
    }

    public boolean compareUserPass(String user, String pass){
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(SELECTPASSWORD);
            psmt.setString(1,user);
            psmt.setString(2,user);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()){
                return pass.equals(rs.getString("mdp"));
            }else{
                return false;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Utilisateur createAccount(Utilisateur user){
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(INSERTUSER);
            psmt.setString(1,user.getPseudo());
            psmt.setString(2,user.getNom());
            psmt.setString(3,user.getPrenom());
            psmt.setString(4,user.getEmail());
            psmt.setString(5,user.getTel());
            psmt.setString(6,user.getRue());
            psmt.setString(7,user.getCP());
            psmt.setString(8,user.getVille());
            psmt.setString(9,user.getMdp());
            psmt.executeQuery();
//            if (rs.next()){
//                user.setId(psmt.getGeneratedKeys().getInt(1));
//            }else{
//                return null;
//            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    //public static final String UPDATEUSER = "UPDATE UTILISATEURS SET pseudo = ?,nom = ?,prenom = ?,email = ?,tel = ?,rue = ?,cp = ?,ville = ?,mdp = ? WHERE pseudo = ? or email = ?";

    public Utilisateur majProfil(Utilisateur user){
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(UPDATEUSER);
            //psmt.setString(1,user.getPseudo());
            psmt.setString(1,user.getNom());
            psmt.setString(2,user.getPrenom());
            //psmt.setString(4,user.getEmail());
            psmt.setString(3,user.getTel());
            psmt.setString(4,user.getRue());
            psmt.setString(5,user.getCP());
            psmt.setString(6,user.getVille());
            psmt.setString(7,user.getMdp());
            psmt.setString(8,user.getPseudo());
            psmt.setString(9,user.getEmail());
            psmt.executeQuery();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}