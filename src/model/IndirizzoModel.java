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

import bean.IndirizzoBean;

public class IndirizzoModel {
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
	
	public void inserisciIndirizzo(IndirizzoBean indirizzo) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String selectSQL = "INSERT INTO `dbuss`.`indirizzo` (`numeroCivico`, `Citta`, `Cap`, `username`, `via`) VALUES (?, ?, ?, ?, ?);";
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, indirizzo.getNumerocivico());
			preparedStatement.setString(2, indirizzo.getCitta());
			preparedStatement.setInt(3, indirizzo.getCap());
			preparedStatement.setString(4, indirizzo.getUsername());
			preparedStatement.setString(5, indirizzo.getVia());
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
	
	public List<IndirizzoBean>  recuperaIndirizzi(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<IndirizzoBean> lista = null;
		
		String selectSQL = "SELECT * FROM dbuss.indirizzo where username=?;";
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			lista = new ArrayList<>();
			while(rs.next())
			{
				IndirizzoBean ind = new IndirizzoBean();
				ind.setCap(rs.getInt("cap"));
				ind.setCitta(rs.getString("citta"));
				ind.setIdIndirizzo(rs.getInt("idIndirizzo"));
				ind.setNumerocivico(rs.getInt("numeroCivico"));
				ind.setVia(rs.getString("via"));
				ind.setUsername(rs.getString("username"));
				lista.add(ind);
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
	
	public IndirizzoBean recuperaIndirizzo(Integer idIndirizzo) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		IndirizzoBean ind = null;
		String selectSQL = "SELECT * FROM dbuss.indirizzo where idIndirizzo=?;";
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idIndirizzo);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				ind = new IndirizzoBean();
				ind.setCap(rs.getInt("cap"));
				ind.setCitta(rs.getString("citta"));
				ind.setIdIndirizzo(rs.getInt("idIndirizzo"));
				ind.setNumerocivico(rs.getInt("numeroCivico"));
				ind.setVia(rs.getString("via"));
				ind.setUsername(rs.getString("username"));
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
		
		return ind;
	}
	public void eliminaIndirizzo(Integer idIndirizzo) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "UPDATE `dbuss`.`indirizzo` SET `username` = NULL WHERE (`idIndirizzo` = ?);";
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idIndirizzo);
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
