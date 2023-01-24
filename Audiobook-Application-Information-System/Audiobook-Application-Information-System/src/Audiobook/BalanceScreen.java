package Audiobook;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class BalanceScreen extends JFrame {
	
	Database database = new Database();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public String ssn;


	public BalanceScreen(String ssn) throws Exception{
		setIconImage(Toolkit.getDefaultToolkit().getImage(BalanceScreen.class.getResource("/icons/audio-book (4).png")));
		setTitle("BookNook");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 500, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 40, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel balanceLabel = new JLabel("100");
		balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		balanceLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		balanceLabel.setBackground(Color.WHITE);
		balanceLabel.setOpaque(true);
		balanceLabel.setBounds(204, 120, 101, 30);
		contentPane.add(balanceLabel);
		
		
		JLabel lblCurrentBalanceText = new JLabel("CURRENT BALANCE :\r\n");
		lblCurrentBalanceText.setForeground(new Color(255, 255, 255));
		lblCurrentBalanceText.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentBalanceText.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblCurrentBalanceText.setBounds(111, 64, 212, 30);
		contentPane.add(lblCurrentBalanceText);
		
		JLabel lblCurrentBalance = new JLabel(String.format("%d", database.getBalance(ssn)));
		lblCurrentBalance.setForeground(new Color(255, 255, 255));
		lblCurrentBalance.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblCurrentBalance.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrentBalance.setBounds(331, 64, 74, 30);
		contentPane.add(lblCurrentBalance);
		
		JButton btnPlus = new JButton("+");
		btnPlus.setForeground(new Color(255, 255, 255));
		btnPlus.setBackground(new Color(236, 65, 0));
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmp = Integer.parseInt(balanceLabel.getText())+10;
				String tmp2 = tmp + "";
				balanceLabel.setText(tmp2);
			}
		});
		btnPlus.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		btnPlus.setBounds(315, 120, 44, 30);
		contentPane.add(btnPlus);
		
		JButton btnMinus = new JButton("-");
		btnMinus.setBackground(new Color(236, 65, 0));
		btnMinus.setForeground(new Color(255, 255, 255));
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmp = Integer.parseInt(balanceLabel.getText())-10;
				if(tmp >= 0) {
					String tmp2 = tmp + "";
					balanceLabel.setText(tmp2);
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid balance entry!");
				}
				
			}
		});
		btnMinus.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		btnMinus.setBounds(150, 120, 44, 30);
		contentPane.add(btnMinus);
		
		JButton btnNewButton_2 = new JButton("ADD");
		btnNewButton_2.setBackground(new Color(236, 65, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tmp = lblCurrentBalance.getText();
				int tmp2 = Integer.parseInt(tmp) + Integer.parseInt(balanceLabel.getText());
				tmp = tmp2 + "";
				lblCurrentBalance.setText(tmp);
				
				try {
					 database.increaseBalance(Integer.parseInt(balanceLabel.getText()), ssn);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		btnNewButton_2.setBounds(204, 160, 100, 40);
		contentPane.add(btnNewButton_2);
		
		JButton btnGoBack = new JButton("");
		btnGoBack.setBackground(new Color(255, 255, 255));
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuScreen menuScreen;
				try {
					menuScreen = new MenuScreen(ssn);
					menuScreen.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
			}
		});
		btnGoBack.setIcon(new ImageIcon(BalanceScreen.class.getResource("/icons/arrow.png")));
		btnGoBack.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		btnGoBack.setBounds(10, 10, 44, 30);
		contentPane.add(btnGoBack);
		
		
		
		
	}
}
