package test;

import static org.junit.Assert.*;

import java.beans.EventSetDescriptor;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import beany.XmlParser;
import klasy.Event;

public class XmlParseTest {
	static List<Event> events = new ArrayList<Event>();
	
	File file = new File("TestFile1.xml");
	Event event1 = new Event();
	
	@Test
	public void XmlParseTest1() throws DOMException, SAXException, ParseException {
		
		events =XmlParser.parseXml(file);
		assertNotNull(events);
	
	}
	@Test
	public void XmlPaseTest2() throws DOMException, SAXException, ParseException{
		events =XmlParser.parseXml(file);
		event1.setDescription("test 123");
		assertEquals(event1.getDescription(), events.get(0).getDescription() );
		
	}
	public void XmlPaseTest3() throws DOMException, SAXException, ParseException{
		events =XmlParser.parseXml(file);
		event1.setTitle("Test");
		assertEquals(event1.getTitle(), events.get(0).getTitle() );
		
	}
	@Test 
	public void dateParseTest() throws DOMException, SAXException, ParseException{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,2016);
		cal.set(Calendar.MONTH,2);
		cal.set(Calendar.DAY_OF_MONTH,2);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 00);
		Date startDate = cal.getTime();
		events =XmlParser.parseXml(file);
		
		event1.setStartDate(startDate);;
		assertEquals(event1.getStartDate(), events.get(0).getStartDate());	}
	@Test
	public void dateParseTest1() throws DOMException, SAXException, ParseException{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,2016);
		cal.set(Calendar.MONTH,2);
		cal.set(Calendar.DAY_OF_MONTH,3);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 00);
		Date startDate = cal.getTime();
		events =XmlParser.parseXml(file);
		
		event1.setEndDate(startDate);;
		assertEquals(event1.getEndDate(), events.get(0).getEndDate());	}
	
	
}
