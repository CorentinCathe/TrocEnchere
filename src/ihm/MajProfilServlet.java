package ihm;
import bll.UtilisateurManager;
import bo.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/majprofil")
public class MajProfilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/ModifProfil.jsp");
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
        System.out.println("new pass = "+req.getParameter("newpassword"));
//        if (!req.getParameter("newpassword").equals("")) {
//            mdp = req.getParameter("newpassword");
//        }
        //int credit = Integer.valueOf(req.getParameter("credit"));
        //boolean admin = Boolean.valueOf(req.getParameter("admin"));
        Utilisateur user = new Utilisateur(0, username, nom, prenom, email, tel, rue, cp, ville, mdp, 0, true);
        UtilisateurManager um = new UtilisateurManager();
        user = um.modifierUnUtilisateur(user);
        req.getSession().setAttribute("user",user);
//        if (user != null)
//
//        else
        System.out.println(user);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/AfficherProfil.jsp");
        rd.forward(req, resp);
    }
}