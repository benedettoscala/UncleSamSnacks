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

/**
 * Servlet implementation class IndirizzoControl
 */
@WebServlet("/IndirizzoControl")
public class IndirizzoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String senzaAutorizzazione = "AccessoRifiutato.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndirizzoControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IndirizzoModel indMdl = new IndirizzoModel();
		String azione = request.getParameter("azione");
		RequestDispatcher dis= null;
		UtenteBean usr = (UtenteBean) request.getSession().getAttribute("user");
		
		switch(azione)
		{
		case "visualizza":
			if(usr!=null)
			{
				List<IndirizzoBean> indirizzi = null;
				try {
					indirizzi = indMdl.recuperaIndirizzi(usr.getUsername());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("indirizzi", indirizzi);
				dis = this.getServletContext().getRequestDispatcher("/SceltaIndirizzo.jsp");
				dis.forward(request, response);
			} else {
				response.sendRedirect("login.jsp");
			}
			
			break;
		case "visualizzaUtente":
			if(usr!=null)
			{
				List<IndirizzoBean> indirizzi = null;
				try {
					indirizzi = indMdl.recuperaIndirizzi(usr.getUsername());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("indirizzi", indirizzi);
				dis = this.getServletContext().getRequestDispatcher("/VisualizzaIndirizziUtente.jsp");
				dis.forward(request, response);
			} else {
				response.sendRedirect("login.jsp");
			}
			
			break;
		case "elimina":
			if(usr!=null)
			{
				Integer idIndirizzo = Integer.parseInt(request.getParameter("idIndirizzo"));
				
				try {
					IndirizzoBean buff = indMdl.recuperaIndirizzo(idIndirizzo);
					if(buff.getUsername().equals(usr.getUsername()))
						indMdl.eliminaIndirizzo(idIndirizzo);
					else
						response.sendRedirect(senzaAutorizzazione);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("IndirizzoControl?azione=visualizzaUtente");
			break;
		case "aggiungiIndirizzo":
			dis = this.getServletContext().getRequestDispatcher("/CreaIndirizzo.jsp");
			dis.forward(request, response);
			break;
		default:
			response.sendRedirect("HomepageControl");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IndirizzoModel indMdl = new IndirizzoModel();
		String azione = request.getParameter("azione");
		IndirizzoBean ind = new IndirizzoBean();
		UtenteBean usr = (UtenteBean) request.getSession().getAttribute("user");
		
		switch(azione)
		{
		case "aggiungi":
			if(usr!=null)
			{
			ind = new IndirizzoBean();
			ind.setCap(Integer.parseInt(request.getParameter("codicePostale")));
			ind.setCitta(request.getParameter("citta"));
			ind.setNumerocivico(Integer.parseInt((request.getParameter("numeroCivico"))));
			ind.setUsername(usr.getUsername());
			ind.setVia(request.getParameter("via"));
			try {
				indMdl.inserisciIndirizzo(ind);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("IndirizzoControl?azione=visualizza");
			} else {
				response.sendRedirect("login.jsp");
			}
			break;
		
		case "aggiungiUtente":
			if(usr!=null)
			{
			ind = new IndirizzoBean();
			ind.setCap(Integer.parseInt(request.getParameter("codicePostale")));
			ind.setCitta(request.getParameter("citta"));
			ind.setNumerocivico(Integer.parseInt((request.getParameter("numeroCivico"))));
			ind.setUsername(request.getParameter("username"));
			ind.setVia(request.getParameter("via"));
			try {
				indMdl.inserisciIndirizzo(ind);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("IndirizzoControl?azione=visualizzaUtente");
			} else {
				response.sendRedirect("login.jsp");
			}
			break;
		}	
	}

}
