
/*NON ELIMINA LE IMMAGINI DEL PRODOTTO--NON SO COME FARE*/
package control;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UtenteBean;
import model.ProdottoModel;

/**
 * Servlet implementation class EliminaProdottoControl
 */
@WebServlet("/EliminaProdottoControl")
public class EliminaProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaProdottoControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoModel prMdl = new ProdottoModel();
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("user");
		Boolean status = false;
		if(utente == null || !utente.getAutorizzazione().equalsIgnoreCase("amministratore"))
		{
			response.sendRedirect("AccessoRifiutato.jsp");
		}
		else
		{
			String id = request.getParameter("id");
			try {
				status = prMdl.rimuoviProdotto(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("titolo", "Eliminazione Prodotto");
		request.setAttribute("messaggio", "Prodotto eliminato con successo!");
		getServletContext().getRequestDispatcher("/Messaggio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
