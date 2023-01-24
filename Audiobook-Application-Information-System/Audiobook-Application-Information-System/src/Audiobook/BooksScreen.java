package Audiobook;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class BooksScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	public Database database = new Database();
	private JPanel contentPane;
	private JTextField searchField;
	public String ssn;
	ArrayList<String> list = new ArrayList<String>();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BooksScreen(String ssn) throws Exception{
		setTitle("BookNook");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BooksScreen.class.getResource("/icons/audio-book (4).png")));
		
		list = database.listCategories();
		list.add(0, "All");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 40, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> categoryComboBox = new JComboBox(list.toArray(new String[list.size()]));
		categoryComboBox.setForeground(new Color(236, 65, 0));
		categoryComboBox.setBackground(new Color(40, 40, 40));
		categoryComboBox.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		categoryComboBox.setBounds(25, 25, 200, 30);
		contentPane.add(categoryComboBox);
		
		DefaultListModel<String> books = new DefaultListModel<String>();
		books = database.allBooks();
		
		JComboBox timeComboBox = new JComboBox(new String[]{"Choose hour length","0-2","2-4","4-6","6-8","8-10","10-12","12-14","14-16","16-18","18-20","20-22","22-24"});
		timeComboBox.setBackground(new Color(40, 40, 40));
		timeComboBox.setForeground(new Color(236, 65, 0));
		timeComboBox.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		timeComboBox.setBounds(232, 25, 200, 30);
		contentPane.add(timeComboBox);
		
		JList listBooks = new JList();
		listBooks.setModel(books);
		
		listBooks.setForeground(new Color(255, 255, 255));
		listBooks.setBackground(new Color(88, 88, 88));
		listBooks.setBounds(25, 84, 937, 424);
		contentPane.add(listBooks);
		listBooks.setFont(new Font("consolas", Font.BOLD, 15));
		
		JButton btnFilter = new JButton("FILTER");
		btnFilter.setIcon(new ImageIcon(BooksScreen.class.getResource("/icons/filter.png")));
		btnFilter.setBackground(new Color(236, 65, 0));
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Record> books = new ArrayList<Record>();
				Control control = new Control();
				try {
					books = database.filterBooks(list.get(categoryComboBox.getSelectedIndex()), timeComboBox.getSelectedIndex());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(books == null) {
					JOptionPane.showMessageDialog(null,"There are no books with the features you are looking for!");
				}else {
					DefaultListModel<String> filterBooks = new DefaultListModel<String>();
					for(Record r : books) {
						String name = r.bookName;
						String author = r.author;
						String narrator = r.narrator;
						String category = r.category;
						int time = r.time;
						String line  =   control.stringFormat(name, author, narrator, category, time);
						filterBooks.addElement(line);
					}
					listBooks.setModel(filterBooks);
				}

			}
		});
		
		

		btnFilter.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		btnFilter.setBounds(442, 24, 96, 30);
		contentPane.add(btnFilter);
		
		searchField = new JTextField();
		searchField.setForeground(new Color(255, 255, 255));
		searchField.setBackground(new Color(40, 40, 40));
		searchField.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		searchField.setBounds(724, 25, 200, 30);
		contentPane.add(searchField);
		searchField.setColumns(10);
		

		JButton btnSearch = new JButton();
		btnSearch.setBackground(new Color(236, 65, 0));
		btnSearch.setIcon(new ImageIcon(BooksScreen.class.getResource("/icons/search (1).png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookName = null;
				ArrayList<Record> books = new ArrayList<Record>();
				Control control = new Control();
				bookName = searchField.getText();
				if(bookName.compareTo("") == 0) {
					JOptionPane.showMessageDialog(null,"You must first enter a book name!");
				}else {
					try {
						//bookName = control.firstCharacterUpper(bookName);
						System.out.println(bookName);
						books = database.searchBook(bookName);
						if(books == null) {
							JOptionPane.showMessageDialog(null,"No results were found matching your search!");
						}else {
							DefaultListModel<String> searchBooks = new DefaultListModel<String>();
							for(Record r : books) {
								String name = r.bookName;
								String author = r.author;
								String narrator = r.narrator;
								String category = r.category;
								int time = r.time;
								String line  =   control.stringFormat(name, author, narrator, category, time);
								searchBooks.addElement(line);
							}
							listBooks.setModel(searchBooks);
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnSearch.setBounds(931, 25, 31, 30);
		contentPane.add(btnSearch);
		

		
		JButton btnNewButton = new JButton("GO BACK");
		btnNewButton.setBackground(new Color(236, 65, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		btnNewButton.setBounds(25, 518, 114, 30);
		contentPane.add(btnNewButton);
		
		JButton btnBuy = new JButton("RENT");
		btnBuy.setBackground(new Color(236, 65, 0));
		btnBuy.setForeground(new Color(255, 255, 255));
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentScreen rentScreen;
				Control control = new Control();
				try {
					if(listBooks.getSelectedValue() != null) {
						String name = control.getName(listBooks.getSelectedValue().toString());
						rentScreen = new RentScreen(ssn,database.findBookId(name));
						rentScreen.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null,"Select a book first!");
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnBuy.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		btnBuy.setBounds(848, 518, 114, 30);
		contentPane.add(btnBuy);
		
		JLabel lblNewLabel = new JLabel("Book Name                                                  Author                                                 Narrator                                  Category                       Time(h)");
		lblNewLabel.setForeground(new Color(236, 65, 0));
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblNewLabel.setBounds(25, 65, 935, 21);
		contentPane.add(lblNewLabel);
	}
}
