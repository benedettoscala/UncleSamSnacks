package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PagamentoBean;
import bean.UtenteBean;
import model.PagamentoModel;

/**
 * Servlet implementation class PagamentoControl
 */
@WebServlet("/PagamentoControl")
public class PagamentoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private static final String senzaAutorizzazione = "AccessoRifiutato.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagamentoControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PagamentoModel pagMdl = new PagamentoModel();
		String azione = request.getParameter("azione");
		RequestDispatcher dis= null;
		UtenteBean usr =null;
		
		switch(azione)
		{
		/*questo caso si ha quando l'utente deve scegliere quale pagamento utilizzare per il suo ordine*/
		case "visualizza":
			usr = (UtenteBean) request.getSession().getAttribute("user");
			if(usr!=null)
			{
				List<PagamentoBean> pagamenti= null;
				try {
					pagamenti = pagMdl.recuperaPagamenti(usr.getUsername());
					System.out.println(pagamenti);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("pagamenti", pagamenti);
			}
			dis = this.getServletContext().getRequestDispatcher("/SceltaPagamento.jsp");
			dis.forward(request, response);
			break;
		/*in questo caso la servlet manda alla view le carte di credito di un determinato utente
		 * Puo accederci solo l'amministratore
		 */
		case "visualizzaAmministratore":
			usr = (UtenteBean) request.getSession().getAttribute("user");
			if(usr!=null && usr.getAutorizzazione().equals("Amministratore")) {
			String utente = request.getParameter("utente");
			if(utente!=null)
			{
				List<PagamentoBean> pagamenti= null;
				try {
					pagamenti = pagMdl.recuperaPagamenti(utente);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("carte", pagamenti);
			}
			dis = this.getServletContext().getRequestDispatcher("/VisualizzaPagamentiAmministratore.jsp");
			dis.forward(request, response);
			} else {
				System.out.println("prova");
				response.sendRedirect(senzaAutorizzazione);
			}
			break;
			/*in questo caso la servlet manda alla view le carte di credito di un determinato utente
			 * Puo accederci solo l'utente possessore delle carte
			 */
		case "visualizzaUtente":
			usr = (UtenteBean) request.getSession().getAttribute("user");
			if(usr!=null)
			{
				List<PagamentoBean> pagamenti= null;
				try {
					pagamenti = pagMdl.recuperaPagamenti(usr.getUsername());
					System.out.println(pagamenti);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("pagamenti", pagamenti);
			}
			dis = this.getServletContext().getRequestDispatcher("/VisualizzaPagamentiUtente.jsp");
			dis.forward(request, response);
			break;
			/*in questo caso la servlet elimina una determinata carta di credito
			 */
		case "elimina":
			usr = (UtenteBean) request.getSession().getAttribute("user");
			Integer idPagamento = Integer.parseInt(request.getParameter("idPagamento"));
			if(usr!=null)
			{
				try {
					PagamentoBean buff = pagMdl.recuperaPagamento(idPagamento);
					if(buff.getUsername().equals(usr.getUsername()))
						pagMdl.eliminaPagamento(idPagamento);
					else
						response.sendRedirect("HomepageControl");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("PagamentoControl?azione=visualizzaUtente");
			break;
			/*in questo caso la servlet reindirizza l'utente alla pagina di creazione di carta di credito
			 */
		case "aggiungiPagamento":
			dis = this.getServletContext().getRequestDispatcher("/CreaPagamento.jsp");
			dis.forward(request, response);
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PagamentoModel pagMdl = new PagamentoModel();
		String azione = request.getParameter("azione");
		System.out.println(azione);
		UtenteBean usr = (UtenteBean) request.getSession().getAttribute("user");
		RequestDispatcher dis= null;
		PagamentoBean pag = null;
		
		switch(azione)
		{
		case "aggiungi":
			if(usr!=null) {
			pag = new PagamentoBean();
			pag.setNome(request.getParameter("nome"));
			pag.setCognome(request.getParameter("cognome"));
			pag.setDataScadenza(Date.valueOf(request.getParameter("dataScadenza")));
			pag.setUsername(usr.getUsername());
			pag.setCvv(Integer.parseInt(request.getParameter("cvv")));
			pag.setNumeroCarta(request.getParameter("numeroCarta"));
			System.out.println(pag + "prova");
			try {
				pagMdl.inserisciPagamento(pag);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("PagamentoControl?azione=visualizza&idIndirizzo="+request.getParameter("idIndirizzo"));
			} else {
				response.sendRedirect(senzaAutorizzazione);
			}
			break;
		case "visualizza":
			if(usr!=null)
			{
				List<PagamentoBean> pagamenti= null;
				try {
					pagamenti = pagMdl.recuperaPagamenti(usr.getUsername());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("pagamenti", pagamenti);
				dis = this.getServletContext().getRequestDispatcher("/SceltaPagamento.jsp");
				dis.forward(request, response);
			} else {
				response.sendRedirect("login.jsp");
			}
			
			break;
		case "aggiungiUtente":
			if(usr!=null) {
			pag = new PagamentoBean();
			pag.setNome(request.getParameter("nome"));
			pag.setCognome(request.getParameter("cognome"));
			pag.setDataScadenza(Date.valueOf(request.getParameter("dataScadenza")));
			pag.setUsername(usr.getUsername());
			pag.setCvv(Integer.parseInt(request.getParameter("cvv")));
			pag.setNumeroCarta(request.getParameter("numeroCarta"));
			try {
				pagMdl.inserisciPagamento(pag);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("PagamentoControl?azione=visualizzaUtente");
			} else {
				response.sendRedirect(senzaAutorizzazione);
			}
			break;
		}
	}

}
