package ihm;

import bll.ArticleVenduManager;
import bll.CategorieManager;
import bo.CategorieBO;
import dal.CategorieDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {

    public AccueilServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategorieBO> listCat = new ArrayList<>();
        try {
            CategorieManager cm = new CategorieManager();
            listCat = cm.selectAll();
            request.setAttribute("listCat",listCat);
        }catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("articlename",request.getParameter("articlename"));
        request.setAttribute("categorieSelection",request.getParameter("categorieSelection"));
        Boolean isOnPageInscription = false;
        request.setAttribute("isOnPageInscription",isOnPageInscription);
        //System.out.println("Connected : "+request.getSession().getAttribute("user")!=null);
        if (request.getParameter("articlename") != null && request.getParameter("categorieSelection") != null){
            System.out.println("param" + request.getParameter("articlename"));
            System.out.println("param" + request.getParameter("categorieSelection"));
            String articleName = request.getParameter("articlename");
            int categorieSelection = Integer.valueOf(request.getParameter("categorieSelection"));
            System.out.println("article name : "+articleName);
            System.out.println("categorie name : "+categorieSelection);
            try {
                ArticleVenduManager avm = new ArticleVenduManager();
                System.out.println("CONDITIONS DE REQUETES article name : "+articleName + " categorie name : "+categorieSelection);
                if(articleName.equals("") && categorieSelection == 0) {
                    System.out.println("APPEL SELECT ALL");
                    request.setAttribute("listeArticlesVendus", avm.selectAll());
                }else if (articleName.equals("")){
                    System.out.println("APPEL SELECT CAT");
                    request.setAttribute("listeArticlesVendus", avm.selectByCategorieId(categorieSelection));
                } else if (!articleName.equals("") && categorieSelection != 0){
                    System.out.println("APPEL SELECT NAME CAT");
                    request.setAttribute("listeArticlesVendus", avm.selectByNameAndCategorie(articleName,categorieSelection));
                } else if (!articleName.equals("") && categorieSelection == 0){
                    System.out.println("APPEL SELECT NAME");
                    request.setAttribute("listeArticlesVendus", avm.selectByName(articleName));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{try {
            ArticleVenduManager avm = new ArticleVenduManager();
            request.setAttribute("listeArticlesVendus", avm.selectAll());
        }catch (Exception e) {
            e.printStackTrace();
        }
        }
        if(request.getSession().getAttribute("connected")==null)
            request.getSession().setAttribute("connected", false);

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Accueil.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean isOnPageInscription = false;
        request.setAttribute("isOnPageInscription",isOnPageInscription);
        if(request.getParameter("disconnect")!=null){
            request.getSession().setAttribute("user",null);
            request.getSession().setAttribute("connected",false);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Accueil.jsp");
            rd.forward(request, response);
            return;
        }
        if (request.getParameter("profil")!= null) {
            request.setAttribute("isConnected", true);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Accueil.jsp");
            rd.forward(request, response);
        }

    }

}
