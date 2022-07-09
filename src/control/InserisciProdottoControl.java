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

import bean.AziendaBean;
import bean.CategoriaBean;
import model.AziendaModel;
import model.CategoriaModel;

/**
 * Servlet implementation class InserisciProdottoControl
 */
@WebServlet("/InserisciProdottoControl")
public class InserisciProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String senzaAutorizzazione = "AccessoRifiutato.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciProdottoControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoriaModel catMdl = new CategoriaModel();
		AziendaModel azdMdl = new AziendaModel();
		List<CategoriaBean> listaCategorie = null;
		List<AziendaBean> listaAziende = null;
		try {
			listaAziende = azdMdl.recuperaAziende();
			listaCategorie = catMdl.recuperaCategorie();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/InserimentoProdotto.jsp");
		request.setAttribute("listaCategorie", listaCategorie);
		request.setAttribute("listaAziende", listaAziende);
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
