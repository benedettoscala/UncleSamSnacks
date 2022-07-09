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

import bean.PagamentoBean;

public class PagamentoModel {
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
	
	public void inserisciPagamento(PagamentoBean pagamento) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "INSERT INTO `dbuss`.`pagamento` (`dataScadenza`, `cvv`, `nome`, `cognome`, `numeroCarta`, `username`) VALUES (?, ?, ?, ?, ?, ?);";
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setDate(1, pagamento.getDataScadenza());
			preparedStatement.setInt(2, pagamento.getCvv());
			preparedStatement.setString(3, pagamento.getNome());
			preparedStatement.setString(4, pagamento.getCognome());
			preparedStatement.setString(5, pagamento.getNumeroCarta());
			preparedStatement.setString(6, pagamento.getUsername());
			preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}	
	}
	
	public List<PagamentoBean>  recuperaPagamenti(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<PagamentoBean> lista = null;
		
		String selectSQL = "SELECT * FROM dbuss.pagamento where username=?;";
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			lista = new ArrayList<>();
			while(rs.next())
			{
				PagamentoBean pag = new PagamentoBean();
				pag.setUsername(rs.getString("username"));
				pag.setNome(rs.getString("nome"));
				pag.setCognome(rs.getString("cognome"));
				pag.setNumeroCarta(rs.getString("numeroCarta"));
				pag.setDataScadenza(rs.getDate("dataScadenza"));
				pag.setIdPagamento(rs.getInt("idPagamento"));
				pag.setCvv(rs.getInt("cvv"));
				lista.add(pag);
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
		
		
		return lista;
	}
	
	public PagamentoBean  recuperaPagamento(Integer idPagamento) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PagamentoBean pag = null;
		String selectSQL = "SELECT * FROM dbuss.pagamento where idPagamento=?;";
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idPagamento);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				pag = new PagamentoBean();
				pag.setUsername(rs.getString("username"));
				pag.setNome(rs.getString("nome"));
				pag.setCognome(rs.getString("cognome"));
				pag.setNumeroCarta(rs.getString("numeroCarta"));
				pag.setDataScadenza(rs.getDate("dataScadenza"));
				pag.setIdPagamento(rs.getInt("idPagamento"));
				pag.setCvv(rs.getInt("cvv"));
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
		
		
		return pag;
	}
	
	
	public void eliminaPagamento(Integer idPagamento) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "UPDATE `dbuss`.`pagamento` SET `username` = NULL WHERE (`idPagamento` = ?);";
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idPagamento);
			preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}	
	}
	
}
