package ihm;

import bll.EnchereManager;
import bll.RetraitManager;
import bo.EnchereBO;
import bo.RetraitBO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@WebServlet("/enchere")
public class EnchereServlet extends HttpServlet {

    EnchereBO enchere = null;
    EnchereManager em = new EnchereManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isOnPageInscription = false;
        req.setAttribute("isOnPageInscription",isOnPageInscription);
        RetraitManager rm = new RetraitManager();
        String idArticle = req.getParameter("articleId");
        RetraitBO retrait = null;

        try {
            enchere = em.selectAllEncherInfo(Integer.parseInt(idArticle));
            retrait = rm.selectRetraitByArticleId(Integer.parseInt(idArticle));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        HttpSession session = req.getSession();
        session.setAttribute("enchere",enchere);
        session.setAttribute("retrait",retrait);
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/DetailEnchere.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offre = Integer.parseInt(req.getParameter("offre"));
        if (enchere.getMontant() < offre && enchere.getDate().after(enchere.getArticle().getDateFinEncheres())){
            try {
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                enchere = new EnchereBO(date,offre,enchere.getArticle(), enchere.getUtilisateur());
                em.insertEnchere(enchere);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            HttpSession session = req.getSession();
            try {
                session.setAttribute("enchere",em.selectOneById(enchere.getId()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/DetailEnchere.jsp");
            rd.forward(req, resp);
            return;
        }else{
            req.setAttribute("message","Vous ne pouvez pas faire une enchère plus basse que l'enchère actuel");
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/DetailEnchere.jsp");
            rd.forward(req, resp);
            return;
        }
    }
}
