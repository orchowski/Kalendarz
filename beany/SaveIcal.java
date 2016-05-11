package beany;

import java.beans.EventSetDescriptor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import klasy.Event;

// TODO: Auto-generated Javadoc
/**
 * The Class SaveIcal.
 */
@ManagedBean
@SessionScoped
public class SaveIcal {
	
	/** The version. */
	private String version = "VERSION:2.0\r\n";
	
	/** The prodid. */
	private String prodid = "PRODID://21INF-SP//Google Calendar GENERATOR\r\n";
	
	/** The cal begin. */
	private String calBegin = "BEGIN:VCALENDAR\r\n";
	
	/** The cal end. */
	private String calEnd = "END:VCALENDAR\r\n";
	
	/** The event begin. */
	private String eventBegin = "BEGIN:VEVENT\r\n";
	
	/** The event end. */
	private String eventEnd = "END:VEVENT\r\n";
	
	/** The date start. */
	private String dateStart = "DTSTART:";
	
	/** The begin v timezone. */
	private String beginVTimezone = "BEGIN:VTIMEZONE\r\n";

	/**
	 * Save.
	 *
	 * @param events the events
	 */
	public void save(List<Event> events) {
		if (!isEventEmpty(events)) {
			saveToFile(events);
		}
	}

	/**
	 * Checks if is event empty.
	 *
	 * @param events the events
	 * @return true, if is event empty
	 */
	private boolean isEventEmpty(List<Event> events) {
		if (events.isEmpty()) {
			System.out.println("There is nothing to save.");
			return true;
		}
		return false;

	}

	/**
	 * Save to file.
	 *
	 * @param events the events
	 */
	private void saveToFile(List<Event> events) {
		StringBuilder fileIcal = new StringBuilder();
		fileIcal.append("ical");
		fileIcal.append(".ics");
		File file = new File("D:/ical.ics");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileWriter iCalFile = new FileWriter(file.getAbsoluteFile());
			BufferedWriter buf = new BufferedWriter(iCalFile);
			buf.write(calBegin);
			buf.write(version);
			buf.write(prodid);
			buf.write(beginVTimezone);
			for (int i = 0; i < events.size(); i++) {
				buf.write(eventBegin);

				buf.write(dateStart + dateParse(events.get(i)));
				buf.write(eventEnd);
				System.out.println("stworzono");
			}
			buf.write(calEnd);
			buf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Date parse.
	 *
	 * @param event the event
	 * @return the string
	 */
	@SuppressWarnings("deprecation")

	private String dateParse(Event event) {
		StringBuilder date = new StringBuilder();
		String str;
		int year = event.getStartDate().getYear()+1900;
		int month = event.getStartDate().getMonth() +1;
		int day = event.getStartDate().getDate();
		int hour = event.getStartDate().getHours();
		int minutes = event.getStartDate().getMinutes();
		//date.append(year+month+day+"T"+hour+minutes+"00"+"\r\n");
		date.append(year);
		date.append(month);
		date.append(day+"T");
		date.append(hour);
		date.append(minutes+"00"+"\r\n");
		str = date.toString();
		System.out.println(date);
		return str;
	}

}
