package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import beany.SaveIcal;
import junit.framework.TestCase;
import junitx.framework.FileAssert;
import klasy.Event;

public class TestD extends TestCase {
	SaveIcal testSave = new SaveIcal();
	static List<Event> events = new ArrayList<Event>();
	static Date dateStart = new Date();
	static Date dateEnd = new Date();
	static Event event = new Event();

	public void testFile() throws IOException {
		File testFile = testSave.saveToFile(events, "/ical1.ics");
		File expectedFile = new File("/ical.ics");
		// Assert.assertEquals(expectedFile, testFile);
		FileAssert.assertEquals(expectedFile, testFile);
		FileReader test = new FileReader(testFile);
		FileReader expect = new FileReader(expectedFile);
		BufferedReader r1 = new BufferedReader(test);
		BufferedReader r2 = new BufferedReader(expect);
		String line;
		while ((line = r1.readLine()) != null) {
			assertEquals(line, r2.readLine());
		}
	}

	@Test
	 public void testDate() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 0);
		Date d = cal.getTime();
		String expected = "20160522T000000\r\n";
		event.setStartDate(d);
		assertEquals(expected, testSave.dateParse(event, true));

	}
	@Test
	 public void testDate2() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 10);
		cal.set(Calendar.MINUTE, 12);
		cal.set(Calendar.SECOND, 54);
		Date d = cal.getTime();
		String expected = "20160522T101254\r\n";
		event.setStartDate(d);
		assertEquals(expected, testSave.dateParse(event, true));

	}
	@Test
	 public void testDate3() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,2018);
		cal.set(Calendar.HOUR_OF_DAY, 10);
		cal.set(Calendar.MINUTE, 12);
		cal.set(Calendar.SECOND, 54);
		Date d = cal.getTime();
		String expected = "20180522T101254\r\n";
		event.setStartDate(d);
		assertEquals(expected, testSave.dateParse(event, true));

	}
	@Test
	 public void testDate4() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,2018);
		cal.set(Calendar.MONTH,1);
		cal.set(Calendar.DAY_OF_MONTH,11);
		cal.set(Calendar.HOUR_OF_DAY, 10);
		cal.set(Calendar.MINUTE, 12);
		cal.set(Calendar.SECOND, 54);
		Date d = cal.getTime();
		String expected = "20180211T101254\r\n";
		event.setStartDate(d);
		assertEquals(expected, testSave.dateParse(event, true));

	}

}
