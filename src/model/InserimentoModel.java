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

import bean.InserimentoBean;

public class InserimentoModel {
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
	
	public void inserisciInserimento(InserimentoBean inserimento) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "INSERT INTO `dbuss`.`inserimento` (`ordine`, `prodotto`, `prezzo`, `iva`, `quantita`, nomeProdotto,urlImmagine) VALUES (?, ?, ?, ?, ?, ?,?);";
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, inserimento.getOrdine());
			preparedStatement.setInt(2, inserimento.getProdotto());
			preparedStatement.setDouble(3, inserimento.getPrezzo());
			preparedStatement.setInt(4, inserimento.getIva());
			preparedStatement.setInt(5, inserimento.getQuantita());
			preparedStatement.setString(6, inserimento.getNomeProdotto());
			preparedStatement.setString(7, inserimento.getUrlImmagine());
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
	
	public List<InserimentoBean> restiuisciInserimenti(Integer idOrdine) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT ordine, prodotto, prezzo, iva, quantita, nomeProdotto, urlImmagine,utente FROM dbuss.inserimento join dbuss.ordine where idOrdine=ordine and ordine=?;";
		List<InserimentoBean> listaInserimenti = new ArrayList<>();
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idOrdine);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			InserimentoBean inserimento = null;
			while(rs.next())
			{
				inserimento = new InserimentoBean();
				inserimento.setIva(rs.getInt("iva"));
				inserimento.setNomeProdotto(rs.getString("nomeProdotto"));
				inserimento.setOrdine(rs.getInt("ordine"));
				inserimento.setPrezzo(rs.getDouble("prezzo"));
				inserimento.setProdotto(rs.getInt("prodotto"));
				inserimento.setQuantita(rs.getInt("quantita"));
				inserimento.setUsername(rs.getString("utente"));
				inserimento.setUrlImmagine(rs.getString("urlImmagine"));
				listaInserimenti.add(inserimento);	
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
		return listaInserimenti;
	}
	
}
