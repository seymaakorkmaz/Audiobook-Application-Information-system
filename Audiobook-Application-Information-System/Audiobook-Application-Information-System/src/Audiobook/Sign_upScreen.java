package Audiobook;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class Sign_upScreen extends JFrame {
	private String ssn;
	
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	Database database = new Database();
	private static final long serialVersionUID = 1L;
	private JTextField email;
	private JTextField name;
	private JTextField password;
	private JTextField surname;
	private JTextField username;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Sign_upScreen() throws Exception{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sign_upScreen.class.getResource("/icons/audio-book (4).png")));
		getContentPane().setBackground(new Color(40, 40, 40));
		setTitle("BookNook");
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 800, 500);
		getContentPane().setLayout(null);
		

		JLabel lblClentRegster = new JLabel("  SIGN UP");
		lblClentRegster.setIcon(new ImageIcon(Sign_upScreen.class.getResource("/icons/audio-book (3).png")));
		lblClentRegster.setForeground(new Color(236, 65, 0));
		lblClentRegster.setHorizontalAlignment(SwingConstants.CENTER);
		lblClentRegster.setFont(new Font("Tw Cen MT", Font.BOLD, 35));
		lblClentRegster.setBounds(192, 48, 395, 62);
		getContentPane().add(lblClentRegster);
		
		JLabel lblName = new JLabel("NAME:");
		lblName.setForeground(new Color(236, 65, 0));
		lblName.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(192, 134, 104, 25);
		getContentPane().add(lblName);
		
		JLabel lblSurname = new JLabel("SURNAME:");
		lblSurname.setForeground(new Color(236, 65, 0));
		lblSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurname.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		lblSurname.setBounds(192, 166, 104, 25);
		getContentPane().add(lblSurname);
		
		JLabel lblBirthday = new JLabel("BIRTHDAY:");
		lblBirthday.setForeground(new Color(236, 65, 0));
		lblBirthday.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirthday.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		lblBirthday.setBounds(192, 201, 104, 25);
		getContentPane().add(lblBirthday);
		
		JLabel lblEmail = new JLabel("E-MAIL:");
		lblEmail.setForeground(new Color(236, 65, 0));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		lblEmail.setBounds(192, 240, 104, 25);
		getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setForeground(new Color(236, 65, 0));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		lblPassword.setBounds(192, 311, 104, 25);
		getContentPane().add(lblPassword);

		
		username = new JTextField();
		username.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		username.setColumns(10);
		username.setBounds(306, 272, 215, 25);
		getContentPane().add(username);
		
		JLabel lblUsername = new JLabel("USERNAME:");
		lblUsername.setForeground(new Color(236, 65, 0));
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		lblUsername.setBounds(192, 273, 104, 25);
		getContentPane().add(lblUsername);
		
		
		email = new JTextField();
		email.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		email.setBounds(306, 239, 215, 25);
		getContentPane().add(email);
		email.setColumns(10);
		
		name = new JTextField();
		name.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		name.setColumns(10);
		name.setBounds(306, 133, 215, 25);
		getContentPane().add(name);
		
		
		surname = new JTextField();
		surname.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		surname.setColumns(10);
		surname.setBounds(306, 165, 215, 25);
		getContentPane().add(surname);
		
		password = new JPasswordField();
		password.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		password.setColumns(10);
		password.setBounds(306, 305, 215, 25);
		getContentPane().add(password);
		
		JLabel hata_email = new JLabel("e-mail is incorrect");
		hata_email.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		hata_email.setForeground(new Color(255, 255, 255));
		hata_email.setHorizontalAlignment(SwingConstants.LEFT);
		hata_email.setBounds(526, 243, 200, 21);
		getContentPane().add(hata_email);
		hata_email.setVisible(false);
		
		
		JComboBox comboBoxDay = new JComboBox();
		comboBoxDay.setBackground(new Color(255, 255, 255));
		comboBoxDay.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		Object[] days = {"day",1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		Object[] months = {"month",1,2,3,4,5,6,7,8,9,10,11,12};
		Object[] years = {"year",2020,2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009,2008,2007,2006,2005,2004,
				2003,2002,2001,2000,1999,1998,1997,1996,1995,1994,1993,1992,1991,1990,1989,1988,1987,1986,1985,1984,1983,1982,1981,1980,1979,1978,1977,1976,1975,1974,1973,1972,1971,1970};
		comboBoxDay.setModel(new DefaultComboBoxModel(days));
		comboBoxDay.setBounds(306, 198, 62, 32);
		getContentPane().add(comboBoxDay);
		
		JComboBox comboMonth = new JComboBox();
		comboMonth.setBackground(new Color(255, 255, 255));
		comboMonth.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		comboMonth.setBounds(366, 198, 78, 32);
		comboMonth.setModel(new DefaultComboBoxModel(months));
		getContentPane().add(comboMonth);
		
		JComboBox comboYear = new JComboBox();
		comboYear.setBackground(new Color(255, 255, 255));
		comboYear.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		comboYear.setBounds(443, 198, 78, 32);
		comboYear.setModel(new DefaultComboBoxModel(years));
		getContentPane().add(comboYear);
		
		Control control = new Control();
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(236, 65, 0));
		btnRegister.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		btnRegister.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
				try {
					MenuScreen menu;
					
					int hata = 0;
					
					if(control.isStringEmpty(name.getText())) {
						hata = 1;
					}
					
					if(control.isStringEmpty(surname.getText())) {
						hata = 	1;
					}
					
					if(control.isStringEmpty(email.getText())) {
						hata = 1;
					}else if(!control.isEMailValid(email.getText())) {
						hata_email.setVisible(true);	
						email.setText("");
						hata = 2;
					}
					
					if(control.isStringEmpty(password.getText())) {
						hata = 1;
					}
					
					if(comboYear.getSelectedIndex() == 0 ||  comboMonth.getSelectedIndex() == 0 ||   comboBoxDay.getSelectedIndex() == 20) {
						hata =1;
					}
					
				   if(hata == 0){
						String date = 2021 - comboYear.getSelectedIndex() + "-" + comboMonth.getSelectedIndex() + "-" + comboBoxDay.getSelectedIndex();
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
						java.util.Date date1 = sdf.parse(date);
						 
						java.sql.Date sqlDate = new Date(date1.getTime()); 
						ssn = database.userRegister(name.getText(), surname.getText(),username.getText() , email.getText() , password.getText(),sqlDate);
						if(ssn != null) {
							JOptionPane.showMessageDialog(null,"Registration successful!");
							try {
								menu = new MenuScreen(ssn);
								setVisible(false);
								menu.setVisible(true);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							dispose();
							
						}else
							JOptionPane.showMessageDialog(null,"A user with this username already exists");
						    username.setText("");
					}else if(hata == 1){
						JOptionPane.showMessageDialog(null,"Please fill all the fields!!!");
					}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
					
			}
		});
		
		btnRegister.setBounds(309, 368, 175, 60);
		getContentPane().add(btnRegister);
		
		JButton btnGoBack = new JButton("");
		btnGoBack.setBackground(new Color(255, 255, 255));
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainScreen mainScreen;
				try {
					mainScreen = new MainScreen();
					mainScreen.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
			}
		});
		btnGoBack.setIcon(new ImageIcon(Sign_upScreen.class.getResource("/icons/arrow.png")));
		btnGoBack.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		btnGoBack.setBounds(10, 10, 44, 30);
		getContentPane().add(btnGoBack);
		

		
		

	}
}
