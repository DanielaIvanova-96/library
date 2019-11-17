package tuv.lib.models;

import java.util.List;

import tuv.lib.models.dao.BookDAO;
import tuv.lib.models.dao.BookDAOImpl;
import tuv.lib.models.interfaces.BookService;

/**
 * Service used in controllers for communication with the database for the books models
 * @author Zheni
 *
 */
public class BookServiceImpl implements BookService {
	private BookDAO bookDAO;

	public BookServiceImpl()
	{
		bookDAO = new BookDAOImpl(); 
	}
	
	public void addBook(Book b) {
		bookDAO.addBook(b);
	}

	public void updateBook(Book b) {
		bookDAO.updateBook(b);
	}

	public void removeBook(int id) {
		bookDAO.removeBook(id);
	}

	public List<Book> getBookbyName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> getBooksByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> getBooksByGenre(String genre) {
		// TODO Auto-generated method stub
		return null;
	}
}
