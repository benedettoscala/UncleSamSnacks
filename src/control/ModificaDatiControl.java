package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UtenteBean;
import model.UtenteModel;

/**
 * Servlet implementation class ModificaDatiControl
 */
@WebServlet("/ModificaDatiControl")
public class ModificaDatiControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaDatiControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteBean usr = null;
		UtenteModel utMdl = new UtenteModel();
		usr = (UtenteBean) request.getSession().getAttribute("user");
		String vecchioUsername = usr.getUsername();
		usr.setCognome(request.getParameter("cognome"));
		usr.setDataNascita(Date.valueOf(request.getParameter("data")));
		usr.setEmail(request.getParameter("email"));
		usr.setNome(request.getParameter("nome"));
		usr.setPassword(request.getParameter("password"));
		usr.setUsername(request.getParameter("username"));
		try {
			if(utMdl.modificaUtente(usr, vecchioUsername));
				System.out.println("utente Modificato");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("CatalogoControl");
	}

}
