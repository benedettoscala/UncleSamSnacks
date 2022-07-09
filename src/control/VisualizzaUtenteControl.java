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

import bean.IndirizzoBean;
import bean.UtenteBean;
import model.IndirizzoModel;
import model.UtenteModel;

/**
 * Servlet implementation class VisualizzaUtenteControl
 */
@WebServlet("/VisualizzaUtenteControl")
public class VisualizzaUtenteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String senzaAutorizzazione = "AccessoRifiutato.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaUtenteControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteModel utMdl = new UtenteModel();
		IndirizzoModel indMdl = new IndirizzoModel();
		UtenteBean user = (UtenteBean) request.getSession().getAttribute("user");
		List<IndirizzoBean> listaIndirizzi = null;
		
		if(user==null||!user.getAutorizzazione().equals("Amministratore"))
			response.sendRedirect(senzaAutorizzazione);
			//TODO Fai un bel messaggio di errore
		else {
		UtenteBean utente= null;
		
		try {
			listaIndirizzi = indMdl.recuperaIndirizzi(request.getParameter("utente"));
			utente = utMdl.retrieveUtente(request.getParameter("utente"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/VisualizzaUtente.jsp");
		request.setAttribute("utente", utente);
		request.setAttribute("listaIndirizzi", listaIndirizzi);
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
