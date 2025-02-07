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

import it.tesi.java.accessi.business.AccessiDbBustepaga;
import it.tesi.java.accessi.export.Create_CSV_Bustepaga;
import it.tesi.java.accessi.model.ContattoBustepaga;

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



public class Bustepaga{

	public JFrame frmProgettoPapaleo;
	private JTable listautenti;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtCodice;
	private JTextField txtRetribuzione;
	private JLabel lbldata_ora;
	private JTextField txtId;
	private JTextField textFieldSearch;
	private JComboBox comboBoxSelection;
	private JTextField txtOremensili;
	private JTextField txtSistemaorario;
	private JTextField txtSistemamensilizzato;
	private JTextField txtAssenze;
	private JTextField txtMensilita;
	private JTextField txtAnno;
	private JTextField txtStato;
	private JTextField txtDatapagamento;
	private JTextField txtIban;
	private JTextField txtAcconto;
	private JTextField txtNote;
	private JTextField txtPermessi;
	private JTextField txtStraordinari;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bustepaga window = new Bustepaga();
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
		public  Bustepaga() {
		initialize();
		clock();
		}

	private void initialize() {
		frmProgettoPapaleo = new JFrame();
		frmProgettoPapaleo.setBackground(SystemColor.inactiveCaption);
		frmProgettoPapaleo.getContentPane().setBackground(SystemColor.textHighlight);
		frmProgettoPapaleo.setType(Type.UTILITY);
		frmProgettoPapaleo.setResizable(false);
		frmProgettoPapaleo.setTitle("Software Per Il Controllo Del Personale");
		frmProgettoPapaleo.setBounds(100, 100, 1876, 752);
		frmProgettoPapaleo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProgettoPapaleo.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.inactiveCaption);
		tabbedPane.setBounds(10, 23, 1843, 680);
		frmProgettoPapaleo.getContentPane().add(tabbedPane);
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Gestione Contabilit�", null, panel, null);
		panel.setLayout(null);
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
				
		});
		scrollPane.setBounds(10, 11, 1798, 406);
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
				        txtRetribuzione.setText(model.getValueAt(selectedRow, 4).toString());
				        txtOremensili.setText(model.getValueAt(selectedRow, 5).toString());
				        txtSistemaorario.setText(model.getValueAt(selectedRow, 6).toString());
				        txtSistemamensilizzato.setText(model.getValueAt(selectedRow, 7).toString());
				        txtStraordinari.setText(model.getValueAt(selectedRow, 8).toString());
				        txtAssenze.setText(model.getValueAt(selectedRow, 9).toString());
				        txtMensilita.setText(model.getValueAt(selectedRow, 10).toString());
				        txtAnno.setText(model.getValueAt(selectedRow, 11).toString());
				        txtIban.setText(model.getValueAt(selectedRow, 12).toString());
				        txtDatapagamento.setText(model.getValueAt(selectedRow, 13).toString());
				        txtStato.setText(model.getValueAt(selectedRow, 14).toString());
				        txtPermessi.setText(model.getValueAt(selectedRow, 15).toString());
				        txtNote.setText(model.getValueAt(selectedRow, 16).toString());
				        txtAcconto.setText(model.getValueAt(selectedRow, 17).toString());
						
				    }                                  
				    
				
			
		});
		
		listautenti.setAutoCreateRowSorter(true);
		listautenti.setModel(new DefaultTableModel(new Object[][] {},
				new String[] {"ID", "Nome", "Cognome", "Codice","Retribuzione","Ore mensili","Sistema Orario","Sistema Mensilizzato","Straordinari","Assenze","Mensilit�","Anno","Iban","Data Pagamento","Stato","Permessi","Note","Acconto"}) { 
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
		
		List<ContattoBustepaga> utentibustepaga;
		try {
			utentibustepaga = AccessiDbBustepaga.getInstance().ricercautentibustepaga();
			
			for (ContattoBustepaga c : utentibustepaga) {
				Vector rowData = new Vector();
				rowData.add(c.getId());
				rowData.add(c.getNome());
				rowData.add(c.getCognome());
				rowData.add(c.getCodice());
				rowData.add(c.getRetribuzione());
				rowData.add(c.getOremensili());
				rowData.add(c.getSistemaorario());
				rowData.add(c.getSistemamensilizzato());
				rowData.add(c.getStraordinari());
				rowData.add(c.getAssenze());
				rowData.add(c.getMensilita());
				rowData.add(c.getAnno());
				rowData.add(c.getIban());
				rowData.add(c.getDatapagamento());
				rowData.add(c.getStato());
				rowData.add(c.getPermessi());
				rowData.add(c.getNote());
				rowData.add(c.getAcconto());
				
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
				Create_CSV_Bustepaga.main(null);
				
				 
			}
		});
		btnEsportaCsv.setBounds(10, 433, 249, 23);
		panel.add(btnEsportaCsv);
		
		JButton btnAggiorna = new JButton("Aggiorna");
		btnAggiorna.setBounds(316, 456, 89, 23);
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
				            model.setValueAt(txtRetribuzione.getText(), i, 4);
				            model.setValueAt(txtOremensili.getText(), i, 5);
				            model.setValueAt(txtSistemaorario.getText(), i, 6);
				            model.setValueAt(txtSistemamensilizzato.getText(), i, 7);
				            model.setValueAt(txtStraordinari.getText(), i, 8);
				            model.setValueAt(txtAssenze.getText(), i, 9);
				            model.setValueAt(txtAssenze.getText(), i, 10);
							          
				             try 
							{
								int SelezioneIndiceRiga=listautenti.getSelectedRow();
								model.removeRow(SelezioneIndiceRiga);
								String jdbcUrl = "jdbc:mysql://100.116.192.84:3306/controllo_accessi";
								String username = "root";
								String password = "";
								String query= "UPDATE `utentibustepaga` SET `Nome`='"+txtNome.getText()+"',`Cognome`='"+txtCognome.getText()+"',`Codice`='"+txtCodice.getText()+"',`Retribuzione`='"+txtRetribuzione.getText()+"',`Oremensili`='"+txtOremensili.getText()+"',`Sistemaorario`='"+txtSistemaorario.getText()+"',`Sistemamensilizzato`='"+txtSistemamensilizzato.getText()+"',`Straordinari`='"+txtStraordinari.getText()+"',`Assenze`='"+txtAssenze.getText()+"',`Mensilita`='"+txtMensilita.getText()+"',`Anno`='"+txtAnno.getText()+"',`Iban`='"+txtIban.getText()+"',`Datapagamento`='"+txtDatapagamento.getText()+"',`Stato`='"+txtStato.getText()+"',`Permessi`='"+txtPermessi.getText()+"',`Note`='"+txtNote.getText()+"',`Acconto`='"+txtAcconto.getText()+"' WHERE `id`= "+txtId.getText(); 
						          System.out.println(query);
						          frmProgettoPapaleo.setVisible(false);
						          Bustepaga.main(null);
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
			        txtRetribuzione.setText(model.getValueAt(selectedRow, 4).toString());
			        txtOremensili.setText(model.getValueAt(selectedRow, 5).toString());
			        txtSistemaorario.setText(model.getValueAt(selectedRow, 6).toString());
			        txtSistemamensilizzato.setText(model.getValueAt(selectedRow, 7).toString());
			        txtStraordinari.setText(model.getValueAt(selectedRow, 8).toString());
			        txtAssenze.setText(model.getValueAt(selectedRow, 9).toString());
			        txtMensilita.setText(model.getValueAt(selectedRow, 10).toString());
			        txtAnno.setText(model.getValueAt(selectedRow, 11).toString());
			        txtIban.setText(model.getValueAt(selectedRow, 12).toString());
			        txtDatapagamento.setText(model.getValueAt(selectedRow, 13).toString());
			        txtStato.setText(model.getValueAt(selectedRow, 14).toString());
			        txtPermessi.setText(model.getValueAt(selectedRow, 15).toString());
			        txtNote.setText(model.getValueAt(selectedRow, 16).toString());
			        txtAcconto.setText(model.getValueAt(selectedRow, 17).toString());
					
					}
		});
		
		btnModifica.setBounds(415, 456, 89, 23);
		panel.add(btnModifica);
		JButton btnElimina = new JButton("Elimina");
		btnElimina.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=(DefaultTableModel) listautenti.getModel();
				try 
				{
					int SelezioneIndiceRiga=listautenti.getSelectedRow();
					model.removeRow(SelezioneIndiceRiga);
					String jdbcUrl = "jdbc:mysql://100.116.192.84:3306/controllo_accessi";
					String username = "root";
					String password = "";
					String sql = "DELETE FROM `utentibustepaga` WHERE `utentibustepaga`.`id` ="+Selezione.getIdSelezione();//+SelezioneIndiceRiga+1;
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
		btnElimina.setBounds(415, 428, 89, 23);
		panel.add(btnElimina);
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.setBounds(1721, 592, 89, 23);
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
		lblNome.setBounds(181, 523, 56, 16);
		panel.add(lblNome);
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(141, 541, 140, 22);
		panel.add(txtNome);
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(319, 523, 56, 16);
		panel.add(lblCognome);
		txtCognome = new JTextField();
		txtCognome.setColumns(10);
		txtCognome.setBounds(291, 541, 140, 22);
		panel.add(txtCognome);
		JLabel lblCodice = new JLabel("Codice");
		lblCodice.setBounds(484, 523, 56, 16);
		panel.add(lblCodice);
		txtCodice = new JTextField();
		txtCodice.setColumns(10);
		txtCodice.setBounds(441, 541, 140, 22);
		panel.add(txtCodice);
		
		JLabel lblRetribuzione = new JLabel("Retribuzione");
		lblRetribuzione.setBounds(621, 523, 89, 14);
		panel.add(lblRetribuzione);
		txtRetribuzione = new JTextField();
		txtRetribuzione.setColumns(10);
		txtRetribuzione.setBounds(593, 541, 140, 22);
		panel.add(txtRetribuzione);
		
		JLabel lblOremensili = new JLabel("Ore Mensili");
		lblOremensili.setBounds(789, 523, 69, 14);
		panel.add(lblOremensili);
		
		txtOremensili = new JTextField();
		txtOremensili.setColumns(10);
		txtOremensili.setBounds(743, 541, 140, 22);
		panel.add(txtOremensili);
		
		JLabel lblSistemaorario = new JLabel("Sistema Orario");
		lblSistemaorario.setBounds(929, 524, 116, 14);
		panel.add(lblSistemaorario);
		
		txtSistemaorario = new JTextField();
		txtSistemaorario.setColumns(10);
		txtSistemaorario.setBounds(893, 541, 140, 22);
		panel.add(txtSistemaorario);
		
		
		txtSistemamensilizzato = new JTextField();
		txtSistemamensilizzato.setColumns(10);
		txtSistemamensilizzato.setBounds(1043, 541, 140, 22);
		panel.add(txtSistemamensilizzato);
		
		JLabel lblStraordinari = new JLabel("Straordinari");
		
		lblStraordinari.setBounds(1219, 524, 116, 14);
		panel.add(lblStraordinari);
		txtStraordinari = new JTextField();
		txtStraordinari.setColumns(10);
		txtStraordinari.setBounds(1193, 541, 140, 22);
		panel.add(txtStraordinari);
		
		
		
		
		
		
		
		JButton btnAggiungi = new JButton("AGGIUNGI");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ContattoBustepaga nuovoContattoBustepaga = new ContattoBustepaga();
				nuovoContattoBustepaga.setNome(txtNome.getText());
				nuovoContattoBustepaga.setCognome(txtCognome.getText());
				nuovoContattoBustepaga.setCodice(txtCodice.getText());
				nuovoContattoBustepaga.setRetribuzione(txtRetribuzione.getText());
				nuovoContattoBustepaga.setOremensili(txtOremensili.getText());
				nuovoContattoBustepaga.setSistemaorario(txtSistemaorario.getText());
				nuovoContattoBustepaga.setSistemamensilizzato(txtSistemamensilizzato.getText());
				nuovoContattoBustepaga.setStraordinari(txtStraordinari.getText());
				nuovoContattoBustepaga.setAssenze(txtAssenze.getText());
				nuovoContattoBustepaga.setMensilita(txtMensilita.getText());
				nuovoContattoBustepaga.setAnno(txtAnno.getText());
				nuovoContattoBustepaga.setIban(txtIban.getText());
				nuovoContattoBustepaga.setDatapagamento(txtDatapagamento.getText());
				nuovoContattoBustepaga.setStato(txtStato.getText());
				nuovoContattoBustepaga.setPermessi(txtPermessi.getText());
				nuovoContattoBustepaga.setNote(txtNote.getText());
				nuovoContattoBustepaga.setAcconto(txtAcconto.getText());
				
					try {
					int id = AccessiDbBustepaga.getInstance().aggiungiContattoBustepaga(nuovoContattoBustepaga);
					if(id > 0) {
					JOptionPane.showMessageDialog(null, "Utente "+txtNome.getText()+ " INSERITO CORRETTAMENTE");
						txtCognome.setText("");
						txtNome.setText("");
						txtCodice.setText("");
						txtRetribuzione.setText("");
						txtOremensili.setText("");
						txtSistemaorario.setText("");
						txtSistemamensilizzato.setText("");
						txtStraordinari.setText("");
						txtAssenze.setText("");
						txtMensilita.setText("");
						txtAnno.setText("");
						txtIban.setText("");
						txtDatapagamento.setText("");
						txtStato.setText("");
						txtPermessi.setText("");
						txtNote.setText("");
						txtAcconto.setText("");
						
						frmProgettoPapaleo.setVisible(false);
						Bustepaga.main(null);
				        
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
		
		
		
		
		btnAggiungi.setBounds(316, 428, 89, 23);
		panel.add(btnAggiungi);
		lbldata_ora = new JLabel("DATA E ORA");
		lbldata_ora.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbldata_ora.setBounds(23, 11, 392, 46);
		panel.add(lbldata_ora);
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(54, 524, 46, 14);
		panel.add(lblId);
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(15, 542, 116, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String selection=(String)comboBoxSelection.getSelectedItem();
					String jdbcUrl = "jdbc:mysql://100.116.192.84:3306/controllo_accessi";
					String username = "root";
					String password = "";
					String query="select * from utentibustepaga where "+selection+"=? ";
					
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
		textFieldSearch.setBounds(811, 430, 146, 31);
		panel.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		comboBoxSelection = new JComboBox();
		comboBoxSelection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSelection.setModel(new DefaultComboBoxModel(new String[] {"id", "Nome", "Cognome", "Codice", "Retribuzione","Oremensili","Sistemaorario","Sistemamensilizzato","Straordinari","Assenze","Mensilita","Anno","Iban","Datapagamento","Stato","Permessi","Note","Acconto"}));
		comboBoxSelection.setBounds(665, 430, 107, 31);
		panel.add(comboBoxSelection);
		JLabel lblRicercaContattoBustepagaPer = new JLabel("RICERCA UTENTE PER:");
		lblRicercaContattoBustepagaPer.setBounds(514, 430, 146, 31);
		panel.add(lblRicercaContattoBustepagaPer);
		
		JButton btnAzzera = new JButton("AZZERA");
		btnAzzera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				try 
				{
					String jdbcUrl = "jdbc:mysql://100.116.192.84:3306/controllo_accessi";
					String username = "root";
					String password = "";
					String sql = "TRUNCATE TABLE utentibustepaga";
					System.out.println(sql);
					frmProgettoPapaleo.setVisible(false);
					Bustepaga.main(null);
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
		btnAzzera.setBounds(1228, 452, 107, 31);
		panel.add(btnAzzera);
		
		JLabel lblAttenzioneCliccandoAzzera = new JLabel("ATTENZIONE CLICCANDO AZZERA SI ELIMINA IL DATABASE");
		lblAttenzioneCliccandoAzzera.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAttenzioneCliccandoAzzera.setBounds(1072, 428, 297, 31);
		panel.add(lblAttenzioneCliccandoAzzera);
		
		
		
		
		JLabel lblAssenze = new JLabel("Assenze");
		lblAssenze.setBounds(1389, 523, 69, 14);
		panel.add(lblAssenze);
		
		txtAssenze = new JTextField();
		txtAssenze.setColumns(10);
		txtAssenze.setBounds(1349, 541, 140, 22);
		panel.add(txtAssenze);
		
		txtMensilita = new JTextField();
		txtMensilita.setColumns(10);
		txtMensilita.setBounds(1499, 541, 140, 22);
		panel.add(txtMensilita);
		
		txtAnno = new JTextField();
		txtAnno.setColumns(10);
		txtAnno.setBounds(1649, 541, 140, 22);
		panel.add(txtAnno);
		
		JLabel lblMensilita = new JLabel("Mensilit\u00E0");
		lblMensilita.setBounds(1547, 523, 116, 14);
		panel.add(lblMensilita);
		
		JLabel lblAnno = new JLabel("Anno");
		lblAnno.setBounds(1692, 523, 116, 14);
		panel.add(lblAnno);
		
		txtStato = new JTextField();
		txtStato.setColumns(10);
		txtStato.setBounds(441, 592, 140, 22);
		panel.add(txtStato);
		
		txtDatapagamento = new JTextField();
		txtDatapagamento.setColumns(10);
		txtDatapagamento.setBounds(291, 592, 140, 22);
		panel.add(txtDatapagamento);
		
		txtIban = new JTextField();
		txtIban.setColumns(10);
		txtIban.setBounds(141, 592, 140, 22);
		panel.add(txtIban);
		
		JLabel lblDatapagamento = new JLabel("Data Pagamento");
		lblDatapagamento.setBounds(301, 574, 116, 14);
		panel.add(lblDatapagamento);
		
		JLabel lblIban = new JLabel("IBAN");
		lblIban.setBounds(169, 574, 116, 14);
		panel.add(lblIban);
		
		JLabel lblStato = new JLabel("Stato");
		lblStato.setBounds(477, 574, 116, 14);
		panel.add(lblStato);
		
		JLabel lblPermessi = new JLabel("Permessi");
		lblPermessi.setBounds(636, 574, 116, 14);
		panel.add(lblPermessi);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setBounds(792, 574, 116, 14);
		panel.add(lblNote);
		
		JLabel lblAcconto = new JLabel("Acconto");
		lblAcconto.setBounds(933, 574, 116, 14);
		panel.add(lblAcconto);
		
		txtAcconto = new JTextField();
		txtAcconto.setColumns(10);
		txtAcconto.setBounds(893, 591, 140, 22);
		panel.add(txtAcconto);
		
		txtNote = new JTextField();
		txtNote.setColumns(10);
		txtNote.setBounds(741, 592, 140, 22);
		panel.add(txtNote);
		
		txtPermessi = new JTextField();
		txtPermessi.setColumns(10);
		txtPermessi.setBounds(591, 592, 140, 22);
		panel.add(txtPermessi);
		
		JLabel lblSistemaMensilizzato = new JLabel("Sistema Mensilizzato");
		lblSistemaMensilizzato.setBounds(1055, 524, 122, 14);
		panel.add(lblSistemaMensilizzato);
		
		
		
		
		btnAggiorna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProgettoPapaleo.setVisible(false);
				Bustepaga.main(null);
				
				}
			});
		}
	}