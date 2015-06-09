import java.util.ArrayList;


public class Patron {
	
	// Attributes
	private String name;
	private Library library;
	private ArrayList<Book> rentedBooks;
	
	// Constructors
	public Patron(String name, Library library){
		this.name=name;
		this.library=library;
		rentedBooks= new ArrayList<Book>(3);
	}
	
	// Methods
	String getName(){
		return this.name;
	}
	
	void take(Book book){
		if (this.rentedBooks.size()>3){
			System.out.println("This patron has already checked out 3 books.");
		}
		else{
			this.rentedBooks.add(book);
		}
		// TODO check he has a card before he can check out a book.
	}
	
	void giveBack(Book book){
		if(this.rentedBooks.isEmpty()){
			System.out.println("This patron has not checked out any book.");
		}
		else{
			this.rentedBooks.remove(book);
		}	
	}
	ArrayList<Book> getBooks(){
		return this.rentedBooks;
	}
	@Override
	public String toString(){
		return this.name;
	}
}
