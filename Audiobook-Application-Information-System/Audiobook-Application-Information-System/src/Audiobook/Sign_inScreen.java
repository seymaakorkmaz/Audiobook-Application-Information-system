package Audiobook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Sign_inScreen extends JFrame {

	Database database = new Database();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEnterUsernameHere;
	private JTextField textFieldPassword;
	public String ssn;

	public Sign_inScreen() throws Exception{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sign_inScreen.class.getResource("/icons/audio-book (4).png")));
		setTitle("BookNook");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 40, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		btnGoBack.setIcon(new ImageIcon(Sign_inScreen.class.getResource("/icons/arrow.png")));
		btnGoBack.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		btnGoBack.setBounds(10, 10, 44, 30);
		contentPane.add(btnGoBack);
		
		JLabel lblClentLognScreen = new JLabel("  USER LOGIN");
		lblClentLognScreen.setIcon(new ImageIcon(Sign_inScreen.class.getResource("/icons/audio-book (3).png")));
		lblClentLognScreen.setForeground(new Color(236, 65, 0));
		lblClentLognScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblClentLognScreen.setFont(new Font("Tw Cen MT", Font.BOLD, 35));
		lblClentLognScreen.setBounds(146, 82, 474, 69);
		contentPane.add(lblClentLognScreen);
		
		JLabel lblUsername = new JLabel("USERNAME:");
		lblUsername.setForeground(new Color(236, 65, 0));
		lblUsername.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(171, 171, 196, 37);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setForeground(new Color(236, 65, 0));
		lblPassword.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(171, 229, 196, 37);
		contentPane.add(lblPassword);
		
		txtEnterUsernameHere = new JTextField();
		txtEnterUsernameHere.setForeground(new Color(0, 0, 0));
		txtEnterUsernameHere.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		txtEnterUsernameHere.setToolTipText("");
		txtEnterUsernameHere.setBounds(345, 171, 230, 37);
		contentPane.add(txtEnterUsernameHere);
		txtEnterUsernameHere.setColumns(10);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(345, 230, 230, 37);
		contentPane.add(textFieldPassword);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setIcon(new ImageIcon(Sign_inScreen.class.getResource("/icons/login.png")));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuScreen menu;
				try {
					ssn = database.userLogin(txtEnterUsernameHere.getText(),textFieldPassword.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(ssn == null) {
					JOptionPane.showMessageDialog(null,"Login failed!");
					textFieldPassword.setText("");
				}else {
					try {
						menu = new MenuScreen(ssn);
						setVisible(false);
						menu.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					dispose();
				}
				
			}
		});
		btnLogin.setBackground(new Color(236, 65, 0));
		
		btnLogin.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		btnLogin.setBounds(298, 292, 175, 60);
		contentPane.add(btnLogin);
	}
}
