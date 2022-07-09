package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UtenteBean;

import model.UtenteModel;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteModel usrMdl = new UtenteModel();
		String user = request.getParameter("username");
		user = user.toLowerCase();
		String realPassword= null;
		UtenteBean usrBn = null;
		try {
			usrBn = usrMdl.retrieveUtente(user);
			
		} catch (SQLException e) {
		}
		
		if(usrBn == null)
		{
			RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/login.jsp");
			request.setAttribute("registrato", false);
			dis.forward(request, response);
		}
		realPassword = usrBn.getPassword();
		String password = request.getParameter("password");
		if(password.equals(realPassword))
		{
			RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/CatalogoControl");
			request.getSession().setAttribute("user", usrBn);
			dis.forward(request, response);
		}
		else
		{
			RequestDispatcher dis = this.getServletContext().getRequestDispatcher("/login.jsp");
			request.setAttribute("registrato", false);
			dis.forward(request, response);
		}
	}

}
