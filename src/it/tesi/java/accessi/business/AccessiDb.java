package it.tesi.java.accessi.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import it.tesi.java.accessi.model.Contatto;


public class AccessiDb {
	private Connection con;
	private static AccessiDb rb;
	
	public static AccessiDb getInstance() {
		if(rb == null) {
			rb = new AccessiDb();
		}
		
		return rb;
	}

	private Connection getConnection() throws SQLException {
		if(con == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName("100.116.192.84");
			dataSource.setPortNumber(3306);
			dataSource.setUser("ammi");
			dataSource.setPassword("ammi");
			dataSource.setDatabaseName("controllo_accessi");
			
			con = dataSource.getConnection();
		}
		
		return con;
	}
	
	public int aggiungiContatto(Contatto c) throws SQLException {
		
		String sql = "INSERT INTO utenti(Utente, Evento, Data, Orario) VALUES(?, ?, ?,?)";
		
		PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, c.getUtente());
		ps.setString(2, c.getEvento());
		ps.setString(3, c.getData());
		ps.setString(4, c.getOrario());
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		
		return rs.getInt(1);
		
		
		
	}

	
	public static void eliminaContatto(Contatto a) {
		Connection cn;
		Statement st;
		String sql;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} 
		try {
			
			cn = DriverManager.getConnection("jdbc:mysql://100.116.192.84:3306/test?user=ammi&password=ammi");

			int id = a.getId();

			sql = "delete from contatto where id=" + id;
			System.out.println(sql); 
			st = cn.createStatement(); 
			st.execute(sql); 
			cn.close();  
			System.out.println("cancellato");
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		}

	}
	
	
	
	
	
	
	
	public List<Contatto> ricercautenti() throws SQLException {
		String sql = "SELECT id, Utente, Evento, Data,Orario  FROM utenti";
		
		PreparedStatement ps = getConnection().prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		List<Contatto> utenti = new ArrayList<Contatto>();
		while(rs.next()) {
			Contatto c = new Contatto();
			c.setId(rs.getInt(1));
			c.setUtente(rs.getString(2));
			c.setEvento(rs.getString(3));
			c.setData(rs.getString(4));
			c.setOrario(rs.getString(5));
			utenti.add(c);
		}
		
		return utenti;
	}
}
