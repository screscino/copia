package it.tesi.java.accessi.export;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DB_Connection_Anagrafica {
	
	
	
	/*
	
	public Connection getConnection() throws SQLException{
		
		
		Connection connection=null;

		
		if(connection == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName("37.102.104.107");
			dataSource.setPortNumber(7779);
			dataSource.setUser("ammi");
			dataSource.setPassword("ammi");
			dataSource.setDatabaseName("controllo_accessi");
			
			connection = dataSource.getConnection();
		}
		
		return connection;
	}
}
	
		*/
		
		
	public Connection getConnection() {
		Connection connection=null;
		
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/controllo_accessi","ammi", "ammi");
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		}
		
		return connection;
		
	}
}
	
	
	
	
