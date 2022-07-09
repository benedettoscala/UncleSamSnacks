package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.ProdottoModel;
import model.UtenteModel;

/**
 * Servlet implementation class UtenteControl
 */
@WebServlet("/UtenteControl")
public class UtenteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteModel usrMdl = new UtenteModel();
		
		Gson g = new Gson();
		String keyWord = request.getParameter("keyword");
		String Json = null;
		try {
			
			Json = g.toJson(usrMdl.retrieveUtentiSuggestion(keyWord));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	System.out.println(Json);
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
