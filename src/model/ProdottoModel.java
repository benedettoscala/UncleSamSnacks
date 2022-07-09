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


import bean.ProdottoBean;

public class ProdottoModel {
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
		
		public ProdottoBean recuperaProdotto(Integer id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ProdottoBean prodotto = null;
			
			
			String selectSQL = "SELECT * FROM dbuss.prodotto where idprodotto = ?";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, id.toString());

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					prodotto = new ProdottoBean();
					prodotto.setNome(rs.getString("nome"));
					prodotto.setAzienda(rs.getString("azienda"));
					prodotto.setCategoria(rs.getString("categoria"));
					prodotto.setDescrizione(rs.getString("descrizione"));
					prodotto.setIdProdotto(rs.getInt("idprodotto"));
					prodotto.setPrezzo(rs.getDouble("prezzo"));
					prodotto.setQuantita(rs.getInt("quantita"));
					prodotto.setUrlImmagine(rs.getString("urlImmagine"));
					prodotto.setSconto(rs.getInt("sconto"));
					prodotto.setIVA(rs.getInt("IVA"));
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
			
			return prodotto;
		}
		public List<ProdottoBean> recuperaProdottiByMarca(String marca) throws SQLException
		{
			Connection conn= null;
			PreparedStatement preparedStatement = null;
			List<ProdottoBean> lista = new ArrayList<>();
			String selectSQL = "SELECT * FROM dbuss.prodotto where azienda=? limit 0, 12;";
			try {
				conn = ds.getConnection();
				preparedStatement = conn.prepareStatement(selectSQL);
				ProdottoBean prodotto = null;
				preparedStatement.setString(1, marca);
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					prodotto = new ProdottoBean();
					prodotto.setNome(rs.getString("nome"));
					prodotto.setAzienda(rs.getString("azienda"));
					prodotto.setCategoria(rs.getString("categoria"));
					prodotto.setDescrizione(rs.getString("descrizione"));
					prodotto.setIdProdotto(rs.getInt("idprodotto"));
					prodotto.setPrezzo(rs.getDouble("prezzo"));
					prodotto.setQuantita(rs.getInt("quantita"));
					prodotto.setUrlImmagine(rs.getString("urlImmagine"));
					prodotto.setSconto(rs.getInt("sconto"));
					prodotto.setIVA(rs.getInt("IVA"));
					lista.add(prodotto);
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
		
		public List<ProdottoBean> recuperaProdottiByCategoria(String categoria) throws SQLException
		{
			Connection conn= null;
			PreparedStatement preparedStatement = null;
			List<ProdottoBean> lista = new ArrayList<>();
			String selectSQL = "SELECT * FROM dbuss.prodotto where categoria=? limit 0, 12;";
			try {
				conn = ds.getConnection();
				preparedStatement = conn.prepareStatement(selectSQL);
				ProdottoBean prodotto = null;
				preparedStatement.setString(1, categoria);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					prodotto = new ProdottoBean();
					prodotto.setNome(rs.getString("nome"));
					prodotto.setAzienda(rs.getString("azienda"));
					prodotto.setCategoria(rs.getString("categoria"));
					prodotto.setDescrizione(rs.getString("descrizione"));
					prodotto.setIdProdotto(rs.getInt("idprodotto"));
					prodotto.setPrezzo(rs.getDouble("prezzo"));
					prodotto.setQuantita(rs.getInt("quantita"));
					prodotto.setUrlImmagine(rs.getString("urlImmagine"));
					prodotto.setSconto(rs.getInt("sconto"));
					prodotto.setIVA(rs.getInt("IVA"));
					lista.add(prodotto);
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
		
		public List<ProdottoBean> recuperaProdottiByKeyWord(String keyword) throws SQLException
		{
			Connection conn= null;
			PreparedStatement preparedStatement = null;
			List<ProdottoBean> lista = new ArrayList<>();
			String buff = keyword + "%";
			String selectSQL = "SELECT * FROM dbuss.prodotto where nome like ? limit 0, 12;";
			try {
				conn = ds.getConnection();
				preparedStatement = conn.prepareStatement(selectSQL);
				ProdottoBean prodotto = null;
				preparedStatement.setString(1, buff);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					prodotto = new ProdottoBean();
					prodotto.setNome(rs.getString("nome"));
					prodotto.setAzienda(rs.getString("azienda"));
					prodotto.setCategoria(rs.getString("categoria"));
					prodotto.setDescrizione(rs.getString("descrizione"));
					prodotto.setIdProdotto(rs.getInt("idprodotto"));
					prodotto.setPrezzo(rs.getDouble("prezzo"));
					prodotto.setQuantita(rs.getInt("quantita"));
					prodotto.setUrlImmagine(rs.getString("urlImmagine"));
					prodotto.setSconto(rs.getInt("sconto"));
					prodotto.setIVA(rs.getInt("IVA"));
					lista.add(prodotto);
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
		
		
		public List<String> recuperaProdottiSearchBar(String keyword) throws SQLException
		{
			Connection conn= null;
			PreparedStatement preparedStatement = null;
			List<String> lista = new ArrayList<>();
			String buff = keyword + "%";
			String selectSQL = "SELECT nome FROM dbuss.prodotto where nome like ? limit 0, 5;";
			try {
				conn = ds.getConnection();
				preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, buff);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					lista.add(rs.getString("nome"));
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
		public List<ProdottoBean> recuperaProdottiV2(int page, String Categoria, String marca) throws SQLException
		{
			Connection conn= null;
			PreparedStatement preparedStatement = null;
			List<ProdottoBean> lista = new ArrayList<>();
			
			if(Categoria == null) Categoria="%"; else Categoria += "%";
			if(marca == null) marca = "%";	else marca += "%";
			page = page-1;
			String selectSQL = "SELECT * FROM dbuss.prodotto where categoria like ?  and azienda like ? LIMIT ?, 12;";
			try {
				conn = ds.getConnection();
				preparedStatement = conn.prepareStatement(selectSQL);
				ProdottoBean prodotto = null;
				preparedStatement.setString(1, Categoria);
				preparedStatement.setString(2, marca);
				preparedStatement.setInt(3, page*12);
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					prodotto = new ProdottoBean();
					prodotto.setNome(rs.getString("nome"));
					prodotto.setAzienda(rs.getString("azienda"));
					prodotto.setCategoria(rs.getString("categoria"));
					prodotto.setDescrizione(rs.getString("descrizione"));
					prodotto.setIdProdotto(rs.getInt("idprodotto"));
					prodotto.setPrezzo(rs.getDouble("prezzo"));
					prodotto.setQuantita(rs.getInt("quantita"));
					prodotto.setUrlImmagine(rs.getString("urlImmagine"));
					prodotto.setIVA(rs.getInt("IVA"));
					prodotto.setSconto(rs.getInt("sconto"));
					lista.add(prodotto);
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
		
		
		
	public List<ProdottoBean> recuperaProdotti(int page) throws SQLException
	{
		Connection conn= null;
		PreparedStatement preparedStatement = null;
		List<ProdottoBean> lista = new ArrayList<>();
		
		page = page-1;
		String selectSQL = "SELECT * FROM dbuss.prodotto LIMIT ?, 12;";
		try {
			conn = ds.getConnection();
			preparedStatement = conn.prepareStatement(selectSQL);
			ProdottoBean prodotto = null;
			preparedStatement.setInt(1, page*10);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				prodotto = new ProdottoBean();
				prodotto.setNome(rs.getString("nome"));
				prodotto.setAzienda(rs.getString("azienda"));
				prodotto.setCategoria(rs.getString("categoria"));
				prodotto.setDescrizione(rs.getString("descrizione"));
				prodotto.setIdProdotto(rs.getInt("idprodotto"));
				prodotto.setPrezzo(rs.getDouble("prezzo"));
				prodotto.setQuantita(rs.getInt("quantita"));
				prodotto.setUrlImmagine(rs.getString("urlImmagine"));
				prodotto.setSconto(rs.getInt("sconto"));
				prodotto.setIVA(rs.getInt("IVA"));
				lista.add(prodotto);
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
		
		
	
	public Boolean inserisciProdotto(ProdottoBean prodotto) throws SQLException
	{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO `dbuss`.`prodotto` (`azienda`, `categoria`, `nome`, `prezzo`, `descrizione`, `urlImmagine`, `quantita`,`sconto`,`IVA`) VALUES (?, ?, ?, ?, ?, ?, ?,?,?);";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, prodotto.getAzienda());
			preparedStatement.setString(2, prodotto.getCategoria());
			preparedStatement.setString(3, prodotto.getNome());
			preparedStatement.setString(4, String.valueOf(prodotto.getPrezzo()));
			preparedStatement.setString(5, prodotto.getDescrizione());
			preparedStatement.setString(6, prodotto.getUrlImmagine());
			preparedStatement.setString(7, String.valueOf(prodotto.getQuantita()));
			preparedStatement.setString(8, String.valueOf(prodotto.getSconto()));
			preparedStatement.setInt(9, prodotto.getIVA());
			
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
		
		return null;
		
	}
	
	public Boolean rimuoviProdotto(int id) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "DELETE FROM `dbuss`.`prodotto` WHERE (`idprodotto` = ?);";
		String strID = String.valueOf(id);
		int controllo = 0;
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, strID);	
			controllo = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return controllo!=0;
	}
	
	public Boolean modificaProdotto(ProdottoBean prodotto) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "UPDATE `dbuss`.`prodotto` SET `azienda` = ?"
				+ ", `categoria` = ?, `nome` = ?, `prezzo` = ?, `descrizione` = ?,`urlImmagine`= ?, `quantita` = ?,`sconto` = ?, `IVA` = ? WHERE (`idprodotto` = ?);";
		int controllo = 0;
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, prodotto.getAzienda());
			preparedStatement.setString(2, prodotto.getCategoria());
			preparedStatement.setString(3, prodotto.getNome());
			preparedStatement.setString(4, String.valueOf(prodotto.getPrezzo()));
			preparedStatement.setString(5, prodotto.getDescrizione());
			if(prodotto.getUrlImmagine()==null) preparedStatement.setString(6, prodotto.getUrlImmagine());
			preparedStatement.setString(6, prodotto.getUrlImmagine());
			preparedStatement.setString(7, String.valueOf(prodotto.getQuantita()));
			preparedStatement.setString(8, String.valueOf(prodotto.getSconto()));
			preparedStatement.setString(9, String.valueOf(prodotto.getIdProdotto()));
			preparedStatement.setInt(9, prodotto.getIVA());
			preparedStatement.setInt(10, prodotto.getIdProdotto());
			controllo = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return controllo!=0;
	}
}