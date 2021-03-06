package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Library.Book;
import Library.Library;
import Library.Patron;

public class PatronTest {
	private Patron pat;
	private Book toto;
	private Library lib;
	
	@Before
	public void setUp() throws Exception {
		toto=new Book("Kaa", "Chouchou");
		ArrayList<Book> totob=new ArrayList<Book>(0);
		totob.add(toto);
		lib=new Library(totob);
		pat=new Patron("Kaa-neki", lib);
		lib.getCal().advance();
		lib.getCal().advance();
		}

	@Test
	public void test() throws Exception {
		assertEquals(pat.getName(),"Kaa-neki");
		pat.take(toto);
		ArrayList<Book> patCollection=new ArrayList<Book>(0);
		patCollection.add(toto);
		assertEquals(pat.getBooks(),patCollection);
		pat.giveBack(toto);
		patCollection.remove(toto);
		assertEquals(pat.getBooks(),patCollection);
		assertEquals(pat.toString(),"Kaa-neki");
		assertFalse(pat.hasBooksThatWereDueYesterday());
		toto.checkOut(1);
		pat.take(toto);
		assertEquals(lib.getTodaysDate(),2);
		assertTrue(pat.hasBooksThatWereDueYesterday());
		
	}

}
