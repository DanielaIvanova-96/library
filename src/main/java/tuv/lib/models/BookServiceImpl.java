package tuv.lib.models;

import java.util.List;

import tuv.lib.models.dao.BookDAO;
import tuv.lib.models.dao.BookDAOImpl;
import tuv.lib.models.interfaces.BookService;

/**
 * Service used in controllers for communication with the database for the books
 * models
 * 
 * @author Zheni
 *
 */
public class BookServiceImpl implements BookService {
	private BookDAO bookDAO;

	public BookServiceImpl() {
		bookDAO = new BookDAOImpl();
	}

	public void addBook(Book b) {
		bookDAO.addBook(b);
	}

	public void updateBook(Book b) {
		bookDAO.updateBook(b);
	}

	public void removeBook(Book b) {
		bookDAO.removeBook(b);
	}

	public List<Book> getBookbyName(String name) {
		return bookDAO.getBookByName(name);
	}

	public List<Book> getBooksByAuthor(String author) {
		return bookDAO.getBookByAuthor(author);
	}

	public List<Book> getBooksByGenre(String genre) {
		return bookDAO.getBookByGenre(genre);
	}

	public List<Book> getBookByCondition(int condition) {
		return bookDAO.getBookByCondition(condition);
	}
}
