package Audiobook;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class MyBooksScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	public Database database = new Database();
	private JPanel contentPane;
	public String ssn;
	
	public MyBooksScreen(String ssn) throws Exception{
		setTitle("BookNook");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MyBooksScreen.class.getResource("/icons/audio-book (4).png")));
		
		Control control = new Control();
		ArrayList<Record> books = new ArrayList<Record>();
		books = database.callView(ssn);
		DefaultListModel<String> myBooks = new DefaultListModel<>();
		for(Record r : books) {
			String name = r.bookName;
			String author = r.author;
			String narrator = r.narrator;
			String category = r.category;
			int time = r.time;
			String line  =   control.stringFormat(name, author, narrator, category, time);
			myBooks.addElement(line);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 40, 40));
		contentPane.setForeground(new Color(40, 40, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JList<String> listBooks = new JList<String>();
		listBooks.setBackground(new Color(88, 88, 88));
		listBooks.setForeground(new Color(255, 255, 255));
		listBooks.setFont(new Font("Consolas", Font.BOLD, 15));
		listBooks.setBounds(25, 91, 935, 425);
		listBooks.setModel(myBooks);
		contentPane.add(listBooks);
		
		JLabel lblMyBooks = new JLabel("  MY BOOKS");
		lblMyBooks.setIcon(new ImageIcon(MyBooksScreen.class.getResource("/icons/audio-book (3).png")));
		lblMyBooks.setForeground(new Color(236, 65, 0));
		lblMyBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyBooks.setFont(new Font("Tw Cen MT", Font.BOLD, 30));
		lblMyBooks.setBounds(350, 22, 250, 40);
		contentPane.add(lblMyBooks);
		
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
		btnGoBack.setIcon(new ImageIcon(MyBooksScreen.class.getResource("/icons/arrow.png")));
		btnGoBack.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		btnGoBack.setBounds(25, 22, 44, 30);
		contentPane.add(btnGoBack);
		
		JLabel favCategoryLabel = new JLabel("Your Favorite Category: " + database.favCategory(ssn));
		favCategoryLabel.setForeground(new Color(236, 65, 0));
		favCategoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		favCategoryLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		favCategoryLabel.setBounds(340, 523, 272, 30);
		contentPane.add(favCategoryLabel);
		
		JLabel lblNewLabel = new JLabel("Book Name                                                  Author                                                 Narrator                                  Category                       Time(h)");
		lblNewLabel.setForeground(new Color(236, 65, 0));
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblNewLabel.setBounds(25, 68, 935, 21);
		contentPane.add(lblNewLabel);
	}
}
