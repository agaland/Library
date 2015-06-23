package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Library.Calendar;

public class CalendarTest {
	private Calendar cal;

	@Before
	public void setUp() throws Exception {
		cal = new Calendar();
	}

	@Test
	public void testAdvance() {
		assertEquals(cal.getDate(), 0);
		cal.advance();
		assertEquals(cal.getDate(), 1);
	}

}
