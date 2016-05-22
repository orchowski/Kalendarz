package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

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

	public void test() throws IOException {
		
		File testFile = testSave.saveToFile(events, "/ical1.ics");
		File expectedFile = new File("/ical.ics");
	//	Assert.assertEquals(expectedFile, testFile);
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

}
