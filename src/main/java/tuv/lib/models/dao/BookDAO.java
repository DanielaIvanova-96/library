package tuv.lib.models.dao;

import java.util.List;

import tuv.lib.models.Book;
import tuv.lib.models.User;

public interface BookDAO {
	public Book getBookById(int id);
	public void addBook(Book b);
	public void updateBook(Book b);
	public void removeBook(int id);
}
