import java.util.ArrayList;


public class Library {
	
	private boolean okToPrint=false;
	private ArrayList<Book> collection;
	private HashMap<String, Patron> registeredPatrons;
	
	public Library(ArrayList<Book> collection){
		this.collection=collection;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Calendar today= new Calendar();
//		System.out.println(today.getDate());
//		today.advance();
//		System.out.println(today.getDate());
		
//		Book toto=new Book("Kaa", "Chouchou");
//		System.out.println(toto.getAuthor());
//		System.out.println(toto.getDueDate());
//		System.out.println(toto.getTitle());
//		System.out.println(toto.toString());
//		toto.checkOut(5);
//		System.out.println(toto.getDueDate());
//		toto.checkIn();
//		System.out.println(toto.getDueDate());
	}
	void Print(String message){
		if (okToPrint) {
			System.out.print(message);
		}
		else{
			return;
		}
	}
	void Println(String message){
		if (okToPrint) {
			System.out.println(message);
		}
		else{
			return;
		}
	}

}