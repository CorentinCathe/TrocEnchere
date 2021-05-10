package dal;

import bo.UtilisateurBO;

public interface UtilisateurDAO {

    public UtilisateurBO selectUserById(int id);

    public UtilisateurBO selectUserByUsername(String username);

    public boolean compareUserPass(String user, String pass);

    public UtilisateurBO createAccount(UtilisateurBO user);

    public boolean majProfil(UtilisateurBO user);
}
