package ihm;

import bll.*;
import bo.*;
import dal.RetraitDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/vendreArticle")
public class VendreArticleServlet extends HttpServlet {

    public VendreArticleServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CategorieManager cm = new CategorieManager();
            request.setAttribute("listCat",cm.selectAll());
            System.out.println(request.getAttribute("listCat"));
        }catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/VendreArticle.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try {
             RetraitManager rm = new RetraitManager();
             ArticleVenduManager avm = new ArticleVenduManager();
             CategorieManager cm = new CategorieManager();
             EnchereManager em = new EnchereManager();
             String nomArticle = request.getParameter("articleName");
             String desc = request.getParameter("description");
             Integer categorieId = Integer.parseInt(request.getParameter("categorieSelection"));
             int prixInitial = Integer.parseInt(request.getParameter("initialPrice"));
             Date dateDebut = Date.valueOf(request.getParameter("debutEnchere"));
             Date dateFin = Date.valueOf(request.getParameter("finEnchere"));
             UtilisateurBO user = (UtilisateurBO) request.getSession().getAttribute("user");
             ArticleVenduBO article = new ArticleVenduBO(0, nomArticle, desc,dateDebut, dateFin , prixInitial,prixInitial);
             article.setCategorie(cm.selectById(categorieId));
             article.setUtilisateur(user);
             article = avm.insertArticle(article);

             EnchereBO enchere = new EnchereBO(Date.valueOf(LocalDate.now()),article.getPrixInitial(),article,article.getUtilisateur());
             em.insertEnchere(enchere);

             String rue = request.getParameter("rue");
             String cp = request.getParameter("cp");
             String ville = request.getParameter("ville");
             RetraitBO retrait = new RetraitBO(article, rue, cp, ville);
             rm.insertRetrait(retrait);

             RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
             rd.forward(request, response);

             response.sendRedirect(request.getContextPath() + "/accueil");

             return;
        } catch (SQLException throwables) {
            System.out.println("Mise en vente impossible");
            throwables.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/VendreArticle.jsp");
        rd.forward(request, response);

    }

}
