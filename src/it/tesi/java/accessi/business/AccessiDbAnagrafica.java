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

import it.tesi.java.accessi.model.ContattoAnagrafica;


public class AccessiDbAnagrafica {
	private Connection con;
	private static AccessiDbAnagrafica rb;
	
	public static AccessiDbAnagrafica getInstance() {
		if(rb == null) {
			rb = new AccessiDbAnagrafica();
		}
		
		return rb;
	}

	private Connection getConnection() throws SQLException {
		if(con == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName("100.116.192.84");
			dataSource.setPortNumber(3306);
			dataSource.setUser("root");
			dataSource.setPassword("");
			dataSource.setDatabaseName("controllo_accessi");
			
			con = dataSource.getConnection();
		}
		
		return con;
	}
	
	public int aggiungiContattoAnagrafica(ContattoAnagrafica c) throws SQLException {
		
		String sql = "INSERT INTO utentianagrafica(Nome, Cognome, Codice, Ruolo,Cellulare,Luogodinascita,Datadinascita,Genere,Codicefiscale,Indirizzo,Citta,Provincia,Cap,Stato,Email,Note,Istruzione) VALUES(?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getCognome());
		ps.setString(3, c.getCodice());
		ps.setString(4, c.getRuolo());
		ps.setString(5, c.getCellulare());
		ps.setString(6, c.getLuogodinascita());
		ps.setString(7, c.getDatadinascita());
		ps.setString(8, c.getGenere());
		ps.setString(9, c.getCodicefiscale());
		ps.setString(10, c.getIndirizzo());
		ps.setString(11, c.getCitta());
		ps.setString(12, c.getProvincia());
		ps.setString(13, c.getCap());
		ps.setString(14, c.getStato());
		ps.setString(15, c.getEmail());
		ps.setString(16, c.getNote());
		ps.setString(17, c.getIstruzione());
		
		
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		
		return rs.getInt(1);
		
		
		
	}

	
	public static void eliminaContattoAnagrafica(ContattoAnagrafica a) {
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
			
			cn = DriverManager.getConnection("jdbc:mysql://100.116.192.84:3306/test?user=root&password=");

			int id = a.getId();

			sql = "delete from ContattoAnagrafica where id=" + id;
			System.out.println(sql); 
			st = cn.createStatement(); 
			st.execute(sql); 
			cn.close();  
			System.out.println("cancellato");
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		}

	}
	
	
	
	
	
	
	
	public List<ContattoAnagrafica> ricercautentianagrafica() throws SQLException {
		String sql = "SELECT id, Nome, Cognome, Codice,Ruolo,Cellulare,Luogodinascita,Datadinascita,Genere,Codicefiscale,Indirizzo,Citta,Provincia ,Cap ,Stato,Email ,Note,Istruzione   FROM utentianagrafica";
		
		PreparedStatement ps = getConnection().prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		List<ContattoAnagrafica> utentianagrafica = new ArrayList<ContattoAnagrafica>();
		while(rs.next()) {
			ContattoAnagrafica c = new ContattoAnagrafica();
			c.setId(rs.getInt(1));
			c.setNome(rs.getString(2));
			c.setCognome(rs.getString(3));
			c.setCodice(rs.getString(4));
			c.setRuolo(rs.getString(5));
			c.setCellulare(rs.getString(6));
			c.setLuogodinascita(rs.getString(7));
			c.setDatadinascita(rs.getString(8));
			c.setGenere(rs.getString(9));
			c.setCodicefiscale(rs.getString(10));
			c.setIndirizzo(rs.getString(11));
			c.setCitta(rs.getString(12));
			c.setProvincia(rs.getString(13));
			c.setCap(rs.getString(14));
			c.setStato(rs.getString(15));
			c.setEmail(rs.getString(16));
			c.setNote(rs.getString(17));
			c.setIstruzione(rs.getString(18));
			
			utentianagrafica.add(c);
		}
		
		return utentianagrafica;
	}
}
