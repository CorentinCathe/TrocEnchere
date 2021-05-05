package bll;

import bo.Utilisateur;
import dal.DAOFactory;
import dal.UtilisateurDAO;

public class UtilisateurManager {
    public Utilisateur ajouterUnUtilisateur(Utilisateur user) {
        UtilisateurDAO aDAO = DAOFactory.getUtilisateurDAO();
        user = aDAO.createAccount(user);
        return user;
    }


    public Utilisateur Connection(String pseudo,String mdp) {
        UtilisateurDAO aDAO = DAOFactory.getUtilisateurDAO();
        if (aDAO.compareUserPass(pseudo, mdp))
            return aDAO.selectUserByUsername(pseudo);
        else
            return null;
    }
}
