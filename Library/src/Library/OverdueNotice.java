package Library;

public class OverdueNotice {

	// Attributes
	private Patron patron;
	private int todaysDate;
	
	//Constructor
	public OverdueNotice(Patron patron, int todaysDate){
		this.patron=patron;
		this.todaysDate=todaysDate;
	}
	
	//Methods
	@Override
	public String toString(){
		String msg = new String("");
		msg+="Dear "+patron+", here are the books you have checked out and their due dates:";
	
		for (int i=0;i<this.patron.getBooks().size();i++){
			msg+="\n"+this.patron.getBooks().get(i).toString()+", "+this.patron.getBooks().get(i).getDueDate();
			
			if (todaysDate>this.patron.getBooks().get(i).getDueDate()){
				msg+=","+" OVERDUE";
			}
			else{
				
			}
		}
		return msg;
	}
}
