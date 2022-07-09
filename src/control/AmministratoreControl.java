package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.UtenteBean;

/**
 * Servlet implementation class AmministratoreControl
 */
@WebServlet("/AmministratoreControl")
public class AmministratoreControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmministratoreControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteBean user = (UtenteBean) request.getSession().getAttribute("user");
		Boolean isAdministrator=false;
		if(user!=null&&user.getAutorizzazione().equals(("Amministratore")))
				isAdministrator= true;
		Gson g = new Gson();
		
		String Json = null;
		Json = g.toJson(isAdministrator);
		
		
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        response.getWriter().write(Json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
