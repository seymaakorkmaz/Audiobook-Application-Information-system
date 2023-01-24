package Audiobook;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;

public class MenuScreen extends JFrame {
	public String ssn;
	private static final long serialVersionUID = 1L;
	public Database database = new Database();
	private JPanel contentPane;

	public MenuScreen(String ssn) throws Exception {
		setTitle("BookNook");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuScreen.class.getResource("/icons/audio-book (4).png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 40, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBooks = new JButton("BOOKS");
		btnBooks.setBackground(new Color(236, 65, 0));
		btnBooks.setForeground(new Color(255, 255, 255));
		btnBooks.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		btnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BooksScreen booksScreen;
				try {
					booksScreen = new BooksScreen(ssn);
					setVisible(false);
					booksScreen.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnBooks.setBounds(320, 160, 300, 50);
		contentPane.add(btnBooks);
		
		JButton btnBalance = new JButton("BALANCE");
		btnBalance.setForeground(new Color(255, 255, 255));
		btnBalance.setBackground(new Color(236, 65, 0));
		btnBalance.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		btnBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BalanceScreen balanceScreen;
				try {
					balanceScreen = new BalanceScreen(ssn);
					setVisible(false);
					balanceScreen.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnBalance.setBounds(320, 300, 300, 50);
		contentPane.add(btnBalance);
		
		JButton btnMyBooks = new JButton("MY BOOKS");
		btnMyBooks.setForeground(new Color(255, 255, 255));
		btnMyBooks.setBackground(new Color(236, 65, 0));
		btnMyBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyBooksScreen myBooksScreen;
				try {
					myBooksScreen = new MyBooksScreen(ssn);
					setVisible(false);
					myBooksScreen.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnMyBooks.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		btnMyBooks.setBounds(320, 230, 300, 50);
		contentPane.add(btnMyBooks);
		
		JLabel lblWelcome = new JLabel("WELCOME, " + database.getName(ssn).toUpperCase());
		lblWelcome.setForeground(new Color(236, 65, 0));
		lblWelcome.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblWelcome.setBounds(42, 68, 318, 40);
		contentPane.add(lblWelcome);
		
		JLabel lblCurBal = new JLabel("CURRENT BALANCE: " + String.format("%d", database.getBalance(ssn)) + " TL  ");
		lblCurBal.setForeground(new Color(236, 65, 0));
		lblCurBal.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblCurBal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurBal.setBounds(508, 68, 443, 40);
		contentPane.add(lblCurBal);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setBackground(new Color(236, 65, 0));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainScreen mainScreen;
				try {
					mainScreen = new MainScreen();
					setVisible(false);
					mainScreen.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnLogOut.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		btnLogOut.setBounds(320, 370, 300, 50);
		contentPane.add(btnLogOut);
		
		JButton btnDeleteUser = new JButton("DELETE ACCOUNT");
		btnDeleteUser.setForeground(new Color(255, 255, 255));
		btnDeleteUser.setBackground(new Color(236, 65, 0));
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, 
				"Are you sure you want to delete your account?", "Confirmation Message Box", JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					try {
						database.deleteAccount(ssn);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					MainScreen mainScreen;
					try {
						mainScreen = new MainScreen();
						setVisible(false);
						mainScreen.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					dispose();;
				}
			}
		});
		btnDeleteUser.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		btnDeleteUser.setBounds(320, 438, 300, 50);
		contentPane.add(btnDeleteUser);
		
		JLabel lblNewLabel = new JLabel("  BOOKNOOK");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MenuScreen.class.getResource("/icons/audio-book (3).png")));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 35));
		lblNewLabel.setBounds(320, 27, 300, 40);
		contentPane.add(lblNewLabel);
	}
}