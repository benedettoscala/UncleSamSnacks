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

import bean.UtenteBean;

public class UtenteModel {
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
	
	public Boolean modificaUtente(UtenteBean usr, String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		String selectSQL = "UPDATE `dbuss`.`utente` SET `username` = ?, `email` = ?,"
				+ " `password` = ?, `nome` = ?, `cognome` = ?,"
				+ " `dataNascita` = ? WHERE (`username` = ?);";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, usr.getUsername());
			preparedStatement.setString(2, usr.getEmail());
			preparedStatement.setString(3, usr.getPassword());
			preparedStatement.setString(4, usr.getNome());
			preparedStatement.setString(5, usr.getCognome());
			preparedStatement.setDate(6, usr.getDataNascita());
			preparedStatement.setString(7, username);

			result = preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		return result == 1;
	}
	
	public Boolean creaUtente(UtenteBean usr) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		String selectSQL = "INSERT INTO `dbuss`.`utente` (`username`, `email`, `password`, `nome`, `cognome`, `dataNascita`, `autorizzazione`) VALUES (?, ?, ?, ?, ?, ?, ?);";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, usr.getUsername());
			preparedStatement.setString(2, usr.getEmail());
			preparedStatement.setString(3, usr.getPassword());
			preparedStatement.setString(4, usr.getNome());
			preparedStatement.setString(5, usr.getCognome());
			preparedStatement.setDate(6, usr.getDataNascita());
			preparedStatement.setString(7, usr.getAutorizzazione());

			result = preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		return result == 1;
	}

	
	public UtenteBean retrieveUtente(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UtenteBean user = null;
		
		String selectSQL = "SELECT * FROM dbuss.utente where username= ?;";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				user = new UtenteBean();
				user.setPassword(rs.getString("Password")); 
				user.setNome(rs.getString("Nome"));
				user.setCognome(rs.getString("Cognome"));
				user.setAutorizzazione(rs.getString("Autorizzazione"));
				user.setDataNascita(rs.getDate("dataNascita"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
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
		
		return user;
	}
	
	public Boolean trovaEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Boolean result = false;
		String selectSQL = "SELECT * FROM dbuss.utente where email = ?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			result = rs.next();
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		return result;
	}
	
	public Boolean trovaUsername(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Boolean result = false;
		String selectSQL = "SELECT * FROM dbuss.utente where username = ?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			result = rs.next();
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		return result;
	}
	
	
	
	public List<UtenteBean> retrieveUtenti() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UtenteBean user = null;
		String selectSQL = "SELECT * FROM dbuss.utente";
		List<UtenteBean> utenti = new ArrayList<>();
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				user = new UtenteBean();
				user.setPassword(rs.getString("Password")); 
				user.setNome(rs.getString("Nome"));
				user.setCognome(rs.getString("Cognome"));
				user.setAutorizzazione(rs.getString("Autorizzazione"));
				user.setDataNascita(rs.getDate("dataNascita"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				utenti.add(user);
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
		
		return utenti;
	}
	
	public List<String> retrieveUtentiSuggestion(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UtenteBean user = null;
		String buff = username + "%";
		String selectSQL = "SELECT * FROM dbuss.utente where username like ?";
		List<String> utenti = new ArrayList<>();
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, buff);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				utenti.add(rs.getString("username"));
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
		
		return utenti;
	}
}
