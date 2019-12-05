package tuv.lib.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**Class for the rent model
 * @author Zheni
 *
 */
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
	
	
	public Rent(String clientName , String BookName, String retDate)
	{
		this.client = new Client(clientName);
		this.book = new Book(BookName);
		this.returnDate = LocalDate.parse(retDate);
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

	/**
	 * Updates loyalty of the user that is in the rent
	 */
	public void updateLoyalty() {
		LocalDate tempDateTime = LocalDate.from(this.getReturnDate());
		long diff = tempDateTime.until(LocalDate.now(), ChronoUnit.DAYS);
		long update = 0;
		if (diff < 0 && diff > -9) {
			update = 2;
		} else if (diff == 0) {
			update = 1;
		} else {

			update = -5;
		}
		int newLoyalty = (int) (this.getClient().getLoyalty() + update);
		this.getClient().setLoyalty(newLoyalty);
	}

}
