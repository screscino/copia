

package it.tesi.java.accessi.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class loginAccessi {

	private JFrame frmLoginSystem;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					loginAccessi window = new loginAccessi();
					window.frmLoginSystem.setVisible(true);
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
			}
			
		});
	}

	public loginAccessi() {
		initialize();
	}

	private void initialize() {
		frmLoginSystem = new JFrame();
		frmLoginSystem.getContentPane().setBackground(SystemColor.activeCaption);
		frmLoginSystem.setTitle("Software Per Il Controllo Del Personale");
		frmLoginSystem.setBounds(200, 200, 486, 272);
		frmLoginSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginSystem.getContentPane().setLayout(null);
		
		JLabel lblLoginSystem = new JLabel("Login System");
		lblLoginSystem.setFont(new Font("Arial", Font.PLAIN, 25));
		lblLoginSystem.setBounds(143, 11, 166, 35);
		frmLoginSystem.getContentPane().add(lblLoginSystem);
		
		JLabel lblUsername = new JLabel("User Name");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 18));
		lblUsername.setBounds(77, 70, 118, 25);
		frmLoginSystem.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPassword.setBounds(77, 126, 107, 25);
		frmLoginSystem.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 18));
		txtUsername.setBounds(222, 68, 136, 28);
		frmLoginSystem.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(222, 123, 136, 28);
		frmLoginSystem.getContentPane().add(txtPassword);
		
		JButton btnLogin =  new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password=txtPassword.getText();
				String username=txtUsername.getText();
				
				if(password.equals("admin")&&username.equals("admin")) {
					txtPassword.setText(null);
					txtUsername.setText(null);
					frmLoginSystem.setVisible(false);
					
					
					//Rubrica info=new Rubrica();
					adminop.main(null);
					
					
				}
				else {
					//JOptionPane.showMessageDialog(null,"Invalid Login","Login Error",JOptionPane.ERROR_MESSAGE);
					txtPassword.setText(null);
					txtUsername.setText(null);
						
				}
				if(password.equals("utente")&&username.equals("utente")) {
					txtPassword.setText(null);
					txtUsername.setText(null);
					frmLoginSystem.setVisible(false);
					
					
					Utente.main(null);
				}
				else {
					//JOptionPane.showMessageDialog(null,"Invalid Login","Login Error",JOptionPane.ERROR_MESSAGE);
					txtPassword.setText(null);
					txtUsername.setText(null);
					
				}
				if(password.equals("entrata")&&username.equals("entrata")) {
					txtPassword.setText(null);
					txtUsername.setText(null);
					frmLoginSystem.setVisible(false);
					
					
					Entrata.main(null);
				}
				else {
					//JOptionPane.showMessageDialog(null,"Invalid Login","Login Error",JOptionPane.ERROR_MESSAGE);
					txtPassword.setText(null);
					txtUsername.setText(null);
					
				}
				
				
				if(password.equals("uscita")&&username.equals("uscita")) {
					txtPassword.setText(null);
					txtUsername.setText(null);
					frmLoginSystem.setVisible(false);
					
					
					Uscita.main(null);
				}
				else {
					//JOptionPane.showMessageDialog(null,"Invalid Login","Login Error",JOptionPane.ERROR_MESSAGE);
					txtPassword.setText(null);
					txtUsername.setText(null);
					
				}
				if(password.equals("anagrafica")&&username.equals("anagrafica")) {
					txtPassword.setText(null);
					txtUsername.setText(null);
					frmLoginSystem.setVisible(false);
					
					
					Anagrafica.main(null);
				}
				else {
					//JOptionPane.showMessageDialog(null,"Invalid Login","Login Error",JOptionPane.ERROR_MESSAGE);
					txtPassword.setText(null);
					txtUsername.setText(null);


				
				
				}
				
				
				
				
				
				
			}
			
		});
		btnLogin.setBounds(30, 192, 89, 23);
		frmLoginSystem.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsername.setText(null);
				txtPassword.setText(null);
			}
		});
		btnReset.setBounds(174, 192, 89, 23);
		frmLoginSystem.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLoginSystem=new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confermi di Voler uscire","Login System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
				
			}
		});
		btnExit.setBounds(328, 192, 89, 23);
		frmLoginSystem.getContentPane().add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(24, 166, 421, 2);
		frmLoginSystem.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(24, 57, 421, 2);
		frmLoginSystem.getContentPane().add(separator_1);
	}
}
