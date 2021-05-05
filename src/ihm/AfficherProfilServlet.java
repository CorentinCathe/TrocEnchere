package ihm;
import bll.UtilisateurManager;
import bo.Utilisateur;
import jdk.jshell.execution.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/afficherprofil")
public class AfficherProfilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(((Utilisateur)req.getSession().getAttribute("user")).getPrenom());
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/AfficherProfil.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(((Utilisateur)req.getSession().getAttribute("user")).getPrenom());
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/AfficherProfil.jsp");
        rd.forward(req, resp);
    }
}