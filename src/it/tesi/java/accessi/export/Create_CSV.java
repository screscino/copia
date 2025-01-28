package it.tesi.java.accessi.export;


import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


 public class Create_CSV {
 public static void main(String[] args) {
 
   try {
    PrintWriter pw= new PrintWriter(new File("accessi.csv"));
    StringBuilder sb=new StringBuilder();
 
 
    Connection connection=null;
    DB_Connection obj_DB_Connection=new DB_Connection();
    connection=obj_DB_Connection.getConnection();
    ResultSet rs=null;
 
    String query="SELECT id, Utente, Evento, Data,Orario  FROM utenti";
    PreparedStatement ps=connection.prepareStatement(query);
    rs=ps.executeQuery();
    sb.append("ID      ");
    sb.append(","); 
    sb.append("Utente    ");
    sb.append(","); 
    sb.append("Evento ");
    sb.append(","); 
    sb.append("Data    ");
    sb.append(","); 
    sb.append("Orario    ");
    sb.append(","); 
    sb.append("=E3-E2"); 
    sb.append(","); 
    sb.append("\r\n");
 
    while(rs.next()){
    	
    	
    	
     sb.append(rs.getString("id"));
     sb.append(","); 
     sb.append(rs.getString("Utente"));
     sb.append(",");
     sb.append(rs.getString("Evento"));
     sb.append(",");
     sb.append(rs.getString("Data"));
     sb.append(",");
     sb.append(rs.getString("Orario"));
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
 
 
 