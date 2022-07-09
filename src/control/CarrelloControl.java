package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Carrello;
import bean.ProdottoBean;
import model.ProdottoModel;

/**
 * Servlet implementation class CarrelloControl
 */
@WebServlet("/CarrelloControl")
public class CarrelloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrelloControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodottoId = request.getParameter("id");
		String action = request.getParameter("azione");
		Integer quantita = null;
		ProdottoModel prMdl = new ProdottoModel();
		ProdottoBean prodotto = null;
		Carrello car = null;
		RequestDispatcher dis = null;
		switch(action)
		{
		case "aggiungi":
			try {
				prodotto= prMdl.recuperaProdotto(Integer.parseInt(prodottoId));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			
			quantita = Integer.parseInt(request.getParameter("quantita"));
			car = (Carrello) request.getSession().getAttribute("carrello");
			
			if(car==null)
			{
				car = new Carrello();
			}
			
			car.aggiungiProdotto(prodotto, car.restituisciQuantita(prodotto)+quantita);
			request.getSession().setAttribute("carrello", car);
			dis = this.getServletContext().getRequestDispatcher("/Carrello.jsp");
			dis.forward(request, response);
			break;
		case "modifica":		
			try {
				prodotto= prMdl.recuperaProdotto(Integer.parseInt(prodottoId));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			quantita = Integer.parseInt(request.getParameter("quantita"));
			car = (Carrello) request.getSession().getAttribute("carrello");
			car.aggiungiProdotto(prodotto, quantita);
			request.getSession().setAttribute("carrello", car);
			dis = this.getServletContext().getRequestDispatcher("/Carrello.jsp");
			dis.forward(request, response);
			break;
		case "visualizza":
			response.sendRedirect("Carrello.jsp");
			break;
		case "rimuovi":
			try {
				prodotto= prMdl.recuperaProdotto(Integer.parseInt(prodottoId));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			car = (Carrello) request.getSession().getAttribute("carrello");
			car.rimuoviProdotto(prodotto);
			request.getSession().setAttribute("carrello", car);
			dis = this.getServletContext().getRequestDispatcher("/Carrello.jsp");
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
