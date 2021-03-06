package Library;
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
	public String getName(){
		return this.name;
	}
	
	public void take(Book book) throws Exception {
			if (this.rentedBooks.size()>2){
				throw new Exception("Cannot take more than 3 books.");
			}
			else{
				rentedBooks.add(book);
			}
	}
	
	public void giveBack(Book book){
		if(this.rentedBooks.isEmpty()){
			System.out.println("This patron has not checked out any book.");
		}
		else{
			this.rentedBooks.remove(book);
		}	
	}
	
	public ArrayList<Book> getBooks(){
		return rentedBooks;
	}
	
	@Override
	public String toString(){
		return name;
	}

	public boolean hasBooksThatWereDueYesterday() {
		
		for (int i=0;i<this.getBooks().size();i++){
			if (this.getBooks().get(i).getDueDate()==this.library.getTodaysDate()-1){
				return true;
			}
		}
		return false;
	}
	public Library getLib(){
		return library;
	}
}
