
public class Calendar {
	
	// Attributes
	private int date;
	
	// Constructor
	
	public Calendar(){
		date=0;
	}
	
	// Methods
	int getDate(){
		return this.date;
	}
	
	void advance(){
		this.date=this.date+1;
	}
}
