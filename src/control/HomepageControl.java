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
import bean.UtenteBean;
import model.AziendaModel;
import model.CategoriaModel;
import model.ProdottoModel;
import model.UtenteModel;

/**
 * Servlet implementation class HomepageControl
 */
@WebServlet("/HomepageControl")
public class HomepageControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomepageControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CategoriaBean> listaCategorie = null;
		List<AziendaBean> listaAziende = null;
		CategoriaModel catMdl = new CategoriaModel();
		AziendaModel azdMdl = new AziendaModel();
		
		try {
			listaCategorie = catMdl.recuperaCategorie();
			listaAziende = azdMdl.recuperaAziende();
		} catch (SQLException e) {
			System.out.println("Qualcosa è andato storto nel recupero delle categorie e delle aziende nella servlet per l'homepage");
			e.printStackTrace();
		}
		
		RequestDispatcher dis  = this.getServletContext().getRequestDispatcher("/Homepage.jsp");
		request.setAttribute("listaCategorie", listaCategorie);
		request.setAttribute("listaAziende", listaAziende);
		dis.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
