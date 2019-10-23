package tuv.lib.models.interfaces;

import tuv.lib.models.Book;

public interface BookService {
	public void addBook(Book b);
	public void updateBook(Book b);
	public void removeBook(Book b);
}
