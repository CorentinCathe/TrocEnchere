package ihm;

import bll.ArticleVenduManager;
import bll.CategorieManager;
import bo.ArticleVenduBO;
import bo.CategorieBO;
import bo.UtilisateurBO;
import dal.CategorieDAO;
import utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {

    public AccueilServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("connected")==null)
            request.getSession().setAttribute("connected", false);
        Boolean isOnPageInscription = false;

        request.setAttribute("isOnPageInscription",isOnPageInscription);
        request.setAttribute("articlename",request.getParameter("articlename"));
        request.setAttribute("categorieSelection",request.getParameter("categorieSelection"));
        request.setAttribute("radio",request.getParameter("radio"));
        request.setAttribute("checkAchatOuverte",request.getParameter("checkAchatOuverte"));
        request.setAttribute("checkAchatmesEncheres",request.getParameter("checkAchatmesEncheres"));
        request.setAttribute("checkAchatWin",request.getParameter("checkAchatWin"));
        request.setAttribute("checkVenteEnCours",request.getParameter("checkVenteEnCours"));
        request.setAttribute("checkVenteNotStarted",request.getParameter("checkVenteNotStarted"));
        request.setAttribute("checkVenteFinish",request.getParameter("checkVenteFinish"));
        try {
            CategorieManager cm = new CategorieManager();
            request.setAttribute("listCat",cm.selectAll());
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (request.getSession().getAttribute("user")==null) {
                System.out.println("param" + request.getParameter("articlename"));
                System.out.println("param" + request.getParameter("categorieSelection"));
                String articleName = request.getParameter("articlename");
                Integer categorieSelection;
                if (request.getParameter("categorieSelection")!=null)
                    categorieSelection = Integer.valueOf(request.getParameter("categorieSelection"));
                else
                    categorieSelection = null;
                System.out.println("article name : " + articleName);
                System.out.println("categorie name : " + categorieSelection);
                request.setAttribute("listeArticlesVendus", this.encheresOuvertes(null, articleName,categorieSelection));
        }else{
            String selecteur = "";
            String ouverte = "";
            if (request.getParameter("radio")==null){
                selecteur = "achat";
                ouverte = "ouverte";
                request.setAttribute("radio","achat");
                request.setAttribute("checkAchatOuverte","ouverte");
            }else{
                selecteur = request.getParameter("radio");
                ouverte = request.getParameter("checkAchatOuverte");
            }
            Integer categorieSelection = null;
            if (request.getParameter("categorieSelection") != null)
                categorieSelection = Integer.parseInt(request.getParameter("categorieSelection"));
            String articleName = request.getParameter("articlename");
            String mesEncheres = request.getParameter("checkAchatmesEncheres");
            String mesWin = request.getParameter("checkAchatWin");
            String ventesEnCours = request.getParameter("checkVenteEnCours");
            String mesVentesNotStarted = request.getParameter("checkVenteNotStarted");
            String mesVentesFinish = request.getParameter("checkVenteFinish");
            List<ArticleVenduBO> listArticle = new ArrayList<>();
            ArticleVenduManager avm = new ArticleVenduManager();
            try {
                if (selecteur.equals("achat")) {
                    if (ouverte != null)
                        this.encheresOuvertes(listArticle, articleName, categorieSelection);
                    if (mesEncheres != null)
                        listArticle = Utils.ajouteSiExistePas(listArticle, avm.selectAtLeastOneBet(((UtilisateurBO) request.getSession().getAttribute("user")).getId()));
                    if (mesWin != null)
                        listArticle = Utils.ajouteSiExistePas(listArticle, avm.selectWon(((UtilisateurBO) request.getSession().getAttribute("user")).getId()));
                } else {
                    if (ventesEnCours != null)
                        listArticle = Utils.ajouteSiExistePas(listArticle, avm.selectAllSell(((UtilisateurBO) request.getSession().getAttribute("user")).getId()));
                    if (mesVentesNotStarted != null)
                        listArticle = Utils.ajouteSiExistePas(listArticle, avm.selectAllNotStartedSell(((UtilisateurBO) request.getSession().getAttribute("user")).getId()));
                    if (mesVentesFinish != null)
                        listArticle = Utils.ajouteSiExistePas(listArticle, avm.selectAllFinishedSell(((UtilisateurBO) request.getSession().getAttribute("user")).getId()));
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("listeArticlesVendus", listArticle);
            Date localDate = Date.valueOf(LocalDate.now());
            request.setAttribute("localDate", localDate);
        }



        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Accueil.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean isOnPageInscription = false;
        request.setAttribute("isOnPageInscription",isOnPageInscription);
        if(request.getParameter("disconnect")!=null){
            request.getSession().setAttribute("user",null);
            request.getSession().setAttribute("connected",false);
            this.doGet(request,response);
            return;
        }
        if (request.getParameter("profil")!= null) {
            request.setAttribute("isConnected", true);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Accueil.jsp");
            rd.forward(request, response);
        }

    }

    private List<ArticleVenduBO> encheresOuvertes(List<ArticleVenduBO> list, String articleName, Integer categorieSelection){
        if (list==null)
            list = new ArrayList<>();
        if (articleName != null && categorieSelection != null) {
            try {
                ArticleVenduManager avm = new ArticleVenduManager();
                System.out.println("CONDITIONS DE REQUETES article name : " + articleName + " categorie name : " + categorieSelection);
                if (articleName.equals("") && categorieSelection == 0) {
                    System.out.println("APPEL SELECT ALL");
                    list.addAll(avm.selectAll());
                } else if (articleName.equals("")) {
                    System.out.println("APPEL SELECT CAT");
                    list.addAll(avm.selectByCategorieId(categorieSelection));
                } else if (!articleName.equals("") && categorieSelection != 0) {
                    System.out.println("APPEL SELECT NAME CAT");
                    list.addAll(avm.selectByNameAndCategorie(articleName, categorieSelection));
                } else if (!articleName.equals("") && categorieSelection == 0) {
                    System.out.println("APPEL SELECT NAME");
                    list.addAll(avm.selectByName(articleName));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ArticleVenduManager avm = new ArticleVenduManager();
                list.addAll(avm.selectAll());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }


}
