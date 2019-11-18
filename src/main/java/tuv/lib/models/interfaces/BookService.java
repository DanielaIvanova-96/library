package tuv.lib.models.interfaces;

import java.util.List;

import tuv.lib.models.Book;

public interface BookService {
	public void addBook(Book b);
	public void updateBook(Book b);
	public void removeBook(int id);
	public List<Book> getBookbyName(String name);
	public List<Book> getBooksByAuthor(String author);
	public List<Book> getBooksByGenre(String genre);
	public List<Book> getBookByCondition(int condition);
}
