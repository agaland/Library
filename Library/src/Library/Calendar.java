package Library;

public final class Calendar {
	
	// Attributes
	private int date;
	
	// Constructor
	
	public Calendar(){
		date=0;
	}
	
	// Methods
	public int getDate(){
		return date;
	}
	
	public void advance(){
		date += 1;
	}
}