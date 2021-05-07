package bll;

import bo.UtilisateurBO;
import dal.DAOFactory;
import dal.UtilisateurDAOImpl;

public class UtilisateurManager {
    public UtilisateurBO ajouterUnUtilisateur(UtilisateurBO user) {
        UtilisateurDAOImpl aDAO = DAOFactory.getUtilisateurDAO();
        user = aDAO.createAccount(user);
        return user;
    }

        public boolean modifierUnUtilisateur(UtilisateurBO user) {
            UtilisateurDAOImpl aDAO = DAOFactory.getUtilisateurDAO();
//            if (aDAO.compareUserPass(user.getPseudo(), user.getMdp()))
                return aDAO.majProfil(user);
//            else
//                return null;
        }

    public UtilisateurBO connection(String pseudo, String mdp) {
        UtilisateurDAOImpl aDAO = DAOFactory.getUtilisateurDAO();
        if (aDAO.compareUserPass(pseudo, mdp))
            return aDAO.selectUserByUsername(pseudo);
        else
            return null;
    }


}
