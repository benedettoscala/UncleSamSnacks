package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UtenteBean;
import model.UtenteModel;

/**
 * Servlet implementation class ListaUtentiControl
 */
@WebServlet("/ListaUtentiControl")
public class ListaUtentiControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String senzaAutorizzazione = "AccessoRifiutato.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaUtentiControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteModel utMdl = new UtenteModel();
		UtenteBean user = (UtenteBean) request.getSession().getAttribute("user");
		
		if(user==null||!user.getAutorizzazione().equals("Amministratore"))
			response.sendRedirect(senzaAutorizzazione);
		else {
		List<UtenteBean> listaUtenti = null;
		
		try {
			listaUtenti = utMdl.retrieveUtenti();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/ListaUtenti.jsp");
		request.setAttribute("listaUtenti", listaUtenti);
		dis.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
