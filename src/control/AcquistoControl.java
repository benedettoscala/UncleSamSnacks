package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Carrello;
import bean.InserimentoBean;
import bean.OrdineBean;
import bean.ProdottoBean;
import model.CategoriaModel;
import model.InserimentoModel;
import model.OrdineModel;

/**
 * Servlet implementation class AcquistoControl
 */
@WebServlet("/AcquistoControl")
public class AcquistoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Double spedizione = 1.99;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcquistoControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrello car = (Carrello) request.getSession().getAttribute("carrello");
		OrdineModel ordMdl = new OrdineModel();
		InserimentoModel insMdl = new InserimentoModel();
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
		
		OrdineBean ordine= new OrdineBean();
		ordine.setUtente(request.getParameter("username"));
		ordine.setData(date);
		ordine.setOra(time);
		ordine.setIdPagamento(Integer.parseInt(request.getParameter("idPagamento")));
		ordine.setIdIndirizzo(Integer.parseInt(request.getParameter(("idIndirizzo"))));
		ordine.setTotale((car.getTotale() + spedizione));
		Map<ProdottoBean, Integer> carrello = car.restituisciCarrello();
		InserimentoBean inserimento = null;
		try {
			ordine.setIdOrdine(ordMdl.inserisciOrdine(ordine));
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		System.out.println(ordine);
		for(Map.Entry<ProdottoBean, Integer> prodotto: carrello.entrySet())
		{
			inserimento = new InserimentoBean();
			inserimento.setIva(prodotto.getKey().getIVA());
			inserimento.setOrdine(ordine.getIdOrdine());
			inserimento.setPrezzo(prodotto.getKey().getPrezzoReale());
			inserimento.setProdotto(prodotto.getKey().getIdProdotto());
			inserimento.setQuantita(prodotto.getValue());
			inserimento.setNomeProdotto(prodotto.getKey().getNome());
			inserimento.setUrlImmagine(prodotto.getKey().getUrlImmagine());
			System.out.println(inserimento);
			try {
				insMdl.inserisciInserimento(inserimento);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("carrello", null);
		 
		response.sendRedirect("OrdineControl?azione=visualizzaOrdine&&idOrdine="+ordine.getIdOrdine());
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
