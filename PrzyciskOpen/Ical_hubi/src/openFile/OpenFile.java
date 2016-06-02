package openFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import beany.EventBean;
import jdk.nashorn.internal.runtime.ParserException;
import klasy.Event;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

public class OpenFile {
	private static net.fortuna.ical4j.model.Calendar calendar;
	private static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private Event event;
	private EventBean eventBean;

	public java.util.Date stringParseToDate(String date) throws ParseException {
		java.util.Date result = dateFormat.parse(date);
		return result;
	}

	public java.util.Date returnDate(Property property) throws ParseException {
		String minutes = null;
		String hour = null;
		String dataPoczatek = property.getValue();
		// System.out.println(dataPoczatek);
		String year = dataPoczatek.substring(0, 4);
		String month = dataPoczatek.substring(4, 6);
		String day = dataPoczatek.substring(6, 8);
		if (dataPoczatek.contains("T")) {
			// System.out.println(dataPoczatek.substring(8,9));
			hour = dataPoczatek.substring(9, 11);
			minutes = dataPoczatek.substring(11, 13);
		} else {
			hour = "00";
			minutes = "00";
		}
		String date = day + "-" + month + "-" + year + " " + hour + ":" + minutes + ":00";
		Date startDate = stringParseToDate(date);
		return startDate;
	}

	// g³owna metoda otwierajaca plik iCal
	public void open() throws net.fortuna.ical4j.data.ParserException, ParseException {
		try {
			final FileInputStream fin = new FileInputStream("basic.ics");
			final CalendarBuilder builder = new CalendarBuilder();
			try {
				// calendar = builder.build(fin);
				calendar = builder.build(fin);
			} catch (IOException e) {
				System.out.println("IOException!");
			} catch (ParserException e) {
				System.out.println("ParserException!");
			}

		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku!");
		}

		String line;
		for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
			Component component = (Component) i.next();
			line = component.getName() + " ";

			for (Iterator<Property> j = component.getProperties().iterator(); j.hasNext();) {
				Property property = (Property) j.next();
				line += property.getName() + " " + property.getValue();
				if (property.getName().equals("DTSTART")) {
					Date startDate = returnDate(property);
					event.setStartDate(startDate);
				}
				if (property.getName().equals("DTEND")) {
					java.util.Date endDate = returnDate(property);
					event.setEndDate(endDate);
				}
				if (property.getName().equals("SUMMARY")) {
					String title = property.getValue();
					event.setTitle(title);
				}
				if (property.getName().equals("DESCRIPTION")) {
					String description = property.getValue();
					event.setDescription(description);
				}
				eventBean.add();
			}
		}
	}
}
