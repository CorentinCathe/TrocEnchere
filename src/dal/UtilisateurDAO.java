package dal;

import bo.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UtilisateurDAO {
    public Utilisateur selectUserByUsername(String username) {
        Utilisateur user=new Utilisateur();
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            Statement psmt = cnx.createStatement();
            ResultSet rs = psmt.executeQuery("SELECT * from UTILISATEURS where pseudo = " + username);
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public boolean compareUserPass(String user, String pass){
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            Statement psmt = cnx.createStatement();
            ResultSet rs = psmt.executeQuery("SELECT mdp from UTILISATEURS where pseudo = " + user);
            return pass.equals(rs.getString("mdp"));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}