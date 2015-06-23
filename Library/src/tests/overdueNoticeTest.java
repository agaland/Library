package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Library.Book;
import Library.Library;
import Library.OverdueNotice;
import Library.Patron;

public class overdueNoticeTest {

	private OverdueNotice note;
	private Patron pat;
	private Library lib;
	private Book toto;
	
	@Before
	public void setUp() throws Exception {
		toto=new Book("Kaa", "Chouchou");
		ArrayList<Book> totob=new ArrayList<Book>(0);
		totob.add(toto);
		lib=new Library(totob);
		pat=new Patron("Kaa-neki", lib);
		note=new OverdueNotice(pat, 2);
		
	}

	@Test
	public void test() throws Exception {
		pat.take(toto);
		lib.getCal().advance();
		lib.getCal().advance();
		toto.checkOut(1);
		System.out.println(note.toString());
		assertEquals(note.toString(), "Dear Kaa-neki, here are the books you have checked out and their due dates:"+"\n"+"Kaa, by Chouchou, 1, OVERDUE");
		toto.checkIn();
		toto.checkOut(3);
		assertEquals(note.toString(), "Dear Kaa-neki, here are the books you have checked out and their due dates:"+"\n"+"Kaa, by Chouchou, 3");
		
	}

}
