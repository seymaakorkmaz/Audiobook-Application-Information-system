package Audiobook;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MainScreen() {
		setTitle("BookNook");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainScreen.class.getResource("/icons/audio-book (4).png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 40, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSignUp = new JButton("SIGN UP");
		btnSignUp.setForeground(new Color(255, 255, 255));
		btnSignUp.setBackground(new Color(236, 65, 0));
		btnSignUp.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sign_upScreen sign_up;
				try {
					sign_up = new Sign_upScreen();
					setVisible(false);
					sign_up.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		btnSignUp.setBounds(305, 298, 175, 60);
		contentPane.add(btnSignUp);
		
		JButton btnSignIn = new JButton("SIGN IN");
		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setBackground(new Color(236, 65, 0));
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sign_inScreen sign_in;
				try {
					sign_in = new Sign_inScreen();
					setVisible(false);
					sign_in.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnSignIn.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		btnSignIn.setBounds(305, 219, 175, 60);
		contentPane.add(btnSignIn);
		
		JLabel lblWelcome = new JLabel("   WELCOME TO BOOKNOOK");
		lblWelcome.setIcon(new ImageIcon(MainScreen.class.getResource("/icons/audio-book (2).png")));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(new Color(236, 65, 0));
		lblWelcome.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
		lblWelcome.setBounds(70, 75, 640, 101);
		contentPane.add(lblWelcome);
	}

}
