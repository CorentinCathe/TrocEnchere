package dal;

import bo.UtilisateurBO;

import java.sql.*;

public class UtilisateurDAOImpl implements UtilisateurDAO{

    public static final String SELECT_USER_BY_ID = "SELECT * FROM UTILISATEURS WHERE id = ?";
    public static final String SELECT_USER = "SELECT * FROM UTILISATEURS WHERE pseudo = ? or email = ?";
    public static final String SELECT_PASSWORD = "SELECT mdp from UTILISATEURS where pseudo = ? or email = ?";
    public static final String INSERT_USER = "INSERT INTO UTILISATEURS VALUES (?,?,?,?,?,?,?,?,?,0,'true')";
    public static final String UPDATE_USER = "UPDATE UTILISATEURS SET nom = ?,prenom = ?,tel = ?,rue = ?,cp = ?,ville = ?,mdp = ? WHERE pseudo = ? or email = ?";

    @Override
    public UtilisateurBO selectUserById(int id) {
        UtilisateurBO user=null;
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_USER_BY_ID);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();

            if(rs.next()){
                String username = rs.getString("pseudo");
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
                user = new UtilisateurBO(id, username, nom, prenom, email, tel, rue, cp, ville, mdp, credit, admin);
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("selectUserById");
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public UtilisateurBO selectUserByUsername(String username) {
        UtilisateurBO user=null;
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_USER);
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
                user = new UtilisateurBO(id, username, nom, prenom, email, tel, rue, cp, ville, mdp, credit, admin);
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("selectUserByUsername");
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public boolean compareUserPass(String user, String pass){
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(SELECT_PASSWORD);
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

    @Override
    public UtilisateurBO createAccount(UtilisateurBO user){
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            int res;
            PreparedStatement psmt = cnx.prepareStatement(INSERT_USER);
            psmt.setString(1,user.getPseudo());
            psmt.setString(2,user.getNom());
            psmt.setString(3,user.getPrenom());
            psmt.setString(4,user.getEmail());
            psmt.setString(5,user.getTel());
            psmt.setString(6,user.getRue());
            psmt.setString(7,user.getCP());
            psmt.setString(8,user.getVille());
            psmt.setString(9,user.getMdp());
            psmt.execute();
            try(ResultSet generatedKeys = psmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    res = generatedKeys.getInt(1);
                    System.out.println(res);
                    user.setId(res);
                } else {
                    throw new SQLException("SQL Problem");
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    //public static final String UPDATEUSER = "UPDATE UTILISATEURS SET pseudo = ?,nom = ?,prenom = ?,email = ?,tel = ?,rue = ?,cp = ?,ville = ?,mdp = ? WHERE pseudo = ? or email = ?";

    @Override
    public boolean majProfil(UtilisateurBO user){
        boolean res = false;
        try (Connection cnx = ConnectionProvider.getConnection();
        ) {
            PreparedStatement psmt = cnx.prepareStatement(UPDATE_USER);
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
            res = psmt.execute();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return !res;
    }
}