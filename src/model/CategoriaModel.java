package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CategoriaBean;

public class CategoriaModel {
	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/dbuss");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	


public List<CategoriaBean> recuperaCategorie() throws SQLException
{
	Connection conn= null;
	PreparedStatement preparedStatement = null;
	List<CategoriaBean> lista = new ArrayList<>();
	
	String selectSQL = "SELECT * FROM dbuss.categoria;";
	try {
		conn = ds.getConnection();
		preparedStatement = conn.prepareStatement(selectSQL);
		CategoriaBean categoria = null;
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next())
		{
			categoria = new CategoriaBean();
			categoria.setDescrizione(rs.getString("descrizione"));
			categoria.setTipo(rs.getString("tipo"));
			
			lista.add(categoria);
		}
	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	
	
	return lista;
}


}
