
package it.tesi.java.accessi.export;


import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


 public class Create_CSV_Bustepaga {
 public static void main(String[] args) {
 
   try {
    PrintWriter pw= new PrintWriter(new File("export bustepaga.csv"));
    StringBuilder sb=new StringBuilder();
 
 
    Connection connection=null;
    DB_Connection obj_DB_Connection=new DB_Connection();
    connection=obj_DB_Connection.getConnection();
    ResultSet rs=null;
 
    String query="SELECT id, Nome, Cognome, Codice,Retribuzione,Oremensili,Sistemaorario,Sistemamensilizzato,Straordinari,Assenze,Mensilita,Anno,Iban ,Datapagamento  ,Stato ,Permessi  ,Note ,Acconto FROM utentibustepaga";
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
    sb.append("Retribuzione    ");
    sb.append(","); 
    sb.append("Oremensili    ");
    sb.append(","); 
    sb.append("Sistemaorario    ");
    sb.append(","); 
    sb.append("Sistemamensilizzato    ");
    sb.append(","); 
    sb.append("Straordinari    ");
    sb.append(","); 
    sb.append("Assenze    ");
    sb.append(","); 
    sb.append("Mensilita    ");
    sb.append(","); 
    sb.append("Anno    ");
    sb.append(","); 
    sb.append("Iban    ");
    sb.append(","); 
    sb.append("Datapagamento    ");
    sb.append(","); 
    sb.append("Stato    ");
    sb.append(","); 
    sb.append("Permessi    ");
    sb.append(","); 
    sb.append("Note    ");
    sb.append(","); 
    sb.append("Acconto    ");
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
     sb.append(rs.getString("Retribuzione"));
     sb.append(",");
     sb.append(rs.getString("Oremensili"));
     sb.append(",");
     sb.append(rs.getString("Sistemaorario"));
     sb.append(",");
     sb.append(rs.getString("Sistemamensilizzato"));
     sb.append(",");
     sb.append(rs.getString("Straordinari"));
     sb.append(",");
     sb.append(rs.getString("Assenze"));
     sb.append(",");
     sb.append(rs.getString("Mensilita"));
     sb.append(",");
     sb.append(rs.getString("Anno"));
     sb.append(",");
     sb.append(rs.getString("Iban"));
     sb.append(",");
     sb.append(rs.getString("Datapagamento"));
     sb.append(",");
     sb.append(rs.getString("Stato"));
     sb.append(",");
     sb.append(rs.getString("Permessi"));
     sb.append(",");
     sb.append(rs.getString("Note"));
     sb.append(",");
     sb.append(rs.getString("Acconto"));
  
    
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
 
 
 