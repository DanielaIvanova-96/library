package tuv.lib.models.dao;

import tuv.lib.models.Book;
import tuv.lib.models.DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class BookDAOImpl implements BookDAO {

	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addBook(Book b) {
		Connection con = DBConnector.getConnection();
		Statement st;
		try {
			st = con.createStatement();

			String query = "INSERT INTO libr.genres VALUES (default, '" + b.getGenre() + "') "
					+ "ON DUPLICATE KEY UPDATE genre_name = '" + b.getGenre() + "';";
			st.executeUpdate(query);

			query = "INSERT INTO libr.books_info VALUES (default, '" + b.getName() + "', '" + b.getName() + "', "
					+ "(SELECT genre_id FROM libr.genres WHERE genre_name = '" + b.getGenre() + "'))"
					+ " ON DUPLICATE KEY UPDATE book_info_inv_num = '" + b.getInvNumber() + "';";

			st.executeUpdate(query);

			for (int i = 0; i < b.getAuthors().size(); i++) {
				query = "INSERT INTO libr.authors VALUES (default, '" + b.getAuthors().get(i) + "') "
						+ "ON DUPLICATE KEY UPDATE author_name = '" + b.getAuthors().get(i) + "';";
				st.executeUpdate(query);

				query = "INSERT INTO libr.authors_books VALUES ((SELECT authors.AUTHOR_ID from authors WHERE AUTHOR_NAME = '"
						+ b.getAuthors().get(i) + "'), "
						+ "(SELECT books_info.BOOK_INFO_ID FROM books_info WHERE BOOK_INFO_NAME = '" + b.getName()
						+ "'));";
				st.executeUpdate(query);
			}

			query = "INSERT INTO libr.books VALUES (default , 0, (SELECT books_info.BOOK_INFO_ID FROM books_info WHERE BOOK_INFO_NAME = '"
					+ b.getName() + "'));";
			st.executeUpdate(query);
			
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Book is successfully inserted! ");
			alert.showAndWait();
			
		} catch (SQLException e) {
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Book could not be inserted! ");
			alert.showAndWait();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateBook(Book b) {
		// TODO Auto-generated method stub

	}

	public void removeBook(int id) {
		// TODO Auto-generated method stub
	}

	public List<Book> getBookByName(String name) {
		Connection con = DBConnector.getConnection();
		try {

			Statement st = con.createStatement();
			String query = "SELECT BOOK_ID, BOOK_INFO_NAME, AUTHOR_NAME, GENRE_NAME, BOOK_INFO_INV_NUM, BOOK_CONDITION"
					+ " FROM libr.books_info"
					+ " JOIN  authors_books ON books_info.BOOK_INFO_ID = authors_books.BOOK_INFO_ID"
					+ " JOIN authors ON authors.AUTHOR_ID = authors_books.AUTHOR_ID"
					+ " JOIN genres ON genres.GENRE_ID = books_info.GENRE_ID"
					+ " JOIN books ON books.BOOK_INFO_ID = books_info.BOOK_INFO_ID WHERE BOOK_INFO_NAME LIKE '%" + name
					+ "%' ;";

			ResultSet rs = st.executeQuery(query);

			Map<Integer, Book> books = new HashMap<Integer, Book>();
			String res_name, res_author, res_genre, res_inv_num;
			int res_id, res_condition;

			while (rs.next()) {
				res_id = rs.getInt("BOOK_ID");
				res_name = rs.getString("BOOK_INFO_NAME");
				res_author = rs.getString("AUTHOR_NAME");
				res_genre = rs.getString("GENRE_NAME");
				res_inv_num = rs.getString("BOOK_INFO_INV_NUM");
				res_condition = rs.getInt("BOOK_CONDITION");

				if (books.containsKey(res_id)) {
					books.get(res_id).getAuthors().add(res_author);
				} else {
					List<String> authors = new ArrayList<String>();
					authors.add(res_author);
					books.put(res_id, new Book(res_name, authors, res_genre, res_inv_num, res_condition));
				}

			}

			return new ArrayList<Book>(books.values());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<Book> getBookByGenre(String genre) {
		Connection con = DBConnector.getConnection();
		try {

			Statement st = con.createStatement();
			String query = "SELECT BOOK_ID, BOOK_INFO_NAME, AUTHOR_NAME, GENRE_NAME, BOOK_INFO_INV_NUM, BOOK_CONDITION"
					+ " FROM libr.books_info"
					+ " JOIN  authors_books ON books_info.BOOK_INFO_ID = authors_books.BOOK_INFO_ID"
					+ " JOIN authors ON authors.AUTHOR_ID = authors_books.AUTHOR_ID"
					+ " JOIN genres ON genres.GENRE_ID = books_info.GENRE_ID"
					+ " JOIN books ON books.BOOK_INFO_ID = books_info.BOOK_INFO_ID" + " WHERE GENRE_NAME LIKE '%"
					+ genre + "%' ;";

			ResultSet rs = st.executeQuery(query);

			Map<Integer, Book> books = new HashMap<Integer, Book>();
			String res_name, res_author, res_genre, res_inv_num;
			int res_id, res_condition;

			while (rs.next()) {
				res_id = rs.getInt("BOOK_ID");
				res_name = rs.getString("BOOK_INFO_NAME");
				res_author = rs.getString("AUTHOR_NAME");
				res_genre = rs.getString("GENRE_NAME");
				res_inv_num = rs.getString("BOOK_INFO_INV_NUM");
				res_condition = rs.getInt("BOOK_CONDITION");

				if (books.containsKey(res_id)) {
					books.get(res_id).getAuthors().add(res_author);
				} else {
					List<String> authors = new ArrayList<String>();
					authors.add(res_author);
					books.put(res_id, new Book(res_name, authors, res_genre, res_inv_num, res_condition));
				}

			}

			return new ArrayList<Book>(books.values());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<Book> getBookByAuthor(String author) {
		Connection con = DBConnector.getConnection();
		try {

			Statement st = con.createStatement();
			String query = "SELECT BOOK_ID, BOOK_INFO_NAME, AUTHOR_NAME, GENRE_NAME, BOOK_INFO_INV_NUM, BOOK_CONDITION"
					+ " FROM libr.books_info"
					+ " JOIN  authors_books ON books_info.BOOK_INFO_ID = authors_books.BOOK_INFO_ID"
					+ " JOIN authors ON authors.AUTHOR_ID = authors_books.AUTHOR_ID"
					+ " JOIN genres ON genres.GENRE_ID = books_info.GENRE_ID"
					+ " JOIN books ON books.BOOK_INFO_ID = books_info.BOOK_INFO_ID" + " WHERE AUTHOR_NAME LIKE '%"
					+ author + "%' ;";

			ResultSet rs = st.executeQuery(query);

			Map<Integer, Book> books = new HashMap<Integer, Book>();
			String res_name, res_author, res_genre, res_inv_num;
			int res_id, res_condition;

			while (rs.next()) {
				res_id = rs.getInt("BOOK_ID");
				res_name = rs.getString("BOOK_INFO_NAME");
				res_author = rs.getString("AUTHOR_NAME");
				res_genre = rs.getString("GENRE_NAME");
				res_inv_num = rs.getString("BOOK_INFO_INV_NUM");
				res_condition = rs.getInt("BOOK_CONDITION");

				if (books.containsKey(res_id)) {
					books.get(res_id).getAuthors().add(res_author);
				} else {
					List<String> authors = new ArrayList<String>();
					authors.add(res_author);
					books.put(res_id, new Book(res_name, authors, res_genre, res_inv_num, res_condition));
				}

			}

			return new ArrayList<Book>(books.values());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<Book> getBookByCondition(int condition) {
		Connection con = DBConnector.getConnection();
		try {

			Statement st = con.createStatement();
			String query = "SELECT BOOK_ID, BOOK_INFO_NAME, AUTHOR_NAME, GENRE_NAME, BOOK_INFO_INV_NUM, BOOK_CONDITION"
					+ " FROM libr.books_info"
					+ " JOIN  authors_books ON books_info.BOOK_INFO_ID = authors_books.BOOK_INFO_ID"
					+ " JOIN authors ON authors.AUTHOR_ID = authors_books.AUTHOR_ID"
					+ " JOIN genres ON genres.GENRE_ID = books_info.GENRE_ID"
					+ " JOIN books ON books.BOOK_INFO_ID = books_info.BOOK_INFO_ID" + " WHERE BOOK_CONDITION < "
					+ condition + " ;";

			ResultSet rs = st.executeQuery(query);

			Map<Integer, Book> books = new HashMap<Integer, Book>();
			String res_name, res_author, res_genre, res_inv_num;
			int res_id, res_condition;

			while (rs.next()) {
				res_id = rs.getInt("BOOK_ID");
				res_name = rs.getString("BOOK_INFO_NAME");
				res_author = rs.getString("AUTHOR_NAME");
				res_genre = rs.getString("GENRE_NAME");
				res_inv_num = rs.getString("BOOK_INFO_INV_NUM");
				res_condition = rs.getInt("BOOK_CONDITION");

				if (books.containsKey(res_id)) {
					books.get(res_id).getAuthors().add(res_author);
				} else {
					List<String> authors = new ArrayList<String>();
					authors.add(res_author);
					books.put(res_id, new Book(res_name, authors, res_genre, res_inv_num, res_condition));
				}

			}

			return new ArrayList<Book>(books.values());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
