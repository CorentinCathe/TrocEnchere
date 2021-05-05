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

        public Utilisateur modifierUnUtilisateur(Utilisateur user) {
            UtilisateurDAO aDAO = DAOFactory.getUtilisateurDAO();
//            if (aDAO.compareUserPass(user.getPseudo(), user.getMdp()))
                return aDAO.majProfil(user);
//            else
//                return null;
        }

    public Utilisateur connection(String pseudo,String mdp) {
        UtilisateurDAO aDAO = DAOFactory.getUtilisateurDAO();
        if (aDAO.compareUserPass(pseudo, mdp))
            return aDAO.selectUserByUsername(pseudo);
        else
            return null;
    }


}
