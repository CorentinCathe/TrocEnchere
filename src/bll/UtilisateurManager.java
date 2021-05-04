package bll;

import bo.Utilisateur;
import dal.DAOFactory;
import dal.UtilisateurDAO;

public class UtilisateurManager {
//    public void ajouterUnUtilisateur(String aliment,int numRepas) {
//        Utilisateur a = new Utilisateur(aliment,numRepas);
//        UtilisateurDAO aDAO = DAOFactory.getUtilisateurDAO();
//        aDAO.insert(a);
//    }

    public Utilisateur Connection(String pseudo,String mdp) {
        UtilisateurDAO aDAO = DAOFactory.getUtilisateurDAO();
        return aDAO.selectUserByUsername(pseudo);
    }
}
