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
import bean.InserimentoBean;
import bean.OrdineBean;
import bean.PagamentoBean;
import bean.UtenteBean;
import model.IndirizzoModel;
import model.InserimentoModel;
import model.OrdineModel;
import model.PagamentoModel;

/**
 * Servlet implementation class OrdineControl
 */
@WebServlet("/OrdineControl")
public class OrdineControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String senzaAutorizzazione = "AccessoRifiutato.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdineControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String azione = request.getParameter("azione");
		OrdineModel ordMdl = new OrdineModel();
		InserimentoModel insMdl = new InserimentoModel();
		IndirizzoModel indMdl = new IndirizzoModel();
		List<OrdineBean> listaOrdini = null;;
		UtenteBean usr = null;
		PagamentoBean pagamento = null;
		PagamentoModel pagMdl = new PagamentoModel();
		String utente = null;
		RequestDispatcher dis = null;
		
		switch(azione)
		{
		case "visualizzaOrdini":
			usr = (UtenteBean) request.getSession().getAttribute("user");
			
			if(usr != null)
			{
			try {
				listaOrdini = ordMdl.restituisciOrdiniUtente(usr.getUsername());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			dis = this.getServletContext().getRequestDispatcher("/ListaOrdiniUtente.jsp");
			request.setAttribute("listaOrdini", listaOrdini);
			dis.forward(request, response);
			} else {
				response.sendRedirect("login.jsp");
			}
			break;
		case "visualizzaOrdine":
			usr = (UtenteBean) request.getSession().getAttribute("user");
			String idOrdine = request.getParameter("idOrdine");
			if(usr == null)
				response.sendRedirect("login.jsp");
			else
			{
				List<InserimentoBean> listaInserimenti = null;
				OrdineBean ordine = null;
				IndirizzoBean indirizzo = null;
				try {
					
					ordine = ordMdl.restituisciOrdine(Integer.parseInt(idOrdine));
					indirizzo = indMdl.recuperaIndirizzo(ordine.getIdIndirizzo());
					pagamento = pagMdl.recuperaPagamento(ordine.getIdPagamento());
					listaInserimenti = insMdl.restiuisciInserimenti(Integer.parseInt(idOrdine));
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
				if(listaInserimenti != null && listaInserimenti.size()!=0 && (listaInserimenti.get(0).getUsername().equals(usr.getUsername())||usr.getAutorizzazione().equals("Amministratore")))
				{
					dis = this.getServletContext().getRequestDispatcher("/ListaInserimentiUtente.jsp");
					request.setAttribute("indirizzo", indirizzo);
					request.setAttribute("pagamento", pagamento);
					request.setAttribute("listaInserimenti", listaInserimenti);
					request.setAttribute("ordine", ordine);
					dis.forward(request, response);
				} else {
					response.sendRedirect(senzaAutorizzazione);
				}
			
			}
			break;
		case "visualizzaOrdiniAmministratore":
			String ordinazione = request.getParameter("ordinazione");
			String ordine = request.getParameter("ordine");
			utente = request.getParameter("utente");
			usr = (UtenteBean) request.getSession().getAttribute("user");
			if(usr!=null&&!usr.getAutorizzazione().equals("Amministratore")) {
				response.sendRedirect(senzaAutorizzazione);
			} else {
			List<OrdineBean> listaOrdiniAmministratore = null;
			try {
			if(ordinazione!=null && ordine !=null)
			{
					listaOrdiniAmministratore = ordMdl.restituisciOrdiniBy(ordine.equals("asc"), ordinazione, utente);
			} else {
				listaOrdiniAmministratore = ordMdl.restituisciOrdini();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			dis = this.getServletContext().getRequestDispatcher("/ListaOrdiniAmministratore.jsp");
			request.setAttribute("listaOrdini", listaOrdiniAmministratore);
			dis.forward(request, response);
			}
			break;
		case "OrdiniUtenteAmministratore":
		 utente = request.getParameter("utente");
		
		
		try {
			listaOrdini = ordMdl.restituisciOrdiniUtente(utente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dis = this.getServletContext().getRequestDispatcher("/ListaOrdiniAmministratore.jsp");
		request.setAttribute("listaOrdini", listaOrdini);
		dis.forward(request, response);
		break;
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
 