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

public class App {

	public static void main(String[] args) {
		String iCalFile = "basic.ics";
		Calendar calendar = new Calendar();

		try {
			FileInputStream fin = new FileInputStream(iCalFile);
			CalendarBuilder builder = new CalendarBuilder();
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
		
		for(Iterator i = calendar.getComponents().iterator(); i.hasNext();){
			Component component = (Component) i.next();
			System.out.println("Component: [" + component.getName() + "]");
			
			for(Iterator j = component.getProperties().iterator(); j.hasNext();){
				Property property = (Property) j.next();
				System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
			}
		}

	}

}