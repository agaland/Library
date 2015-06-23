package Library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Library {
	
	//Attributes
	
	private boolean okToPrint=false;
	private ArrayList<Book> collection= new ArrayList<Book>();
	private HashMap<String, Patron> registeredPatrons=new HashMap<String, Patron>();
	private Calendar date= new Calendar();
	private boolean isOpen=false;
	private Patron patronBeingServe=null;
	private ArrayList<Book> results=new ArrayList<Book>();
	private Scanner scanner = new Scanner(System.in);
	
	//Constructors
	
	public Library(){
		okToPrint=true;
		collection=readBookCollection();
	}
	
	public Library(ArrayList<Book> collection){
		this.collection=collection;
		okToPrint=false;
	}
	
	public static void main(String[] args) {
		

		Library test=new Library();
		System.out.println(test.isOpen);
		
		test.start();
		System.out.println(test.isOpen);		

	}
	
	
	//Methods 
	
	public void start(){
		 while (true){
			String input = getAnswerToQuestion("What do you want to do? \n open \n issueCard Patron's name \n serve Patron's name \n checkIn Book numbers \n search string \n checkOut Book numbers \n close \n quit");
		
			if ("quit".equals(input.toLowerCase())){
				break;
				
			}
			
			else{
				
				if (isOpen){
			
			
					if ("close".equals(input.toLowerCase())){
						close();
					}
				
					else if (input.toLowerCase().contains("serve")){
						serve(input.substring(6));
					}
				
					else if (input.toLowerCase().contains("issuecard")){
						issueCard(input.substring(10));
					}
					
					else if (input.toLowerCase().contains("checkin")){
						String[] splitInput =input.substring(8).split(" ");
						int[] tmp= new int[input.substring(7).length()/2];
						
						for (int i=0;i<splitInput.length;i++){
							tmp[i]=Integer.parseInt(splitInput[i]);
						}
						checkIn(tmp);
					}
				
					else if (input.toLowerCase().contains("checkout")){
						String[] splitInput =input.substring(9).split(" ");
						int[] tmp= new int[input.substring(8).length()/2];
					
						for (int i=0;i<splitInput.length;i++){
							tmp[i]=Integer.parseInt(splitInput[i]);
						}
						checkOut(tmp);
					}
				
					else if (input.toLowerCase().contains("search")){
						search(input.substring(7));
					}
				}
				else {
					if ("open".equals(input.toLowerCase())){
						open();
					}
				}
			}
		 }
		quit();
		
	}

	public String getAnswerToQuestion(String question) {
		System.out.println(question);
		String input = scanner.nextLine();
		return input;
	}
	
	public void print(String message){
		if (okToPrint) {
			System.out.print(message);
		}
		else{
			return;
		}
	}
	
	public void println(String message){
		if (okToPrint) {
			System.out.println(message);
		}
		else{
			return;
		}
	}
	
	public ArrayList<OverdueNotice> open(){
		date.advance();
		isOpen=true;
		System.out.println("The library is now open");
		ArrayList<OverdueNotice> notice=new ArrayList<OverdueNotice>(this.createOverdueNotices());
		System.out.println(notice);
		return notice;
	}
		
	public ArrayList<OverdueNotice> createOverdueNotices(){
		ArrayList<OverdueNotice> notices = new ArrayList<OverdueNotice>();
		for (String patronName : registeredPatrons.keySet()){
			Patron patron = registeredPatrons.get(patronName);
			if (patron.hasBooksThatWereDueYesterday())
				notices.add(new OverdueNotice(patron, this.getTodaysDate()));
		}
		return notices;
	}
	
	public Patron issueCard(String nameOfPatron){
		registeredPatrons.put(nameOfPatron, new Patron(nameOfPatron, this));
		System.out.println(nameOfPatron+" is now registered.");
		return registeredPatrons.get(nameOfPatron);
	}
	
	public Patron serve(String nameOfPatron){
		patronBeingServe=registeredPatrons.get(nameOfPatron);
		ArrayList<Book> book=patronBeingServe.getBooks();
		for (int a=0; a< book.size();a++){
			System.out.println(a+". "+book.get(a).toString());
		}
		System.out.println("You are currently serving "+patronBeingServe.getName());
		return patronBeingServe;
	}
	
	public ArrayList<Book> checkIn(int... bookNumbers){
		if (patronBeingServe==null){
			System.out.println("No patron is currently being served.");
			return null;
		}
		else if (patronBeingServe.getBooks().isEmpty()){
			System.out.println("This patron has not checked out any books yet");
			return null;
		}
		else {
		ArrayList<Book>	returnedBooks=new ArrayList<Book>(0);
			ArrayList<Book> tmpList=new ArrayList<Book>(patronBeingServe.getBooks());
			for (int i=0; i<bookNumbers.length;i++){
				Book tmpBook=tmpList.get(bookNumbers[i]);
				
				tmpBook.checkIn();
				collection.add(tmpBook);
				returnedBooks.add(tmpBook);
				System.out.println("The book "+tmpBook.toString()+" was successfully checked in, by " + patronBeingServe.toString());
				patronBeingServe.giveBack(tmpBook);
			}
			return returnedBooks;
		}
	}
	
	public ArrayList<Book> search(String part){
		
		if (part.length()>3){
			
			for (int l=0; l<collection.size();l++){
				if (collection.get(l).toString().toUpperCase().contains(part.toUpperCase())){
					boolean alreadyFound = false;
					for (int m=0; m<results.size();m++){
						if (results.get(m).toString().toUpperCase().contains(collection.get(l).toString().toUpperCase())){
							alreadyFound = true;
							break;
						}
					}
					if (!alreadyFound)
						results.add(collection.get(l));
				}
			}
			if (results.isEmpty()){
				System.out.println("No book found");
				return null;
			}
			else {
				for (int i=0; i<results.size();i++){
					System.out.println(i+". "+results.get(i));
				}
				
				return results;
			}
		}
		else {
			System.out.println("You need at least 4 characters");
			return null;
		}
	}
	
	public ArrayList<Book> checkOut(int... bookNumbers){
		if (patronBeingServe==null){
			System.out.println("No patron is currently being served.");
			return null;
		}
		else {
			ArrayList<Book>	checkedOutBooks=new ArrayList<Book>(0);
			for (int i=0; i<bookNumbers.length;i++){
				Book tmpBook=results.get(bookNumbers[i]);
			try {
				patronBeingServe.take(tmpBook);
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
				tmpBook.checkOut(getTodaysDate()+10);
				collection.remove(tmpBook);
				checkedOutBooks.add(tmpBook);
				System.out.println("The book "+tmpBook.toString()+" was successfully checked out, by " + patronBeingServe.toString());
			}
			results=new ArrayList<Book>();
			return checkedOutBooks;
		}
		
	}
	
	public void close(){
		isOpen=false;
		patronBeingServe=null;
		System.out.println("The library is now closed");
	}
	
	public void quit(){
		close();
		scanner.close();
		System.exit(0);
	}
	
	public int getTodaysDate(){
		return date.getDate();
	}
	
	public Calendar getCal(){
		return this.date;
	}
	
	public Patron getPatronBeingServe(){
		return patronBeingServe;
	}
	public ArrayList<Book> readBookCollection() {
	    File file = new File("books.txt");
	    ArrayList<Book> collection = new ArrayList<Book>();
	    try {
	        FileReader fileReader = new FileReader(file);
	        BufferedReader reader = new BufferedReader(fileReader);
	        while (true) {
	            String line = reader.readLine();
	            if (line == null) break;
	            line = line.trim();
	            if (line.equals("")) continue; // ignore possible blank lines
	            String[] bookInfo = line.split(" :: ");
	            Book newBook = new Book(bookInfo[0], bookInfo[1]);
	            collection.add(newBook);
	            System.out.println(newBook.toString());
	            
	        }reader.close();
	    }
	    catch (IOException e) {
	        System.out.println(e.getMessage());
	    }
	    return collection;
	}

}
