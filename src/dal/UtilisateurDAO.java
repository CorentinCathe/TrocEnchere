package dal;

import bo.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;




public class UtilisateurDAO {

    public static final String SELECTUSER = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
    public static final String SELECTPASSWORD = "SELECT mdp from UTILISATEURS where pseudo = ?";


    public Utilisateur selectUserByUsername(String username) {
        Utilisateur user=new Utilisateur();
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(SELECTUSER,PreparedStatement.RETURN_GENERATED_KEYS);
            psmt.setString(1,username);;
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
            }else{
                System.out.println("is not resultSet");
            }
        } catch (Exception e) {
            System.out.println("selectUserByUsername");
            System.out.println(e.getMessage());
        }
        return user;
    }

    public boolean compareUserPass(String user, String pass){
        System.out.println(1);
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            System.out.println(2);
            PreparedStatement psmt = cnx.prepareStatement(SELECTUSER);
            System.out.println(3);
            psmt.setString(1,user);
            System.out.println(4);
            ResultSet rs = psmt.executeQuery();
            System.out.println(5);
            if (rs.next()){
                System.out.println(6);
                System.out.println("mdp: "+rs.getString(1));
                System.out.println(7);
                return pass.equals(rs.getString("mdp"));
            }else{
                System.out.println("is not resultSet");
            }
        }catch (Exception e) {
            System.out.println("compareUserPass");
            System.out.println(e.getMessage());
        }
        return false;
    }
}