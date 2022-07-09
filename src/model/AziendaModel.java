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

import bean.AziendaBean;

public class AziendaModel {
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
		
		public AziendaBean recuperaDescrizione(String nome) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			AziendaBean azienda = new AziendaBean();
			
			
			String selectSQL = "SELECT * FROM dbuss.azienda where nome = ?";
			
			try {
				
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, nome);
	
				ResultSet rs = preparedStatement.executeQuery();
	
				while (rs.next()) {
					azienda.setNome(rs.getString("nome"));
					azienda.setDescrizione(rs.getString("descrizione"));
				}
	
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			
			return azienda;
		}
	
	
	public List<AziendaBean> recuperaAziende() throws SQLException
	{
		Connection conn= null;
		PreparedStatement preparedStatement = null;
		List<AziendaBean> lista = new ArrayList<>();
		try {
			
			
			String selectSQL = "SELECT * FROM dbuss.azienda;";
			
			conn = ds.getConnection();
			preparedStatement = conn.prepareStatement(selectSQL);
			AziendaBean azienda = null;
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				azienda = new AziendaBean();
				
				azienda.setNome(rs.getString("nome"));
				azienda.setDescrizione(rs.getString("descrizione"));
				
				lista.add(azienda);
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
