package ihm;

import bll.ArticleVenduManager;
import bll.CategorieManager;
import bll.RetraitManager;
import bo.ArticleVenduBO;
import bo.RetraitBO;
import bo.UtilisateurBO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/modifierVente")
public class ModifierVenteServlet extends HttpServlet {

    public ModifierVenteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CategorieManager cm = new CategorieManager();
            request.setAttribute("listCat",cm.selectAll());
            ArticleVenduManager avm = new ArticleVenduManager();
            Integer articleId = Integer.parseInt(request.getParameter("articleId"));
            request.setAttribute("articleToUpdate", avm.selectArticleById(articleId));
        }catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ModifierVente.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Date.valueOf(request.getParameter("debutEnchere")).before(Date.valueOf(LocalDate.now())) || Date.valueOf(request.getParameter("finEnchere")).before(Date.valueOf(request.getParameter("debutEnchere")))) {
            System.out.println("Les dates sont non valides !");
        } else {
            try {
                RetraitManager rm = new RetraitManager();
                ArticleVenduManager avm = new ArticleVenduManager();
                CategorieManager cm = new CategorieManager();
                String nomArticle = request.getParameter("articleName");
                String desc = request.getParameter("description");
                Integer categorieId = Integer.parseInt(request.getParameter("categorieSelection"));
                int prixInitial = Integer.parseInt(request.getParameter("initialPrice"));
                Date dateDebut = Date.valueOf(request.getParameter("debutEnchere"));
                Date dateFin = Date.valueOf(request.getParameter("finEnchere"));
                UtilisateurBO user = (UtilisateurBO) request.getSession().getAttribute("user");
                ArticleVenduBO article = new ArticleVenduBO(Integer.parseInt(request.getParameter("articleId")), nomArticle, desc, dateDebut, dateFin, prixInitial, prixInitial);
                article.setCategorie(cm.selectById(categorieId));
                article.setUtilisateur(user);
                avm.updateArticle(article);

                String rue = request.getParameter("rue");
                String cp = request.getParameter("cp");
                String ville = request.getParameter("ville");
                RetraitBO retrait = new RetraitBO(article, rue, cp, ville);
                rm.update(retrait);
                response.sendRedirect(request.getContextPath() + "/accueil");
                return;
            } catch (SQLException throwables) {
                System.out.println("Modification impossible");
                throwables.printStackTrace();
            }
        }
        request.setAttribute("articleId", request.getParameter("articleId"));
       this.doGet(request, response);
    }
}
