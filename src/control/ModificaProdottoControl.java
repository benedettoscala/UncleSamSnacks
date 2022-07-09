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
import bean.ProdottoBean;
import model.AziendaModel;
import model.CategoriaModel;
import model.ProdottoModel;

/**
 * Servlet implementation class ModificaProdottoControl
 */
@WebServlet("/ModificaProdottoControl")
public class ModificaProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaProdottoControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoBean prodotto = null;
		String id = request.getParameter("id");
		ProdottoModel prMdl = new ProdottoModel();
		CategoriaModel catMdl = new CategoriaModel();
		AziendaModel azdMdl = new AziendaModel();
		List<CategoriaBean> listaCategorie = null;
		List<AziendaBean> listaAziende = null;
		try {
			prodotto = prMdl.recuperaProdotto(Integer.parseInt(id));
			listaAziende = azdMdl.recuperaAziende();
			listaCategorie = catMdl.recuperaCategorie();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/ModificaProdotto.jsp");
		request.setAttribute("prodotto", prodotto);
		request.setAttribute("listaCategorie", listaCategorie);
		request.setAttribute("listaAziende", listaAziende);
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
