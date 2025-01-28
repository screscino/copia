package it.tesi.java.accessi.view;
import net.proteanit.sql.DbUtils;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.Connection;

import it.tesi.java.accessi.business.AccessiDbAnagrafica;
import it.tesi.java.accessi.export.Create_CSV_Anagrafica;
import it.tesi.java.accessi.model.ContattoAnagrafica;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTextPane;
import java.awt.Window.Type;
import java.awt.SystemColor;



public class Anagrafica{

	public JFrame frmProgettoPapaleo;
	private JTable listautenti;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtCodice;
	private JTextField txtRuolo;
	private JLabel lbldata_ora;
	private JTextField txtId;
	private JTextField textFieldSearch;
	private JComboBox comboBoxSelection;
	private JTextField txtCellulare;
	private JTextField txtLuogodinascita;
	private JTextField txtDatadinascita;
	private JTextField txtCodicefiscale;
	private JTextField txtIndirizzo;
	private JTextField txtCitta;
	private JTextField txtStato;
	private JTextField txtCap;
	private JTextField txtProvincia;
	private JTextField txtIstruzione;
	private JTextField txtNote;
	private JTextField txtEmail;
	private JTextField txtGenere;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Anagrafica window = new Anagrafica();
					window.frmProgettoPapaleo.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
			
		});
	}
	public void clock()
	{
		Thread clock=new Thread() 
		{
			public void run()
			{
				try {
					for(;;) {
						
					LocalDate today = LocalDate.now( ZoneId.of( "Europe/Rome" ) );
					int month = today.getMonthValue();  // Returns 1-12 as values.
						
					Calendar cal=new GregorianCalendar();
					int day=cal.get(Calendar.DAY_OF_MONTH);
					int year=cal.get(Calendar.YEAR);
					int second=cal.get(Calendar.SECOND);
					int minute=cal.get(Calendar.MINUTE);
					int hour=cal.get(Calendar.HOUR_OF_DAY);
					lbldata_ora.setText("  Data "+day+"/"+month+ "/"+year+" - Ora " + hour+":"+minute+":"+second );
					sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		clock.start();
		
	}
		public  Anagrafica() {
		initialize();
		clock();
		}

	private void initialize() {
		frmProgettoPapaleo = new JFrame();
		frmProgettoPapaleo.getContentPane().setBackground(SystemColor.textHighlight);
		frmProgettoPapaleo.setType(Type.UTILITY);
		frmProgettoPapaleo.setResizable(false);
		frmProgettoPapaleo.setTitle("Software Per Il Controllo Del Personale");
		frmProgettoPapaleo.setBounds(100, 100, 1394, 698);
		frmProgettoPapaleo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProgettoPapaleo.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 23, 1356, 623);
		frmProgettoPapaleo.getContentPane().add(tabbedPane);
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("ANAGRAFICA", null, panel, null);
		panel.setLayout(null);
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
				
		});
		scrollPane.setBounds(304, 74, 1040, 425);
		panel.add(scrollPane);
			
		listautenti = new JTable();
		listautenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listautenti.addMouseListener(new MouseAdapter() {
		
			
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {

					  
				        int selectedRow = listautenti.getSelectedRow();
				        String selectIdvalue = listautenti.getModel().getValueAt(selectedRow, 0).toString();
				        Selezione.IdSelezione=selectIdvalue;
				        System.out.println(selectIdvalue);
				        
				        DefaultTableModel model = (DefaultTableModel)listautenti.getModel();
				        
				        txtId.setText(model.getValueAt(selectedRow, 0).toString());
				        System.out.println("ora");
				        
				        
				        txtNome.setText(model.getValueAt(selectedRow, 1).toString());
				        txtCognome.setText(model.getValueAt(selectedRow, 2).toString());
				        txtCodice.setText(model.getValueAt(selectedRow, 3).toString());
				        txtRuolo.setText(model.getValueAt(selectedRow, 4).toString());
				        txtCellulare.setText(model.getValueAt(selectedRow, 5).toString());
				        txtLuogodinascita.setText(model.getValueAt(selectedRow, 6).toString());
				        txtDatadinascita.setText(model.getValueAt(selectedRow, 7).toString());
				        txtGenere.setText(model.getValueAt(selectedRow, 8).toString());
				        txtCodicefiscale.setText(model.getValueAt(selectedRow, 9).toString());
				        txtIndirizzo.setText(model.getValueAt(selectedRow, 10).toString());
				        txtCitta.setText(model.getValueAt(selectedRow, 11).toString());
				        txtProvincia.setText(model.getValueAt(selectedRow, 12).toString());
				        txtCap.setText(model.getValueAt(selectedRow, 13).toString());
				        txtStato.setText(model.getValueAt(selectedRow, 14).toString());
				        txtEmail.setText(model.getValueAt(selectedRow, 15).toString());
				        txtNote.setText(model.getValueAt(selectedRow, 16).toString());
				        txtIstruzione.setText(model.getValueAt(selectedRow, 17).toString());
						
				    }                                  
				    
				
			
		});
		
		listautenti.setAutoCreateRowSorter(true);
		listautenti.setModel(new DefaultTableModel(new Object[][] {},
				new String[] {"ID", "Nome", "Cognome", "Codice","Ruolo","Cellulare","Luogo Di Nascita","Data di Nascita","Genere","Codice Fiscale","Indirizzo","Citta","Provincia","Cap","Stato","Email","Note","Istruzione"}) { 
			boolean[] columnEditables = new boolean[] {
					false, false, false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
					};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		listautenti.getColumnModel().getColumn(0);
		listautenti.getColumnModel().getColumn(1);
		listautenti.getColumnModel().getColumn(2);
		listautenti.getColumnModel().getColumn(3);
		listautenti.getColumnModel().getColumn(4);
		listautenti.getColumnModel().getColumn(5);
		listautenti.getColumnModel().getColumn(6);
		listautenti.getColumnModel().getColumn(7);
		listautenti.getColumnModel().getColumn(8);
		listautenti.getColumnModel().getColumn(9);
		listautenti.getColumnModel().getColumn(10);
		listautenti.getColumnModel().getColumn(11);
		listautenti.getColumnModel().getColumn(12);
		listautenti.getColumnModel().getColumn(13);
		listautenti.getColumnModel().getColumn(14);
		listautenti.getColumnModel().getColumn(15);
		listautenti.getColumnModel().getColumn(16);
		listautenti.getColumnModel().getColumn(17);
		DefaultTableModel dtm = (DefaultTableModel) listautenti.getModel();
		
		List<ContattoAnagrafica> utentianagrafica;
		try {
			utentianagrafica = AccessiDbAnagrafica.getInstance().ricercautentianagrafica();
			
			for (ContattoAnagrafica c : utentianagrafica) {
				Vector rowData = new Vector();
				rowData.add(c.getId());
				rowData.add(c.getNome());
				rowData.add(c.getCognome());
				rowData.add(c.getCodice());
				rowData.add(c.getRuolo());
				rowData.add(c.getCellulare());
				rowData.add(c.getLuogodinascita());
				rowData.add(c.getDatadinascita());
				rowData.add(c.getGenere());
				rowData.add(c.getCodicefiscale());
				rowData.add(c.getIndirizzo());
				rowData.add(c.getCitta());
				rowData.add(c.getProvincia());
				rowData.add(c.getCap());
				rowData.add(c.getStato());
				rowData.add(c.getEmail());
				rowData.add(c.getNote());
				rowData.add(c.getIstruzione());
				
				dtm.addRow(rowData);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		scrollPane.setViewportView(listautenti);
		
		JButton btnEsportaCsv = new JButton("Esporta Database in formato csv");
		btnEsportaCsv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				}
		});
		btnEsportaCsv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEsportaCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create_CSV_Anagrafica.main(null);
				
				 
			}
		});
		btnEsportaCsv.setBounds(675, 30, 249, 23);
		panel.add(btnEsportaCsv);
		
		JButton btnAggiorna = new JButton("Aggiorna");
		btnAggiorna.setBounds(326, 536, 89, 23);
		panel.add(btnAggiorna);
		
		
		
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				
				        int i = listautenti.getSelectedRow();
				         DefaultTableModel model = (DefaultTableModel)listautenti.getModel();
				        if(i >= 0)
				        {
				            model.setValueAt(txtId.getText(), i, 0);
				            model.setValueAt(txtNome.getText(), i, 1);
				            model.setValueAt(txtCognome.getText(), i, 2);
				            model.setValueAt(txtCodice.getText(), i, 3);
				            model.setValueAt(txtRuolo.getText(), i, 4);
				            model.setValueAt(txtCellulare.getText(), i, 5);
				            model.setValueAt(txtLuogodinascita.getText(), i, 6);
				            model.setValueAt(txtDatadinascita.getText(), i, 7);
				            model.setValueAt(txtGenere.getText(), i, 8);
				            model.setValueAt(txtCodicefiscale.getText(), i, 9);
				            model.setValueAt(txtCodicefiscale.getText(), i, 10);
							          
				             try 
							{
								int SelezioneIndiceRiga=listautenti.getSelectedRow();
								model.removeRow(SelezioneIndiceRiga);
								String jdbcUrl = "jdbc:mysql://localhost:3306/controllo_accessi";
								String username = "ammi";
								String password = "ammi";
								String query= "UPDATE `utentianagrafica` SET `Nome`='"+txtNome.getText()+"',`Cognome`='"+txtCognome.getText()+"',`Codice`='"+txtCodice.getText()+"',`Ruolo`='"+txtRuolo.getText()+"',`Cellulare`='"+txtCellulare.getText()+"',`Luogodinascita`='"+txtLuogodinascita.getText()+"',`Datadinascita`='"+txtDatadinascita.getText()+"',`Genere`='"+txtGenere.getText()+"',`Codicefiscale`='"+txtCodicefiscale.getText()+"',`Indirizzo`='"+txtIndirizzo.getText()+"',`Citta`='"+txtCitta.getText()+"',`Provincia`='"+txtProvincia.getText()+"',`Cap`='"+txtCap.getText()+"',`Stato`='"+txtStato.getText()+"',`Email`='"+txtEmail.getText()+"',`Note`='"+txtNote.getText()+"',`Istruzione`='"+txtIstruzione.getText()+"' WHERE `id`= "+txtId.getText(); 
						          System.out.println(query);
						          frmProgettoPapaleo.setVisible(false);
						          Anagrafica.main(null);
						          try (Connection conn = (Connection) DriverManager.getConnection(jdbcUrl, username, password); 
										PreparedStatement stmt = conn.prepareStatement(query)) {
									stmt.executeUpdate(query);
									}catch (SQLException exx) {
										exx.printStackTrace();
										}
								}catch(Exception ex){
								JOptionPane.showMessageDialog(null, "Non hai selezionato alcun elemento");
								} 
				            
				            
				            
				            
				            
				            
				            

				        }else{
				        	JOptionPane.showMessageDialog(null, "Non hai selezionato alcun elemento");
				    }                               
			}
		});
		
		btnModifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				   int selectedRow = listautenti.getSelectedRow();
			        DefaultTableModel model = (DefaultTableModel)listautenti.getModel();
			        txtId.setText(model.getValueAt(selectedRow, 0).toString());
			        txtNome.setText(model.getValueAt(selectedRow, 1).toString());
			        txtCognome.setText(model.getValueAt(selectedRow, 2).toString());
			        txtCodice.setText(model.getValueAt(selectedRow, 3).toString());
			        txtRuolo.setText(model.getValueAt(selectedRow, 4).toString());
			        txtCellulare.setText(model.getValueAt(selectedRow, 5).toString());
			        txtLuogodinascita.setText(model.getValueAt(selectedRow, 6).toString());
			        txtDatadinascita.setText(model.getValueAt(selectedRow, 7).toString());
			        txtGenere.setText(model.getValueAt(selectedRow, 8).toString());
			        txtCodicefiscale.setText(model.getValueAt(selectedRow, 9).toString());
			        txtIndirizzo.setText(model.getValueAt(selectedRow, 10).toString());
			        txtCitta.setText(model.getValueAt(selectedRow, 11).toString());
			        txtProvincia.setText(model.getValueAt(selectedRow, 12).toString());
			        txtCap.setText(model.getValueAt(selectedRow, 13).toString());
			        txtStato.setText(model.getValueAt(selectedRow, 14).toString());
			        txtEmail.setText(model.getValueAt(selectedRow, 15).toString());
			        txtNote.setText(model.getValueAt(selectedRow, 16).toString());
			        txtIstruzione.setText(model.getValueAt(selectedRow, 17).toString());
					
					}
		});
		
		btnModifica.setBounds(425, 536, 89, 23);
		panel.add(btnModifica);
		JButton btnElimina = new JButton("Elimina");
		btnElimina.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=(DefaultTableModel) listautenti.getModel();
				try 
				{
					int SelezioneIndiceRiga=listautenti.getSelectedRow();
					model.removeRow(SelezioneIndiceRiga);
					String jdbcUrl = "jdbc:mysql://localhost:3306/controllo_accessi";
					String username = "ammi";
					String password = "ammi";
					String sql = "DELETE FROM `utentianagrafica` WHERE `utentianagrafica`.`id` ="+Selezione.getIdSelezione();//+SelezioneIndiceRiga+1;
					System.out.println(sql);
					try (Connection conn = (Connection) DriverManager.getConnection(jdbcUrl, username, password); 
							PreparedStatement stmt = conn.prepareStatement(sql)) {
						stmt.executeUpdate(sql);
						}catch (SQLException exx) {
							exx.printStackTrace();
							}
					}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Non hai selezionato alcun elemento");
					} 
			}
		});
		btnElimina.setBounds(425, 508, 89, 23);
		panel.add(btnElimina);
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.setBounds(1255, 510, 89, 23);
		panel.add(btnExit_1);
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProgettoPapaleo=new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmProgettoPapaleo, "Confermi di Voler uscire","Login System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);				
					}
				}
			});
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(28, 109, 56, 16);
		panel.add(lblNome);
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(154, 106, 140, 22);
		panel.add(txtNome);
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(28, 139, 56, 16);
		panel.add(lblCognome);
		txtCognome = new JTextField();
		txtCognome.setColumns(10);
		txtCognome.setBounds(154, 136, 140, 22);
		panel.add(txtCognome);
		JLabel lblCodice = new JLabel("Codice");
		lblCodice.setBounds(28, 169, 56, 16);
		panel.add(lblCodice);
		txtCodice = new JTextField();
		txtCodice.setColumns(10);
		txtCodice.setBounds(154, 166, 140, 22);
		panel.add(txtCodice);
		
		JLabel lblRuolo = new JLabel("Ruolo");
		lblRuolo.setBounds(28, 200, 46, 14);
		panel.add(lblRuolo);
		txtRuolo = new JTextField();
		txtRuolo.setColumns(10);
		txtRuolo.setBounds(154, 196, 140, 22);
		panel.add(txtRuolo);
		
		JLabel lblCellulare = new JLabel("Cellulare");
		lblCellulare.setBounds(28, 229, 69, 14);
		panel.add(lblCellulare);
		
		txtCellulare = new JTextField();
		txtCellulare.setColumns(10);
		txtCellulare.setBounds(154, 225, 140, 22);
		panel.add(txtCellulare);
		
		JLabel lblLuogodinascita = new JLabel("Luogo Di Nascita");
		lblLuogodinascita.setBounds(28, 257, 116, 14);
		panel.add(lblLuogodinascita);
		
		txtLuogodinascita = new JTextField();
		txtLuogodinascita.setColumns(10);
		txtLuogodinascita.setBounds(154, 254, 140, 22);
		panel.add(txtLuogodinascita);
		
		JLabel lblDatadinascita = new JLabel("Data Di Nascita gg/mm/aaaa");
		lblDatadinascita.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDatadinascita.setBounds(10, 286, 146, 14);
		panel.add(lblDatadinascita);
		
		
		txtDatadinascita = new JTextField();
		txtDatadinascita.setColumns(10);
		txtDatadinascita.setBounds(154, 282, 140, 22);
		panel.add(txtDatadinascita);
		
		JLabel lblGenere = new JLabel("Genere");
		
		lblGenere.setBounds(28, 314, 116, 14);
		panel.add(lblGenere);
		txtGenere = new JTextField();
		txtGenere.setColumns(10);
		txtGenere.setBounds(154, 311, 140, 22);
		panel.add(txtGenere);
		
		
		
		
		
		
		
		JButton btnAggiungi = new JButton("AGGIUNGI");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ContattoAnagrafica nuovoContattoAnagrafica = new ContattoAnagrafica();
				nuovoContattoAnagrafica.setNome(txtNome.getText());
				nuovoContattoAnagrafica.setCognome(txtCognome.getText());
				nuovoContattoAnagrafica.setCodice(txtCodice.getText());
				nuovoContattoAnagrafica.setRuolo(txtRuolo.getText());
				nuovoContattoAnagrafica.setCellulare(txtCellulare.getText());
				nuovoContattoAnagrafica.setLuogodinascita(txtLuogodinascita.getText());
				nuovoContattoAnagrafica.setDatadinascita(txtDatadinascita.getText());
				nuovoContattoAnagrafica.setGenere(txtGenere.getText());
				nuovoContattoAnagrafica.setCodicefiscale(txtCodicefiscale.getText());
				nuovoContattoAnagrafica.setIndirizzo(txtIndirizzo.getText());
				nuovoContattoAnagrafica.setCitta(txtCitta.getText());
				nuovoContattoAnagrafica.setProvincia(txtProvincia.getText());
				nuovoContattoAnagrafica.setCap(txtCap.getText());
				nuovoContattoAnagrafica.setStato(txtStato.getText());
				nuovoContattoAnagrafica.setEmail(txtEmail.getText());
				nuovoContattoAnagrafica.setNote(txtNote.getText());
				nuovoContattoAnagrafica.setIstruzione(txtIstruzione.getText());
				
					try {
					int id = AccessiDbAnagrafica.getInstance().aggiungiContattoAnagrafica(nuovoContattoAnagrafica);
					if(id > 0) {
					JOptionPane.showMessageDialog(null, "Utente "+txtNome.getText()+ " INSERITO CORRETTAMENTE");
						txtCognome.setText("");
						txtNome.setText("");
						txtCodice.setText("");
						txtRuolo.setText("");
						txtCellulare.setText("");
						txtLuogodinascita.setText("");
						txtDatadinascita.setText("");
						txtGenere.setText("");
						txtCodicefiscale.setText("");
						txtIndirizzo.setText("");
						txtCitta.setText("");
						txtProvincia.setText("");
						txtCap.setText("");
						txtStato.setText("");
						txtEmail.setText("");
						txtNote.setText("");
						txtIstruzione.setText("");
						
						frmProgettoPapaleo.setVisible(false);
						Anagrafica.main(null);
				        
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "PARAMETRI NON INSERITI, O NEL CAMPO Codice NON HAI INSERITO VALORI NUMERICI");
						}
				refreshTable();
				}
			
			
			
			
			
			
			private void refreshTable() {	
			}
			});
		
		
		
		
		btnAggiungi.setBounds(326, 508, 89, 23);
		panel.add(btnAggiungi);
		lbldata_ora = new JLabel("DATA E ORA");
		lbldata_ora.setBackground(SystemColor.inactiveCaption);
		lbldata_ora.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbldata_ora.setBounds(23, 11, 619, 52);
		panel.add(lbldata_ora);
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(28, 81, 46, 14);
		panel.add(lblId);
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(154, 77, 116, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String selection=(String)comboBoxSelection.getSelectedItem();
					String jdbcUrl = "jdbc:mysql://localhost:3306/controllo_accessi";
					String username = "ammi";
					String password = "ammi";
					String query="select * from utentianagrafica where "+selection+"=? ";
					
					Connection conn = (Connection) DriverManager.getConnection(jdbcUrl, username, password); 
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1,textFieldSearch.getText());
					java.sql.ResultSet rs=pst.executeQuery();
					listautenti.setModel(DbUtils.resultSetToTableModel(rs));
					System.out.println(query);
					
					
				
				} catch(Exception ex) {
						ex.printStackTrace();
						}
				}
			});
		textFieldSearch.setBounds(821, 510, 146, 31);
		panel.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		comboBoxSelection = new JComboBox();
		comboBoxSelection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSelection.setModel(new DefaultComboBoxModel(new String[] {"id", "Nome", "Cognome", "Codice", "Ruolo","Cellulare","Luogodinascita","Datadinascita","Genere","Codicefiscale","Indirizzo","Citta","Provincia","Cap","Stato","Email","Note","Istruzione"}));
		comboBoxSelection.setBounds(675, 510, 107, 31);
		panel.add(comboBoxSelection);
		JLabel lblRicercaContattoAnagraficaPer = new JLabel("RICERCA UTENTE PER:");
		lblRicercaContattoAnagraficaPer.setBounds(524, 510, 146, 31);
		panel.add(lblRicercaContattoAnagraficaPer);
		
		JButton btnAzzera = new JButton("AZZERA");
		btnAzzera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				try 
				{
					String jdbcUrl = "jdbc:mysql://localhost:3306/controllo_accessi";
					String username = "ammi";
					String password = "ammi";
					String sql = "TRUNCATE TABLE utentianagrafica";
					System.out.println(sql);
					frmProgettoPapaleo.setVisible(false);
					Anagrafica.main(null);
					try (Connection conn = (Connection) DriverManager.getConnection(jdbcUrl, username, password); 
							PreparedStatement stmt = conn.prepareStatement(sql)) {
						stmt.executeUpdate(sql);
						}catch (SQLException exx) {
							//exx.printStackTrace();
							}
					}catch(Exception ex){
					} 
			}
		
			
	
		});
		btnAzzera.setBounds(977, 510, 107, 31);
		panel.add(btnAzzera);
		
		JLabel lblAttenzioneCliccandoAzzera = new JLabel("ATTENZIONE CLICCANDO AZZERA SI ELIMINA IL DATABASE");
		lblAttenzioneCliccandoAzzera.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAttenzioneCliccandoAzzera.setBounds(975, 551, 297, 31);
		panel.add(lblAttenzioneCliccandoAzzera);
		
		
		
		
		JLabel lblCodicefiscale = new JLabel("Codice Fiscale");
		lblCodicefiscale.setBounds(28, 342, 116, 14);
		panel.add(lblCodicefiscale);
		
		txtCodicefiscale = new JTextField();
		txtCodicefiscale.setColumns(10);
		txtCodicefiscale.setBounds(154, 338, 140, 22);
		panel.add(txtCodicefiscale);
		
		txtIndirizzo = new JTextField();
		txtIndirizzo.setColumns(10);
		txtIndirizzo.setBounds(154, 367, 140, 22);
		panel.add(txtIndirizzo);
		
		txtCitta = new JTextField();
		txtCitta.setColumns(10);
		txtCitta.setBounds(154, 395, 140, 22);
		panel.add(txtCitta);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setBounds(28, 370, 116, 14);
		panel.add(lblIndirizzo);
		
		JLabel lblCitta = new JLabel("Citt\u00E0");
		lblCitta.setBounds(28, 398, 116, 14);
		panel.add(lblCitta);
		
		txtStato = new JTextField();
		txtStato.setColumns(10);
		txtStato.setBounds(154, 480, 140, 22);
		panel.add(txtStato);
		
		txtCap = new JTextField();
		txtCap.setColumns(10);
		txtCap.setBounds(154, 452, 140, 22);
		panel.add(txtCap);
		
		txtProvincia = new JTextField();
		txtProvincia.setColumns(10);
		txtProvincia.setBounds(154, 423, 140, 22);
		panel.add(txtProvincia);
		
		JLabel lblCap = new JLabel("Cap");
		lblCap.setBounds(28, 455, 116, 14);
		panel.add(lblCap);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(28, 427, 116, 14);
		panel.add(lblProvincia);
		
		JLabel lblStato = new JLabel("Stato");
		lblStato.setBounds(28, 483, 116, 14);
		panel.add(lblStato);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(28, 512, 116, 14);
		panel.add(lblEmail);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setBounds(28, 540, 116, 14);
		panel.add(lblNote);
		
		JLabel lblIstruzione = new JLabel("Istruzione");
		lblIstruzione.setBounds(28, 568, 116, 14);
		panel.add(lblIstruzione);
		
		txtIstruzione = new JTextField();
		txtIstruzione.setColumns(10);
		txtIstruzione.setBounds(154, 565, 140, 22);
		panel.add(txtIstruzione);
		
		txtNote = new JTextField();
		txtNote.setColumns(10);
		txtNote.setBounds(154, 537, 140, 22);
		panel.add(txtNote);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(154, 508, 140, 22);
		panel.add(txtEmail);
		
		
		
		
		btnAggiorna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProgettoPapaleo.setVisible(false);
				Anagrafica.main(null);
				
				}
			});
		}
	}