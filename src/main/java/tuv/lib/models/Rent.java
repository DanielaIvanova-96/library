package tuv.lib.models;

import java.time.LocalDate;

public class Rent {
	private Client client;
	private Book book;
	private int status;
	private LocalDate takeDate ;
	private LocalDate returnDate ;
	
	public Rent(Client cl, Book book)
	{
		this.client = cl;
		this.book =book;
		this.status =0;
		this.takeDate = LocalDate.now();
		this.returnDate = takeDate.plusDays(10);
		
	}
}
