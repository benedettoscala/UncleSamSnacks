package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.UtenteModel;

/**
 * Servlet implementation class ControllaRegistrazioneControl
 */
@WebServlet("/ControllaRegistrazioneControl")
public class ControllaRegistrazioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllaRegistrazioneControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteModel utMdl = new UtenteModel();
		Gson g = new Gson();
		String Json = null;
		String azione = request.getParameter("azione");
		switch(azione)
		{
		case "username":
			String username = request.getParameter("username");
			
			try {
				Json = g.toJson(utMdl.trovaUsername(username));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Json = "<result>"+ Json +"</result>";
	        response.setContentType("application/xml");
	        response.setCharacterEncoding("UTF-8");

	        System.out.println(Json);
	        response.getWriter().write(Json);
			break;
		case "email":
			String email = request.getParameter("email");
			try {
				Json = g.toJson(utMdl.trovaEmail(email));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Json = "<result>"+ Json +"</result>";
	        response.setContentType("application/xml");
	        response.setCharacterEncoding("UTF-8");


	        response.getWriter().write(Json);
	        break;
		}
		
        
	}

}
