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
        Boolean isOnPageInscription = false;
        req.setAttribute("isOnPageInscription",isOnPageInscription);
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/ModifProfil.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isOnPageInscription = false;
        req.setAttribute("isOnPageInscription",isOnPageInscription);
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
        String newmdp = req.getParameter("newpassword");
        String newMdpVerification = req.getParameter("confirmPassword");
        System.out.println("mdp : " + mdp);
        System.out.println("newmdp : " + newmdp);
        System.out.println("newMdpVerif : " + newMdpVerification);
        UtilisateurManager um = new UtilisateurManager();
        //newmdp = abc verifPW = ""
        System.out.println((!newmdp.equals("") && newMdpVerification.equals("")) || (newmdp.equals("") && !newMdpVerification.equals("")) || !newmdp.equals(newMdpVerification));
        if( (!newmdp.equals("") && newMdpVerification.equals("")) || (newmdp.equals("") && !newMdpVerification.equals("")) || !newmdp.equals(newMdpVerification)){
            req.setAttribute("newPwVerification", "New password not allowed !");
            this.doGet(req, resp);
        }else {
            if(um.connection(username, mdp) != null){
                System.out.println("Test ");
                if (!newmdp.equals("") && !newmdp.equals(mdp)){
                    mdp = newmdp;
                }
                Utilisateur user = new Utilisateur(0, username, nom, prenom, email, tel, rue, cp, ville, mdp, 0, true);
                boolean res = um.modifierUnUtilisateur(user);
                System.out.println(res);
                req.getSession().setAttribute("user",user);
                System.out.println(user);
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/AfficherProfil.jsp");
                rd.forward(req, resp);
            }

            else {
                req.setAttribute("pwVerification", "Wrong password !");
                this.doGet(req, resp);
            }
        }


    }
}