package it.tesi.java.accessi.export;


import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


 public class Create_CSV_Anagrafica {
 public static void main(String[] args) {
 
   try {
    PrintWriter pw= new PrintWriter(new File("export anagrafica.csv"));
    StringBuilder sb=new StringBuilder();
 
 
    Connection connection=null;
    DB_Connection obj_DB_Connection=new DB_Connection();
    connection=obj_DB_Connection.getConnection();
    ResultSet rs=null;
 
    String query="SELECT id, Nome, Cognome, Codice,Ruolo,Cellulare,Luogodinascita,Datadinascita,Genere,Codicefiscale,Indirizzo,Citta,Provincia ,Cap  ,Stato ,Email  ,Note ,Istruzione FROM utentianagrafica";
    PreparedStatement ps=connection.prepareStatement(query);
    rs=ps.executeQuery();
    sb.append("ID      ");
    sb.append(","); 
    sb.append("Nome    ");
    sb.append(","); 
    sb.append("Cognome ");
    sb.append(","); 
    sb.append("Codice    ");
    sb.append(","); 
    sb.append("Ruolo    ");
    sb.append(","); 
    sb.append("Cellulare    ");
    sb.append(","); 
    sb.append("Luogodinascita    ");
    sb.append(","); 
    sb.append("Datadinascita    ");
    sb.append(","); 
    sb.append("Genere    ");
    sb.append(","); 
    sb.append("Codicefiscale    ");
    sb.append(","); 
    sb.append("Indirizzo    ");
    sb.append(","); 
    sb.append("Citta    ");
    sb.append(","); 
    sb.append("Provincia    ");
    sb.append(","); 
    sb.append("Cap    ");
    sb.append(","); 
    sb.append("Stato    ");
    sb.append(","); 
    sb.append("Email    ");
    sb.append(","); 
    sb.append("Note    ");
    sb.append(","); 
    sb.append("Istruzione    ");
    sb.append(","); 
  
    sb.append("\r\n");
 
    while(rs.next()){
    	
    	
    	
     sb.append(rs.getString("id"));
     sb.append(","); 
     sb.append(rs.getString("Nome"));
     sb.append(",");
     sb.append(rs.getString("Cognome"));
     sb.append(",");
     sb.append(rs.getString("Codice"));
     sb.append(",");
     sb.append(rs.getString("Ruolo"));
     sb.append(",");
     sb.append(rs.getString("Cellulare"));
     sb.append(",");
     sb.append(rs.getString("Luogodinascita"));
     sb.append(",");
     sb.append(rs.getString("Datadinascita"));
     sb.append(",");
     sb.append(rs.getString("Genere"));
     sb.append(",");
     sb.append(rs.getString("Codicefiscale"));
     sb.append(",");
     sb.append(rs.getString("Indirizzo"));
     sb.append(",");
     sb.append(rs.getString("Citta"));
     sb.append(",");
     sb.append(rs.getString("Provincia"));
     sb.append(",");
     sb.append(rs.getString("Cap"));
     sb.append(",");
     sb.append(rs.getString("Stato"));
     sb.append(",");
     sb.append(rs.getString("Email"));
     sb.append(",");
     sb.append(rs.getString("Note"));
     sb.append(",");
     sb.append(rs.getString("Istruzione"));
  
    
     sb.append("\r\n");
    }
 
    pw.write(sb.toString());
    pw.close();
    System.out.println("finished");
 
   } catch (Exception e) {

   } 
	System.out.println("okok");

 }
 
}
 
 
 