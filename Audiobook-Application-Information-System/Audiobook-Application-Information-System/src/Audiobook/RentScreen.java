package Audiobook;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class RentScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Database database = new Database();
	public String ssn;
	public String bookId;
	public RentScreen(String ssn, String bookId) throws Exception {
		Record book = database.getRentBookInfo(bookId);
		Locale trLoc = new Locale("tr-TR");
		
		setTitle("BookNook");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RentScreen.class.getResource("/icons/audio-book (4).png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 40, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Integer i;

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookName = new JLabel("BOOK NAME: " + book.bookName.toUpperCase(trLoc));
		lblBookName.setForeground(new Color(236, 65, 0));
		lblBookName.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblBookName.setBounds(70, 138, 400, 40);
		contentPane.add(lblBookName);
		
		JLabel lblAuthor = new JLabel("AUTHOR: " + book.author.toUpperCase(trLoc));
		lblAuthor.setForeground(new Color(236, 65, 0));
		lblAuthor.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblAuthor.setBounds(70, 188, 400, 40);
		contentPane.add(lblAuthor);
		
		JLabel lblNarratedBy = new JLabel("NARRATED BY: " + book.narrator.toUpperCase(trLoc));
		lblNarratedBy.setForeground(new Color(236, 65, 0));
		lblNarratedBy.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblNarratedBy.setBounds(70, 288, 400, 40);
		contentPane.add(lblNarratedBy);
		
		int hour  = book.time/60;
   	 	int min = book.time - 60*hour;
   	 	String hour2 = new String();
   	 	if(min != 0) {
   	 		hour2 = hour + "." + min;
   	 	}else {
   	 		hour2 = hour +"";
   	 	}
		
		JLabel lblTime = new JLabel("TIME: " + hour2.toString());
		lblTime.setForeground(new Color(236, 65, 0));
		lblTime.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblTime.setBounds(70, 338, 400, 40);
		contentPane.add(lblTime);
		
		JLabel lblDailyPrice = new JLabel("DAILY PRICE: " + String.format("%d", book.dailyPrice) + " TL");
		lblDailyPrice.setForeground(new Color(236, 65, 0));
		lblDailyPrice.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblDailyPrice.setBounds(70, 388, 400, 40);
		contentPane.add(lblDailyPrice);
		
		JLabel lblCategory = new JLabel("CATEGORY: " + book.category.toUpperCase(trLoc));
		lblCategory.setForeground(new Color(236, 65, 0));
		lblCategory.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblCategory.setBounds(70, 238, 400, 40);
		contentPane.add(lblCategory);
		
		JLabel lblAudience = new JLabel("NARRATOR AUDIENCE: " + String.format("%d", book.narratorAudience));
		lblAudience.setForeground(new Color(236, 65, 0));
		lblAudience.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblAudience.setBounds(70, 438, 400, 40);
		contentPane.add(lblAudience);
		
		JComboBox<Integer> comboDays = new JComboBox<Integer>();
		comboDays.setBackground(new Color(255, 255, 255));
		comboDays.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		comboDays.setBounds(843, 278, 60, 30);
		for(i = 1; i<31; i++)
			comboDays.addItem(i);
		contentPane.add(comboDays);
		
		JLabel lblHowManyDays = new JLabel("How many days do you want to rent?");
		lblHowManyDays.setForeground(new Color(255, 255, 255));
		lblHowManyDays.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblHowManyDays.setBounds(528, 278, 311, 30);
		contentPane.add(lblHowManyDays);
		
		JLabel lblCurrentBalance = new JLabel("CURRENT BALANCE: " + String.format("%d", database.getBalance(ssn)) + " TL  ");
		lblCurrentBalance.setForeground(new Color(236, 65, 0));
		lblCurrentBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentBalance.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblCurrentBalance.setBounds(554, 240, 311, 30);
		contentPane.add(lblCurrentBalance);
		
		JButton btnRent = new JButton("RENT");
        btnRent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String func =database.rental(ssn, bookId, comboDays.getSelectedIndex()+1);
                    if(func != null) {
                        JOptionPane.showMessageDialog(null,func);
                    }
                    else {
                    	lblCurrentBalance.setText("CURRENT BALANCE: " + String.format("%d", database.getBalance(ssn)) + " TL  ");
                    	book.narratorAudience++;
                    	lblAudience.setText("NARRATOR AUDIENCE: " + String.format("%d", book.narratorAudience));
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
		btnRent.setBackground(new Color(236, 65, 0));
		btnRent.setForeground(new Color(255, 255, 255));
		btnRent.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		btnRent.setBounds(662, 330, 90, 48);
		contentPane.add(btnRent);
		
		JButton btnGoBack = new JButton("");
		btnGoBack.setBackground(new Color(255, 255, 255));
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BooksScreen bookScreen;
				try {
					bookScreen = new BooksScreen(ssn);
					bookScreen.setVisible(true);
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
		
		JLabel lblNewLabel = new JLabel("  RENT");
		lblNewLabel.setForeground(new Color(236, 65, 0));
		lblNewLabel.setIcon(new ImageIcon(RentScreen.class.getResource("/icons/audio-book (3).png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 30));
		lblNewLabel.setBounds(381, 39, 218, 48);
		contentPane.add(lblNewLabel);
	}
}
