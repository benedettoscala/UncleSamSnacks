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

import com.google.gson.Gson;

import bean.AziendaBean;
import bean.CategoriaBean;
import bean.ProdottoBean;
import model.AziendaModel;
import model.CategoriaModel;
import model.ProdottoModel;

/**
 * Servlet implementation class CatalogoControl
 */
@WebServlet("/CatalogoControl")
public class CatalogoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogoControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProdottoBean> lista = null;
		List<AziendaBean> listaAziende = null;
		List<CategoriaBean> listaCategorie = null;
		String Json = null;
		String pagestr = request.getParameter("pagina");
		if(pagestr==null) pagestr="1";
		Integer page = Integer.parseInt(pagestr);
		if(page<=0) page=1;
		
		ProdottoModel prMdl = new ProdottoModel();
		AziendaModel azdMdl = new AziendaModel();
		CategoriaModel catMdl = new CategoriaModel();
		String azione = request.getParameter("azione");
		String categoria = request.getParameter("categoria");
		String marca = request.getParameter("marca");
		RequestDispatcher dis = null;
		Gson g = new Gson();
		if(azione == null) azione = "";
		switch(azione)
		{
		case "categoria":
			
			if(categoria == null)
			{
				try {
					lista = prMdl.recuperaProdotti(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
			try {
				lista = prMdl.recuperaProdottiByCategoria(categoria);
				listaAziende = azdMdl.recuperaAziende();
				listaCategorie = catMdl.recuperaCategorie();
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
			
			Json = g.toJson(lista);
			
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        

	        response.getWriter().write(Json);
			break;
		case "marca":
			
			if(marca == null)
			{
				try {
					lista = prMdl.recuperaProdotti(1);
					listaAziende = azdMdl.recuperaAziende();
					listaCategorie = catMdl.recuperaCategorie();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
			try {
				lista = prMdl.recuperaProdottiByMarca(marca);
				
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
			
			
			Json = g.toJson(lista);
			
			
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");


	        response.getWriter().write(Json);
			break;
		default:
			
			String keyword = request.getParameter("keyword");

			if(keyword == null)
			{
				try {
					lista = prMdl.recuperaProdottiV2(page,categoria,marca);
					listaAziende = azdMdl.recuperaAziende();
					listaCategorie = catMdl.recuperaCategorie();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
			try {
				lista = prMdl.recuperaProdottiByKeyWord(keyword);
				listaAziende = azdMdl.recuperaAziende();
				listaCategorie = catMdl.recuperaCategorie();
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
			dis = this.getServletContext().getRequestDispatcher("/Catalogo.jsp");
			request.setAttribute("lista", lista);
			request.setAttribute("listaAziende", listaAziende);
			request.setAttribute("listaCategorie", listaCategorie);
			dis.forward(request, response);
			break;
		}
		/*
		dis = this.getServletContext().getRequestDispatcher("/Catalogo.jsp");
		request.setAttribute("lista", lista);
		request.setAttribute("listaAziende", listaAziende);
		dis.forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
