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

import it.tesi.java.accessi.model.ContattoBustepaga;


public class AccessiDbBustepaga {
	private Connection con;
	private static AccessiDbBustepaga rb;
	
	public static AccessiDbBustepaga getInstance() {
		if(rb == null) {
			rb = new AccessiDbBustepaga();
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
	
	public int aggiungiContattoBustepaga(ContattoBustepaga c) throws SQLException {
		
		String sql = "INSERT INTO utentibustepaga(Nome, Cognome, Codice, Retribuzione,Oremensili,Sistemaorario,Sistemamensilizzato,Straordinari,Assenze,Mensilita,Anno,Iban,Datapagamento,Stato,Permessi,Note,Acconto) VALUES(?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getCognome());
		ps.setString(3, c.getCodice());
		ps.setString(4, c.getRetribuzione());
		ps.setString(5, c.getOremensili());
		ps.setString(6, c.getSistemaorario());
		ps.setString(7, c.getSistemamensilizzato());
		ps.setString(8, c.getStraordinari());
		ps.setString(9, c.getAssenze());
		ps.setString(10, c.getMensilita());
		ps.setString(11, c.getAnno());
		ps.setString(12, c.getIban());
		ps.setString(13, c.getDatapagamento());
		ps.setString(14, c.getStato());
		ps.setString(15, c.getPermessi());
		ps.setString(16, c.getNote());
		ps.setString(17, c.getAcconto());
		
		
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		
		return rs.getInt(1);
		
		
		
	}

	
	public static void eliminaContattoBustepaga(ContattoBustepaga a) {
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

			sql = "delete from ContattoBustepaga where id=" + id;
			System.out.println(sql); 
			st = cn.createStatement(); 
			st.execute(sql); 
			cn.close();  
			System.out.println("cancellato");
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		}

	}
	
	
	
	
	
	
	
	public List<ContattoBustepaga> ricercautentibustepaga() throws SQLException {
		String sql = "SELECT id, Nome, Cognome, Codice,Retribuzione,Oremensili,Sistemaorario,Sistemamensilizzato,Straordinari,Assenze,Mensilita,Anno,Iban ,Datapagamento ,Stato,Permessi ,Note,Acconto   FROM utentibustepaga";
		
		PreparedStatement ps = getConnection().prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		List<ContattoBustepaga> utentibustepaga = new ArrayList<ContattoBustepaga>();
		while(rs.next()) {
			ContattoBustepaga c = new ContattoBustepaga();
			c.setId(rs.getInt(1));
			c.setNome(rs.getString(2));
			c.setCognome(rs.getString(3));
			c.setCodice(rs.getString(4));
			c.setRetribuzione(rs.getString(5));
			c.setOremensili(rs.getString(6));
			c.setSistemaorario(rs.getString(7));
			c.setSistemamensilizzato(rs.getString(8));
			c.setStraordinari(rs.getString(9));
			c.setAssenze(rs.getString(10));
			c.setMensilita(rs.getString(11));
			c.setAnno(rs.getString(12));
			c.setIban(rs.getString(13));
			c.setDatapagamento(rs.getString(14));
			c.setStato(rs.getString(15));
			c.setPermessi(rs.getString(16));
			c.setNote(rs.getString(17));
			c.setAcconto(rs.getString(18));
			
			utentibustepaga.add(c);
		}
		
		return utentibustepaga;
	}
}
