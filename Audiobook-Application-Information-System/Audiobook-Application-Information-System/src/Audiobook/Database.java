package Audiobook;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
public class Database {
    public Connection connection;
	public Database() throws Exception{
    	SQLConnection SQLconnection = new SQLConnection();
    	connection = SQLconnection.getConnection();
    }
	
	
	
	public String searchUser(String userName) throws Exception{
		String query = "SELECT ssn FROM users WHERE nickname = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, userName);
		ResultSet result = pstmt.executeQuery();
		
		if(result.next()) 
			return result.getString(1);
		else
			return null;
	}
	
	public String userLogin(String userName, String password) throws Exception{
		String ssn, psswd;
		String query = "SELECT ssn, psswd FROM users WHERE nickname = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, userName);
		ResultSet result = pstmt.executeQuery();
		
		if(!result.next()) {
			return null; 
		}else {
			psswd = result.getString(2);
			if(psswd.compareTo(password)==0) {
				ssn = result.getString(1);
				return ssn;
			}
		}
		return null;
		
	}
	
	public String userRegister(String fname,String lname,String userName,String mail,String psswd, java.sql.Date date1) throws Exception {
		
		
		if(searchUser(userName) == null) {
			String query2 = "INSERT INTO users (ssn, fname, lname, nickname, mail, psswd, bdate) VALUES (nextval('userseq'), ?, ? ,? ,?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(query2);
		    
			stmt.setString(1,fname);
			stmt.setString(2,lname);
			stmt.setString(3,userName);
			stmt.setString(4,mail);
			stmt.setString(5,psswd);
			stmt.setDate(6, date1);
			stmt.executeUpdate();
			
			return searchUser(userName);
		}else {
			return null;
		}
		
	}
	
	public String rental(String user_ssn, String bookid, int dayLimit) throws Exception{
		CallableStatement cstmt = connection.prepareCall("{? = call calculateprice(?,?)}");
		cstmt.registerOutParameter(1, Types.INTEGER);
		cstmt.setInt(2, dayLimit);
		cstmt.setString(3, bookid);
		cstmt.execute();
		int price = cstmt.getInt(1);
		long millis=System.currentTimeMillis();  
	    java.sql.Date date = new java.sql.Date(millis);  

		String query2 = "INSERT INTO rental (id, ussn, bookid, rentday, daylimit, price) VALUES (nextval('rentalseq'), ?, ? ,? ,?, ?)";
		PreparedStatement stmt = connection.prepareStatement(query2);
		stmt.setString(1,user_ssn);
		stmt.setString(2,bookid);
		stmt.setDate(3,date);
		stmt.setInt(4,dayLimit);
		stmt.setInt(5,price);
		stmt.executeUpdate(); 
		if(stmt.getWarnings() != null){
			return stmt.getWarnings().getMessage();
		}
		return null;
	}
	
	public ArrayList<String> listCategories() throws Exception{
		ArrayList<String> list = new ArrayList<String>();
		String query = "select distinct category from  books order by category";
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet result = pstmt.executeQuery();
	
		while(result.next()) {
			list.add(result.getString(1));
		}
		return list;
	}
	
	public void deleteAccount(String user_ssn) throws Exception{
		String query = "delete from users where ssn = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, user_ssn);
		pstmt.executeUpdate();
	}
	
	public ArrayList<Record>  searchBook(String bookName) throws Exception{
		CallableStatement cstmt = connection.prepareCall("{? = call SearchBook(?)}");
		cstmt.registerOutParameter(1,2003);
		cstmt.setString(2, bookName);
		cstmt.execute();

		java.sql.Array type = (java.sql.Array) cstmt.getObject(1);
		if(type == null) {
			return null;
		}
		
		ResultSet rs = type.getResultSet();
		ArrayList<Record> records = new ArrayList<Record>();

		while(rs.next()) {
			Record rec = new Record();
			String line = rs.getString(2);
			String line2 = line.substring(1, line.length()-1);
			String[] arrStr = line2.split(",");
			rec.bookName = arrStr[0];
			rec.author = arrStr[1].substring(1, arrStr[1].length()-1);
			rec.narrator = arrStr[2] + " " +arrStr[3];
			rec.time = Integer.parseInt(arrStr[4]);
			rec.category = arrStr[5];
			
			records.add(rec);
		}
		
		return records;
	}

	public String favCategory(String user_ssn) throws SQLException {
		CallableStatement cstmt = connection.prepareCall("{call favCategory(?,?,?)}");
		cstmt.registerOutParameter(2, Types.INTEGER);
		cstmt.registerOutParameter(3, Types.VARCHAR);
		cstmt.setString(1,user_ssn);
		cstmt.execute();
		String category = cstmt.getString(3);
		return category;
		
	}
	
	public void increaseBalance(int amonut,String user_ssn) throws SQLException{
		CallableStatement cstmt = connection.prepareCall("{call increaseBalance(?,?)}");
		cstmt.setInt(1, amonut);
		cstmt.setString(2,user_ssn);
		cstmt.execute();
	}
	
	public ArrayList<Record> callView(String user_ssn) throws SQLException{
		ArrayList<Record> myLibrary = new ArrayList<>();
		String query = "select * from userLibrary where ssn = ? "
					 + "except select name,author,fname,lname,category,time,ssn,u.id "
				     + "from userLibrary u, rental where bookid = u.id AND isexpired = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, user_ssn);
		pstmt.setInt(2, 1);
		ResultSet result = pstmt.executeQuery();
		
		while(result.next()) {
			Record rec = new Record();
			rec.bookName =  result.getString(1);
			rec.author =  result.getString(2);
			String fname = result.getString(3);
			String fullname = fname +" "+ result.getString(4);
			rec.narrator = fullname;
			rec.category =  result.getString(5);
			rec.time =  result.getInt(6);
			myLibrary.add(rec);
		}
		
		return myLibrary;
	}
	
	public ArrayList<Record> filterBooks(String category, int hour) throws Exception{
		System.out.println(category+hour);
		CallableStatement cstmt = connection.prepareCall("{? = call filterBooks(?,?)}");
		cstmt.registerOutParameter(1,2003);
		cstmt.setString(2,category);
		cstmt.setInt(3,hour);
		cstmt.execute();
		
		java.sql.Array type = (java.sql.Array) cstmt.getObject(1);
		if(type == null) {
			return null;
		}
		
		ResultSet rs = type.getResultSet();
		ArrayList<Record> books = new ArrayList<Record>();

		while(rs.next()) {
			Record rec = new Record();
			String line = rs.getString(2);
			String line2 = line.substring(1, line.length()-1);
			String[] arrStr = line2.split(",");
			rec.bookName = arrStr[0];
			rec.author = arrStr[1].substring(1, arrStr[1].length()-1);
			rec.narrator = arrStr[2] + " " +arrStr[3];
			rec.time = Integer.parseInt(arrStr[4]);
			rec.category = arrStr[5];
			books.add(rec);
		}
		
		return books;
	}
	
	
	public int getBalance(String ssn) throws Exception{
		String query = "select balance from users where ssn = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, ssn);
		ResultSet result = pstmt.executeQuery();
		
		while(result.next()) {
			return result.getInt(1);
		}
		return 0;
	}
	
	public String getName(String ssn) throws Exception{
		String query = "select fname,lname from users where ssn = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, ssn);
		ResultSet result = pstmt.executeQuery();
		
		while(result.next()) {
			return result.getString(1) + " " + result.getString(2);
		}
		return null;
	}
	
	
	public DefaultListModel<String> allBooks() throws Exception{
		
		String query = "SELECT name, author, fname, lname, time, category FROM books b, narrates n, narrator WHERE b.id = bookid AND nssn = ssn;";
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet result = pstmt.executeQuery();

		DefaultListModel<String> books = new DefaultListModel<String>();
		Control control = new Control();
		while(result.next()) {
			
			String name = result.getString(1);
			String author = result.getString(2);
			String narrator = result.getString(3) + " " + result.getString(4);
			Integer time = result.getInt(5);
			String category = result.getString(6);
		
			String line  =   control.stringFormat(name, author, narrator, category, time);
			books.addElement(line);
			
		}
		
		return books;
	}
	
	public String findBookId(String bookName) throws Exception{
		String query = "SELECT id FROM books WHERE name = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, bookName);
		ResultSet result = pstmt.executeQuery();
		
		while(result.next()) {
			return result.getString(1);
		}
		return null;
	}
	
	public Record getRentBookInfo (String id) throws SQLException {
		String query = "SELECT name, author, fname, lname, time, dailyprice, category, audience FROM books b, narrates n, narrator WHERE b.id = ? AND nssn = ssn AND b.id = bookid";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, id);
		ResultSet result = pstmt.executeQuery();
		Record record = new Record();
		while(result.next()) {
			record.bookName = result.getString(1);
			record.author = result.getString(2);
			record.narrator = result.getString(3) + " " + result.getString(4);
			record.time = result.getInt(5);
			record.dailyPrice = result.getInt(6);
			record.category = result.getString(7);
			record.narratorAudience = result.getInt(8);
		}
		return record;
	}
	
	public void isExpired() throws SQLException {
		String query = "select rentday, daylimit, id from rental";
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet result = pstmt.executeQuery();
		
		while(result.next()) {
			Date dt = result.getDate(1);
			int limit = result.getInt(2);
			String id = result.getString(3);
			
			if(LocalDate.now().compareTo(dt.toLocalDate().plusDays(limit)) == 0) {
				setExpired(id);
			}
		}
	}
	
	public void setExpired(String id) throws SQLException {
		String query = "update rental set isexpired = 1 where id = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1,id);
		pstmt.executeUpdate();

	}
	
}