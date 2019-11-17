package tuv.lib.models;

import java.util.List;

public class Book {
	int id;
	String name;
	String invNumber;
	String genre;
	int condition;
	List <String> authors;
	//String authors;

	public Book(){}

	public Book(String name,List<String> authors,String genre, String invNum, int condition ){
		this.name = name;
		this.invNumber = invNum;
		this.genre = genre;
		this.condition = condition;
		this.authors = authors;
	}

	public int getId(){ return this.id; }
	public void setId(int id){ this.id = id; }

	public String getName() { return this.name; }
	public void setName(String name){ this.name = name; }

	public String getInvNumber() { return this.invNumber; }
	public void setInvNumber(String invNumber){ this.invNumber = invNumber; }

	public String getGenre() { return this.genre; }
	public void setGenre(String genre){ this.genre = genre; }

	public int getCondition() { return this.condition; }
	public void setCondition(int condition) {this.condition = condition;}

	public List<String> getAuthors() {return this.authors; }
	public void setAuthors(List<String> authors) {this.authors = authors; }
}
