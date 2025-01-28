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

import it.tesi.java.accessi.business.AccessiDb;
import it.tesi.java.accessi.export.Create_CSV;
import it.tesi.java.accessi.model.Contatto;

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



public class adminor{

	public JFrame frmProgettoPapaleo;
	private JTable listautenti;
	private JTextField txtUtente;
	private JTextField txtEvento;
	private JTextField txtData;
	private JTextField txtOrario;
	private JLabel lbldata_ora;
	private JTextField txtId;
	private JTextField textFieldSearch;
	private JComboBox comboBoxSelection;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminor window = new adminor();
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
		public  adminor() {
		initialize();
		clock();
		}

	private void initialize() {
		frmProgettoPapaleo = new JFrame();
		frmProgettoPapaleo.getContentPane().setBackground(SystemColor.textHighlight);
		frmProgettoPapaleo.setType(Type.UTILITY);
		frmProgettoPapaleo.setResizable(false);
		frmProgettoPapaleo.setTitle("Software Per Il Controllo Del Personale");
		frmProgettoPapaleo.setBounds(100, 100, 1013, 606);
		frmProgettoPapaleo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProgettoPapaleo.getContentPane().setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 23, 979, 523);
		frmProgettoPapaleo.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Accessi", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
				
		});
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(296, 74, 668, 285);
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
				        
				        
				        txtUtente.setText(model.getValueAt(selectedRow, 1).toString());
				        txtEvento.setText(model.getValueAt(selectedRow, 2).toString());
				        txtData.setText(model.getValueAt(selectedRow, 3).toString());
				        txtOrario.setText(model.getValueAt(selectedRow, 4).toString());
				    }                                  
				    
				
			
		});
		
		listautenti.setAutoCreateRowSorter(true);
		listautenti.setModel(new DefaultTableModel(new Object[][] {},
				new String[] {"ID", "Utente", "Evento", "Data","Orario"}) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false,false
					};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		listautenti.getColumnModel().getColumn(0).setPreferredWidth(103);
		listautenti.getColumnModel().getColumn(1).setPreferredWidth(215);
		listautenti.getColumnModel().getColumn(2).setPreferredWidth(207);
		listautenti.getColumnModel().getColumn(3).setPreferredWidth(244);
		listautenti.getColumnModel().getColumn(4).setPreferredWidth(284);
		
		DefaultTableModel dtm = (DefaultTableModel) listautenti.getModel();
		
		List<Contatto> utenti;
		try {
			utenti = AccessiDb.getInstance().ricercautenti();
			
			for (Contatto c : utenti) {
				Vector rowData = new Vector();
				rowData.add(c.getId());
				rowData.add(c.getUtente());
				rowData.add(c.getEvento());
				rowData.add(c.getData());
				rowData.add(c.getOrario());
				
				
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
				Create_CSV.main(null);
				
				 
			}
		});
		btnEsportaCsv.setBounds(675, 30, 249, 23);
		panel.add(btnEsportaCsv);
		
		JButton btnAggiorna = new JButton("Aggiorna");
		btnAggiorna.setBounds(759, 370, 89, 23);
		panel.add(btnAggiorna);
		
		
		
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				
				        int i = listautenti.getSelectedRow();
				         DefaultTableModel model = (DefaultTableModel)listautenti.getModel();
				        if(i >= 0)
				        {
				            model.setValueAt(txtId.getText(), i, 0);
				            model.setValueAt(txtUtente.getText(), i, 1);
				            model.setValueAt(txtEvento.getText(), i, 2);
				            model.setValueAt(txtData.getText(), i, 3);
				            model.setValueAt(txtOrario.getText(), i, 4);
				             try 
							{
								int SelezioneIndiceRiga=listautenti.getSelectedRow();
								model.removeRow(SelezioneIndiceRiga);
								String jdbcUrl = "jdbc:mysql://localhost:3306/controllo_accessi";
								String username = "ammi";
								String password = "ammi";
								String query= "UPDATE `utenti` SET `Utente`='"+txtUtente.getText()+"',`Evento`='"+txtEvento.getText()+"',`Data`='"+txtData.getText()+"',`Orario`='"+txtOrario.getText()+"' WHERE `id`= "+txtId.getText(); 
						          System.out.println(query);
						          frmProgettoPapaleo.setVisible(false);
						          adminor.main(null);
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
			        txtUtente.setText(model.getValueAt(selectedRow, 1).toString());
			        txtEvento.setText(model.getValueAt(selectedRow, 2).toString());
			        txtData.setText(model.getValueAt(selectedRow, 3).toString());
			        txtData.setText(model.getValueAt(selectedRow, 4).toString());
					}
		});
		
		btnModifica.setBounds(864, 370, 89, 23);
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
					String sql = "DELETE FROM `utenti` WHERE `utenti`.`id` ="+Selezione.getIdSelezione();//+SelezioneIndiceRiga+1;
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
		btnElimina.setBounds(759, 412, 89, 23);
		panel.add(btnElimina);
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.setBounds(864, 412, 89, 23);
		panel.add(btnExit_1);
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProgettoPapaleo=new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmProgettoPapaleo, "Confermi di Voler uscire","Login System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);				
					}
				}
			});
		JLabel lblUtente = new JLabel("Utente");
		lblUtente.setBounds(28, 121, 56, 16);
		panel.add(lblUtente);
		txtUtente = new JTextField();
		txtUtente.setColumns(10);
		txtUtente.setBounds(94, 118, 116, 22);
		panel.add(txtUtente);
		JLabel lblEvento = new JLabel("Evento");
		lblEvento.setBounds(295, 416, 56, 16);
		panel.add(lblEvento);
		txtEvento = new JTextField();
		txtEvento.setColumns(10);
		txtEvento.setBounds(361, 413, 116, 22);
		panel.add(txtEvento);
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(296, 446, 56, 16);
		panel.add(lblData);
		txtData = new JTextField();
		txtData.setColumns(10);
		txtData.setBounds(362, 443, 116, 22);
		panel.add(txtData);
		JLabel lblOrario = new JLabel("Orario");
		lblOrario.setBounds(295, 477, 46, 14);
		panel.add(lblOrario);
		txtOrario = new JTextField();
		txtOrario.setColumns(10);
		txtOrario.setBounds(361, 473, 116, 22);
		panel.add(txtOrario);
		JButton btnAggiungi = new JButton("ENTRATA");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
				SimpleDateFormat tmf = new SimpleDateFormat("HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				Date timeObj = calendar.getTime();
				Date dateObj = calendar.getTime();
				String formattedTime = tmf.format(timeObj);
		        
		        String formattedDate = dtf.format(dateObj);
		        
				Contatto nuovoContatto = new Contatto();
				nuovoContatto.setEvento("ENTRATA");
				nuovoContatto.setUtente(txtUtente.getText());
				nuovoContatto.setData(formattedDate);
				nuovoContatto.setOrario(formattedTime);
					try {
					int id = AccessiDb.getInstance().aggiungiContatto(nuovoContatto);
					if(id > 0) {
					//	JOptionPane.showMessageDialog(null, "ENTRATA DI "+txtUtente.getText()+ " REGISTRATA CORRETTAMENTE");
						txtEvento.setText("");
						txtUtente.setText("");
						txtData.setText("");
						txtOrario.setText("");
						frmProgettoPapaleo.setVisible(false);
						adminor.main(null);
				        
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "PARAMETRI NON INSERITI, O NEL CAMPO Data NON HAI INSERITO VALORI NUMERICI");
						}
				refreshTable();
				}
			
			
			
			
			
			
			private void refreshTable() {	
			}
			});
		JButton btnAggiungi_1 = new JButton("USCITA");
		
		btnAggiungi_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
				SimpleDateFormat tmf = new SimpleDateFormat("HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				Date timeObj = calendar.getTime();
				Date dateObj = calendar.getTime();
				String formattedTime = tmf.format(timeObj);
		        
		        String formattedDate = dtf.format(dateObj);
		        
				Contatto nuovoContatto = new Contatto();
				nuovoContatto.setEvento("USCITA");
				nuovoContatto.setUtente(txtUtente.getText());
				nuovoContatto.setData(formattedDate);
				nuovoContatto.setOrario(formattedTime);
					try {
					int id = AccessiDb.getInstance().aggiungiContatto(nuovoContatto);
					if(id > 0) {
						//JOptionPane.showMessageDialog(null, "USCITA DI "+txtUtente.getText()+ " REGISTRATA CORRETTAMENTE");
						txtEvento.setText("");
						txtUtente.setText("");
						txtData.setText("");
						txtOrario.setText("");
						frmProgettoPapaleo.setVisible(false);
						adminor.main(null);
				        
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "PARAMETRI NON INSERITI, O NEL CAMPO Data NON HAI INSERITO VALORI NUMERICI");
						}
				refreshTable();
				}
			
			
			
			
			
			
			private void refreshTable() {	
			}
			});
		
		btnAggiungi_1.setBounds(135, 185, 89, 25);
		panel.add(btnAggiungi_1);
		
		
		
		
		btnAggiungi.setBounds(28, 185, 97, 25);
		panel.add(btnAggiungi);
		lbldata_ora = new JLabel("DATA E ORA");
		lbldata_ora.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbldata_ora.setBounds(23, 11, 619, 52);
		panel.add(lbldata_ora);
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(28, 81, 46, 14);
		panel.add(lblId);
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(94, 78, 116, 20);
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
					String query="select * from utenti where "+selection+"=? ";
					
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
		textFieldSearch.setBounds(592, 370, 146, 31);
		panel.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		comboBoxSelection = new JComboBox();
		comboBoxSelection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSelection.setModel(new DefaultComboBoxModel(new String[] {"id", "Utente", "Evento", "Data", "Orario"}));
		comboBoxSelection.setBounds(475, 370, 107, 31);
		panel.add(comboBoxSelection);
		JLabel lblRicercaContattoPer = new JLabel("RICERCA EVENTO PER:");
		lblRicercaContattoPer.setBounds(343, 370, 146, 31);
		panel.add(lblRicercaContattoPer);
		
		JButton btnAzzera = new JButton("AZZERA");
		btnAzzera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				try 
				{
					String jdbcUrl = "jdbc:mysql://localhost:3306/controllo_accessi";
					String username = "ammi";
					String password = "ammi";
					String sql = "TRUNCATE TABLE utenti";
					System.out.println(sql);
					frmProgettoPapaleo.setVisible(false);
					adminor.main(null);
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
		btnAzzera.setBounds(72, 419, 152, 72);
		panel.add(btnAzzera);
		
		JLabel lblAttenzioneCliccandoAzzera = new JLabel("ATTENZIONE CLICCANDO AZZERA SI ELIMINA IL DATABASE");
		lblAttenzioneCliccandoAzzera.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAttenzioneCliccandoAzzera.setBounds(10, 370, 300, 47);
		panel.add(lblAttenzioneCliccandoAzzera);
		
		JButton btnAnagrafica = new JButton("ANAGRAFICA");
		btnAnagrafica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Anagrafica.main(null);
			}
		});
		btnAnagrafica.setBounds(28, 265, 131, 25);
		panel.add(btnAnagrafica);
		
		JButton btnBustepaga = new JButton("CONTABILIT\u00C0");
		btnBustepaga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bustepaga.main(null);
			}
		});
		btnBustepaga.setBounds(28, 302, 131, 25);
		panel.add(btnBustepaga);
		
		
		btnAggiorna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProgettoPapaleo.setVisible(false);
				adminor.main(null);
				
				}
			});
		}
	}