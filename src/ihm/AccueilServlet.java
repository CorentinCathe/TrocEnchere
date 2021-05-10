package ihm;

import bll.ArticleVenduManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {

    public AccueilServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean isOnPageInscription = false;
        request.setAttribute("isOnPageInscription",isOnPageInscription);
        System.out.println("Connected : "+request.getSession().getAttribute("user")!=null);
        if (request.getAttribute("articlename") != null && request.getAttribute("categorieSelection") != null) {
            String articleName = (String) request.getAttribute("articlename");
            int categorieSelection = (int) request.getAttribute("categorieSelection");
            System.out.println("article name : "+articleName);
            System.out.println("categorie name : "+categorieSelection);
            try {
                ArticleVenduManager avm = new ArticleVenduManager();
                if(articleName.equals("") && categorieSelection == 0) {
                    request.setAttribute("listeArticlesVendus", avm.selectAll());
                }else if (articleName.equals("")){
                    request.setAttribute("listeArticlesVendus", avm.selectByCategorieId(categorieSelection));
                } else if (!articleName.equals("") && categorieSelection != 0){
                    request.setAttribute("listeArticlesVendus", avm.selectByName(articleName,categorieSelection));
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
