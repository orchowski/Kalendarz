package iCalProject;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.Iterator;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.CalendarComponent;

// TODO: Auto-generated Javadoc
/**
 * The Class App.
 */
final public class App {
	
	/** name of iCal File. */
	public final static String ICALFILE = "basic.ics";
	
	/** The calendar. */
	static Calendar calendar = new Calendar();
	
	/** The file. */
	public static String file;

	/**
	 * Instantiates a new app.
	 */
	public App() {

	}

	/**
	 * main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) { 
		App app = new App();
		calendar = app.openStreams(calendar);
		file = app.readiCalFile(calendar);
	}

	/**
	 * Open streams.
	 *
	 * @param calendar the calendar
	 * @return the calendar
	 */
	public Calendar openStreams(Calendar calendar) {
		try {
			final FileInputStream fin = new FileInputStream(ICALFILE);
			final CalendarBuilder builder = new CalendarBuilder();
			try {
				calendar = builder.build(fin);
			} catch (IOException e) {
				System.out.println("IOException!");
			} catch (ParserException e) {
				System.out.println("ParserException!");
			}

		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku!");
		}
		return calendar;
	}

	/**
	 * Readi cal file.
	 *
	 * @param calendar the calendar
	 * @return the string
	 */
	public String readiCalFile(Calendar calendar) {
		String line = null;
		for (final Iterator<CalendarComponent> i = calendar.getComponents().iterator(); i.hasNext();) {
			Component component = (Component) i.next();
			line = component.getName() + " ";
			//System.out.println("Component: [" + component.getName() + "]");

			for (Iterator<Property> j = component.getProperties().iterator(); j.hasNext();) {
				Property property = (Property) j.next();
				line += property.getName() + " " + property.getValue();
				//System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
			}
		}
		return line;
	}
}