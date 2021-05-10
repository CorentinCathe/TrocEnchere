package ihm;
import bll.UtilisateurManager;
import bo.UtilisateurBO;
import dal.security.SecurityImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isOnPageInscription = true;
        req.setAttribute("isOnPageInscription",isOnPageInscription);
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/Inscription.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String nom = req.getParameter("firstName");
        String prenom = req.getParameter("lastName");
        String email = req.getParameter("mail");
        String tel = req.getParameter("phoneNumber");
        String rue = req.getParameter("adresse");
        String cp = req.getParameter("cp");
        String ville = req.getParameter("city");
        String mdp = req.getParameter("password");
        UtilisateurBO user = new UtilisateurBO(0, username, nom, prenom, email, tel, rue, cp, ville, mdp, 0, true);
        System.out.println(SecurityImpl.checkUserSafe(user));
        if (SecurityImpl.checkUserSafe(user)) {
            System.out.println("original : " + mdp);
            mdp = SecurityImpl.hashPassword(mdp);
            System.out.println("hash : " + mdp);
            user.setMdp(mdp);
            UtilisateurManager um = new UtilisateurManager();
            um.ajouterUnUtilisateur(user);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp");
            rd.forward(req, resp);
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/Inscription.jsp");
        rd.forward(req, resp);
        return;

    }
}