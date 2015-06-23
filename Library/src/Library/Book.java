package Library;

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
		return title;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public int getDueDate(){
		return dueDate;
	}
	
	public void checkOut(int date){
		dueDate=date;
	}
	
	public void checkIn(){
		dueDate=-1;
	}
	
	@Override
	public String toString(){
		return title+", by "+author;
	}
}
