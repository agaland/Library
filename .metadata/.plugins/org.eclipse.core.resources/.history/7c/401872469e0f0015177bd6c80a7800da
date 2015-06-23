
public class Book {

	// Attributes
	private String title;
	private String author;
	private int dueDate=-1;
	
	// Constructor
	public Book(String title, String author){
		this.title=title;
		this.author=author;
	}
	
	// Methods
	public String getTitle(){
		return this.title;
	}
	public String getAuthor(){
		return this.author;
	}
	public int getDueDate(){
		return this.dueDate;
	}
	public void checkOut(int date){
		this.dueDate=date;
	}
	public void checkIn(){
		this.dueDate=-1;
	}
	@Override
	public String toString(){
		return title+", by "+author;
	}
}
