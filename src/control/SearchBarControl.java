package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.ProdottoModel;

/**
 * Servlet implementation class SearchBarControl
 */
@WebServlet("/SearchBarControl")
public class SearchBarControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBarControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoModel prdMdl = new ProdottoModel();
		Gson g = new Gson();
		String keyWord = request.getParameter("keyword");
		String Json = null;
		try {
			Json = g.toJson(prdMdl.recuperaProdottiSearchBar(keyWord));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        response.getWriter().write(Json);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
