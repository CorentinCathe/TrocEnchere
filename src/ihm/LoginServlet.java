package ihm;
import bll.UtilisateurManager;
import bo.Utilisateur;
import com.sun.source.tree.ReturnTree;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/LoginPage.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UtilisateurManager um = new UtilisateurManager();
        Utilisateur user = um.connection(username,password);
        if(user == null) {
            req.setAttribute("message","Attention , username ou mot de passe incorrect");
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/LoginPage.jsp");
            rd.forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user",user);
        session.setAttribute("connected",true);
        resp.sendRedirect(req.getContextPath()+"/accueil");
    }
}
