package tuv.lib.models;

import java.time.LocalDate;

public class Rent {
	private Client client;
	private Book book;
	private int status;
	private LocalDate takeDate;
	private LocalDate returnDate;

	public Rent(Client cl, Book book) {
		this.client = cl;
		this.book = book;
		this.status = 0;
		this.takeDate = LocalDate.now();
		this.returnDate = takeDate.plusDays(10);

	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDate getTakeDate() {
		return takeDate;
	}

	public void setTakeDate(LocalDate takeDate) {
		this.takeDate = takeDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

}
