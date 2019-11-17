package tuv.lib.models.dao;

import tuv.lib.models.Book;
import tuv.lib.models.DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDAOImpl implements BookDAO {

	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addBook(Book b) {
		Connection con = DBConnector.getConnection();
//		Statement st = con.createStatement();
//
//		String query = "INSERT INTO libr.genres VALUES (default, '" + genre + "') "
//				+ "ON DUPLICATE KEY UPDATE genre_name = '" + genre + "';";
//		st.executeUpdate(query);
//
//		query = "INSERT INTO libr.books_info VALUES (default, '" + name + "', '" + number + "', "
//				+ "(SELECT genre_id FROM libr.genres WHERE genre_name = '" + genre + "'))"
//				+ " ON DUPLICATE KEY UPDATE book_info_inv_num = '" + number + "';";
//
//		st.executeUpdate(query);
//		
//		query = "INSERT INTO libr.authors VALUES (default, '" + author + "') "
//				+ "ON DUPLICATE KEY UPDATE author_name = '" + author + "';";
//		st.executeUpdate(query);
//
//		query = "INSERT INTO libr.authors_books VALUES ((SELECT authors.AUTHOR_ID from authors WHERE AUTHOR_NAME = '"
//				+ author + "'), " + "(SELECT books_info.BOOK_INFO_ID FROM books_info WHERE BOOK_INFO_NAME = '" + name
//				+ "'));";
//		st.executeUpdate(query);
//
//		query = "INSERT INTO libr.books VALUES (default , 0, (SELECT books_info.BOOK_INFO_ID FROM books_info WHERE BOOK_INFO_NAME = '"
//				+ name + "'));";
//		st.executeUpdate(query);

	}

	public void updateBook(Book b) {
		// TODO Auto-generated method stub
		
	}

	public void removeBook(int id) {
		// TODO Auto-generated method stub
		
	}

}
