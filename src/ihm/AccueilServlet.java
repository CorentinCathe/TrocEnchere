package ihm;

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
        System.out.println(request.getSession().getAttribute("user"));
        if(request.getSession().getAttribute("user") != null) {
            this.doPost(request, response);
        }else {
            request.setAttribute("isConnected", false);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Accueil.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user") == null){
            this.doGet(request, response);
        } else {
            HttpSession session = request.getSession();
            request.setAttribute("user", session.getAttribute("user"));
            request.setAttribute("isConnected", true);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Accueil.jsp");
            rd.forward(request, response);
        }


    }

}