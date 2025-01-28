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
import java.awt.Window;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import java.awt.Window.Type;

public class Entrata{

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
		// new Pulsanti(); da verificare se usare classe per evidenziare evento
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Entrata window = new Entrata();
					window.frmProgettoPapaleo.setVisible(true);
					window.frmProgettoPapaleo.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
					}
				}
			});
		}
	protected void pack() {
		// TODO Auto-generated method stub
	}

	protected void setLocationRelativeTo(Object object) {
		// TODO Auto-generated method stub
	}
	public void clock()
	{
		Thread clock=new Thread() 
		{
			public void run()
			{
				try {
					for(;;)
					{
						LocalDate today = LocalDate.now( ZoneId.of( "Europe/Rome" ) );
						int month = today.getMonthValue();  // Returns 1-12 as values.
						Calendar cal=new GregorianCalendar();
						int day=cal.get(Calendar.DAY_OF_MONTH);
						int year=cal.get(Calendar.YEAR);
						int second=cal.get(Calendar.SECOND);
						int minute=cal.get(Calendar.MINUTE);
						int hour=cal.get(Calendar.HOUR_OF_DAY);
						lbldata_ora.setText("    "+day+"/"+month+ "/"+year+" - " + hour+":"+minute+":"+second );
						sleep(1000);
						}
					} catch (InterruptedException e){
						e.printStackTrace();
						}
				}};
				clock.start();
				}
	public  Entrata() {
		initialize();
		clock();
		}
	private void initialize() {
		frmProgettoPapaleo = new JFrame();
		frmProgettoPapaleo.setType(Type.UTILITY);
		frmProgettoPapaleo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProgettoPapaleo.setResizable(false);
		frmProgettoPapaleo.setBackground(Color.WHITE);
		frmProgettoPapaleo.setTitle("ENTRATA");
		frmProgettoPapaleo.setBounds(100, 100, 801, 305);
		frmProgettoPapaleo.getContentPane().setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setEnabled(false);
		tabbedPane.setBounds(730, 639, 63, 29);
		frmProgettoPapaleo.getContentPane().add(tabbedPane);
		JPanel panel = new JPanel();
		tabbedPane.addTab("ACCESSO", null, panel, null);
		panel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
		});
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(956, 161, 279, 101);
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
		JButton btnEsportaCsv = new JButton("esporta Utente in formato csv");
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
		btnEsportaCsv.setBounds(946, 127, 215, 23);
		panel.add(btnEsportaCsv);
		
		JButton btnAggiorna = new JButton("Aggiorna");
		btnAggiorna.setBounds(1030, 467, 89, 23);
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
						          Entrata.main(null);
						          
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
		
		btnModifica.setBounds(1135, 467, 89, 23);
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
		btnElimina.setBounds(1030, 509, 89, 23);
		panel.add(btnElimina);
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.setBounds(1135, 509, 89, 23);
		panel.add(btnExit_1);
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProgettoPapaleo=new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmProgettoPapaleo, "Confermi di Voler uscire","Login System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);				
					}
				}
			});
		
		JLabel lblEvento = new JLabel("Evento");
		lblEvento.setBounds(1030, 331, 56, 16);
		panel.add(lblEvento);
		txtEvento = new JTextField();
		txtEvento.setColumns(10);
		txtEvento.setBounds(1096, 328, 116, 22);
		panel.add(txtEvento);
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(1031, 361, 56, 16);
		panel.add(lblData);
		txtData = new JTextField();
		txtData.setColumns(10);
		txtData.setBounds(1097, 358, 116, 22);
		panel.add(txtData);
		JLabel lblOrario = new JLabel("Orario");
		lblOrario.setBounds(1030, 392, 46, 14);
		panel.add(lblOrario);
		txtOrario = new JTextField();
		txtOrario.setColumns(10);
		txtOrario.setBounds(1096, 388, 116, 22);
		panel.add(txtOrario);
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(863, 395, 46, 14);
		panel.add(lblId);
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(929, 392, 56, 20);
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
					String query="select * from utenti where "+selection+"  =? ";
					
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
		textFieldSearch.setBounds(1089, 422, 146, 31);
		panel.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		comboBoxSelection = new JComboBox();
		comboBoxSelection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSelection.setModel(new DefaultComboBoxModel(new String[] {"id", "Utente", "Evento", "Data", "Orario"}));
		comboBoxSelection.setBounds(972, 422, 107, 31);
		panel.add(comboBoxSelection);
		JLabel lblRicercaContattoPer = new JLabel("RICERCA EVENTO PER:");
		lblRicercaContattoPer.setBounds(1078, 285, 146, 31);
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
					
					Entrata.main(null);
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
		btnAzzera.setBounds(845, 454, 152, 72);
		panel.add(btnAzzera);
		
		JLabel lblAttenzioneCliccandoAzzera = new JLabel("ATTENZIONE CLICCANDO AZZERA SI ELIMINA IL DATABASE");
		lblAttenzioneCliccandoAzzera.setFont(new Font("Tahoma", Font.PLAIN, 6));
		lblAttenzioneCliccandoAzzera.setBounds(845, 406, 175, 47);
		panel.add(lblAttenzioneCliccandoAzzera);
		lbldata_ora = new JLabel("     DATA E ORA");
		lbldata_ora.setBounds(67, 32, 716, 52);
		frmProgettoPapaleo.getContentPane().add(lbldata_ora);
		lbldata_ora.setFont(new Font("Segoe UI Black", Font.PLAIN, 55));
		JLabel lblUtente = new JLabel("Passa il tuo badge");
		lblUtente.setBounds(223, 95, 755, 64);
		frmProgettoPapaleo.getContentPane().add(lblUtente);
		lblUtente.setBackground(Color.WHITE);
		lblUtente.setFont(new Font("Arial Black", Font.PLAIN, 33));
		txtUtente = new JTextField();
		txtUtente.setBounds(35, 164, 725, 95);
		frmProgettoPapaleo.getContentPane().add(txtUtente);
		
		txtUtente.setHorizontalAlignment(SwingConstants.LEFT);
		txtUtente.setFont(new Font("Tahoma", Font.PLAIN, 55));
		txtUtente.setColumns(10);
		
		
		
		JButton btnEntrata = new JButton("ENTRATA");
		btnEntrata.setBounds(10, 270, 369, 279);
		frmProgettoPapaleo.getContentPane().add(btnEntrata);
		btnEntrata.setFont(new Font("Tahoma", Font.PLAIN, 70));
		
		
		JButton btnUscita = new JButton("USCITA");
		btnUscita.setBounds(414, 270, 361, 279);
		frmProgettoPapaleo.getContentPane().add(btnUscita);
		btnUscita.setFont(new Font("Tahoma", Font.PLAIN, 70));
		
		frmProgettoPapaleo.getRootPane().setDefaultButton(btnEntrata);
		
		
		btnUscita.addActionListener(new ActionListener() {
			
			private static final int TIME_VISIBLE = 3000;

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
						
						 JOptionPane pane = new JOptionPane("USCITA DI "+txtUtente.getText()+ " INSERITA CORRETTAMENTE", JOptionPane.INFORMATION_MESSAGE);
			                JDialog dialog = pane.createDialog(null, "MESSAGGIO DI SISTEMA");
			                dialog.setModal(false);
			                dialog.setVisible(true);
			                pane.setBackground(new Color(255, 0, 0));
			                new Timer(TIME_VISIBLE, new ActionListener() {
			                    @Override
			                    public void actionPerformed(ActionEvent e) {
			                        dialog.setVisible(false);
			                    }
			                }).start();
			                
			                
			                
			                
			                
			                
				//		JPanel panel = new JPanel();
				//       panel.setBackground(new Color(255, 0, 0));
				 //       panel.setLayout(null);
						
					//JOptionPane.showMessageDialog(null, panel,"USCITA REGISTRATA CORRETTAMENTE "+txtUtente.getText() , 1);
					
					
					//	JOptionPane.showMessageDialog(null, "USCITA DI "+txtUtente.getText()+ " REGISTRATA CORRETTAMENTE");
				        
  
				        
				    //    JOptionPane pane = new JOptionPane("ENTRATA DI "+txtUtente.getText()+ " INSERITA CORRETTAMENTE", JOptionPane.INFORMATION_MESSAGE);
		             //   JDialog dialog = pane.createDialog(null, "Title");
		              //  dialog.setModal(false);
		               // dialog.setVisible(true);

		               
		         
				        
						txtEvento.setText("");
						txtUtente.setText("");
						txtData.setText("");
						txtOrario.setText("");
						txtUtente.requestFocusInWindow();
						//frmProgettoPapaleo.setVisible(false);
						//Entrata.main(null);
				        
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "PARAMETRI NON INSERITI, O NEL CAMPO Data NON HAI INSERITO VALORI NUMERICI");
						}
				refreshTable();
				}
			
			
			
			
			
			
			private void refreshTable() {	txtUtente.requestFocusInWindow();
			}
			});
		btnEntrata.addActionListener(new ActionListener() {
			private static final int TIME_VISIBLE = 3000;
			
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
						
						JPanel panel = new JPanel();
				        panel.setBackground(new Color(102, 205, 170));
				        panel.setLayout(null);
						
					//JOptionPane.showMessageDialog(null, panel, "ENTRATA REGISTRATA CORRETTAMENTE " /*+txtUtente.getText()*/ , 1);
					
				        
				        
				        JOptionPane pane = new JOptionPane("ENTRATA DI "+txtUtente.getText()+ " INSERITA CORRETTAMENTE", JOptionPane.INFORMATION_MESSAGE);
		                JDialog dialog = pane.createDialog(null, "MESSAGGIO DI SISTEMA");
		                dialog.setModal(false);
		                dialog.setVisible(true);
		                pane.setBackground(new Color(0, 143, 57));
		                new Timer(TIME_VISIBLE, new ActionListener() {
		                    @Override
		                    public void actionPerformed(ActionEvent e) {
		                        dialog.setVisible(false);
		                    }
		                }).start();
		            }
				        
				        
					
					
						txtEvento.setText("");
						txtUtente.setText("");
						txtData.setText("");
						txtOrario.setText("");
						 
						
						txtUtente.requestFocusInWindow();// inserimento cursore su txtUtente
						//frame.setVisible(true);
						//frmProgettoPapaleo.setVisible(false);
						//Entrata.main(null);
				        
		                    	
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "PARAMETRI NON INSERITI, O NEL CAMPO Data NON HAI INSERITO VALORI NUMERICI");
						}
				refreshTable();
				}
			
			
			
		     
					
			
			
			
			
			private void refreshTable() {	
			}
			});
	
		btnAggiorna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProgettoPapaleo.setVisible(false);
				Entrata.main(null);
				
				}
			});
		}
	}