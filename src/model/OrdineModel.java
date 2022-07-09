package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.OrdineBean;

public class OrdineModel {
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
	
	public Integer inserisciOrdine(OrdineBean ordine) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Integer idOrdine;
		DecimalFormat df = new DecimalFormat("#.##");
		String selectSQL = "INSERT INTO `dbuss`.`ordine` (`data`, `ora`, `utente`, `idIndirizzo`, `idPagamento`, `totale`) VALUES (?, ?, ?, ?, ?,?);";
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setDate(1, ordine.getData());
			preparedStatement.setTime(2, ordine.getOra());
			preparedStatement.setString(3, ordine.getUtente());
			preparedStatement.setInt(4, ordine.getIdIndirizzo());
			preparedStatement.setInt(5, ordine.getIdPagamento());
			preparedStatement.setString(6,df.format(ordine.getTotale()).replace(",", "."));
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			idOrdine = rs.getInt(1);
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}	
		return idOrdine;
	}
	
	public List<OrdineBean> restituisciOrdiniUtente(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<OrdineBean> listaOrdini = new ArrayList<>();
		String selectSQL = "SELECT * FROM dbuss.ordine where utente = ?;";
		ResultSet rs = null;
		OrdineBean ordine = null;
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				ordine = new OrdineBean();
				ordine.setData(rs.getDate("data"));
				ordine.setIdOrdine(rs.getInt("idOrdine"));
				ordine.setIdIndirizzo(rs.getInt("idIndirizzo"));
				ordine.setIdPagamento(rs.getInt("idPagamento"));
				ordine.setOra(rs.getTime("ora"));
				ordine.setUtente(rs.getString("utente"));
				ordine.setTotale(rs.getDouble("totale"));
				listaOrdini.add(ordine);
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
		return listaOrdini;
	}
	
	public List<OrdineBean> restituisciOrdiniBy(Boolean ascendente, String ordina, String utente) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		if(utente == null) utente = "%";
		else utente = utente + "%";
		List<OrdineBean> listaOrdini = new ArrayList<>();
		String SQL = null;
		if(ascendente && ordina.equals("data"))
			SQL = "SELECT * FROM dbuss.ordine where utente like ? order by data asc";
		else if(!ascendente && ordina.equals("data"))
			SQL = "SELECT * FROM dbuss.ordine where utente like ? order by data desc";
		else if(ascendente && ordina.equals("utente"))
			SQL = "SELECT * FROM dbuss.ordine where utente like ? order by utente asc";
		else if(!ascendente && ordina.equals("utente"))
			SQL = "SELECT * FROM dbuss.ordine where utente like ? order by utente desc";
		else if(ascendente && ordina.equals("totale"))
			SQL = "SELECT * FROM dbuss.ordine where utente like ? order by totale asc";
		else if(!ascendente && ordina.equals("totale"))
			SQL = "SELECT * FROM dbuss.ordine where utente like ? order by totale desc";
		
		ResultSet rs = null;
		OrdineBean ordine = null;
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setString(1, utente);
			rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				ordine = new OrdineBean();
				ordine.setData(rs.getDate("data"));
				ordine.setIdOrdine(rs.getInt("idOrdine"));
				ordine.setIdIndirizzo(rs.getInt("idIndirizzo"));
				ordine.setIdPagamento(rs.getInt("idPagamento"));
				ordine.setOra(rs.getTime("ora"));
				ordine.setUtente(rs.getString("utente"));
				ordine.setTotale(rs.getDouble("totale"));
				listaOrdini.add(ordine);
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
		return listaOrdini;
	}
	
	
	public List<OrdineBean> restituisciOrdini() throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<OrdineBean> listaOrdini = new ArrayList<>();
		String selectSQL = "SELECT * FROM dbuss.ordine";
		ResultSet rs = null;
		OrdineBean ordine = null;
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				ordine = new OrdineBean();
				ordine.setData(rs.getDate("data"));
				ordine.setIdOrdine(rs.getInt("idOrdine"));
				ordine.setIdIndirizzo(rs.getInt("idIndirizzo"));
				ordine.setIdPagamento(rs.getInt("idPagamento"));
				ordine.setOra(rs.getTime("ora"));
				ordine.setUtente(rs.getString("utente"));
				ordine.setTotale(rs.getDouble("totale"));
				listaOrdini.add(ordine);
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
		return listaOrdini;
	}
	
	public OrdineBean restituisciOrdine(Integer id) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

	
		String selectSQL = "SELECT * FROM dbuss.ordine where idOrdine = ?;";
		ResultSet rs = null;
		OrdineBean ordine = null;
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
			ordine = new OrdineBean();
			ordine.setData(rs.getDate("data"));
			ordine.setIdOrdine(rs.getInt("idOrdine"));
			ordine.setIdIndirizzo(rs.getInt("idIndirizzo"));
			ordine.setIdPagamento(rs.getInt("idPagamento"));
			ordine.setOra(rs.getTime("ora"));
			ordine.setUtente(rs.getString("utente"));
			ordine.setTotale(rs.getDouble("totale"));
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
		return ordine;
	}
}
