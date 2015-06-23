package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Library.Book;

public class BookTest {
	private Book book;
	
	@Before
	public void setUp() throws Exception {
		book=new Book("Kaa", "Chouchou");
	}

	@Test
	public void test() {
		assertEquals(book.getAuthor(), "Chouchou");
		assertEquals(book.getTitle(), "Kaa");
		assertEquals(book.getDueDate(), -1);
		book.checkOut(10);
		assertEquals(book.getDueDate(), 10);
		book.checkIn();
		assertEquals(book.getDueDate(), -1);
		assertEquals(book.toString(),"Kaa, by Chouchou");
	}

}
