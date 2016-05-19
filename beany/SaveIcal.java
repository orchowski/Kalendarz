package beany;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import klasy.Event;

// TODO: Auto-generated Javadoc
/**
 * The Class SaveIcal.
 * 
 * @author Krav(Przemys³aw Krawczel)
 */
@ManagedBean
@SessionScoped
public class SaveIcal {

	/** The version. */
	private static final String VERSION = "VERSION:2.0\r\n";

	/** The prodid. */
	private static final String PRODID = "PRODID://21INF-SP//Google Calendar GENERATOR\r\n";

	/** The cal begin. */
	private static final String CALBEGIN = "BEGIN:VCALENDAR\r\n";

	/** The cal end. */
	private static final String CALEND = "END:VCALENDAR\r\n";

	/** The event begin. */
	private static final String EVENTBEGIN = "BEGIN:VEVENT\r\n";

	/** The event end. */
	private static final String EVENTEND = "END:VEVENT\r\n";

	/** The date start. */
	private static final String DATESTART = "DTSTART:";

	/** The Constant DATEEND. */
	private static final String DATEEND = "DTEND:";

	/** The Constant DESC. */
	private static final String DESC = "DESCRIPTION:";

	/** The Constant EVENTTITLE. */
	private static final String EVENTTITLE = "SUMMARY:";

	/**
	 * Instantiates a new save ical.
	 */

	/**
	 * Save.
	 *
	 * @param events
	 *            the events
	 */
	public void save(final List<Event> events) {
		if (!isEventEmpty(events)) {
			saveToFile(events);
		}
	}

	/**
	 * Checks if is event empty.
	 *
	 * @param events
	 *            the events
	 * @return true, if is event empty
	 */
	private boolean isEventEmpty(final List<Event> events) {
		boolean isEmpty1;
		if (events.isEmpty()) {
			isEmpty1 = true;
			info();
		} else {
			isEmpty1 = false;
		}
		return isEmpty1;

	}

	/**
	 * Save to file.
	 *
	 * @param events
	 *            the events
	 */
	private void saveToFile(final List<Event> events) {
		// final File file = new File("ical/src/main/webapp/ical.ics");
		final File file = new File("/src/main/resources/ical.ics");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Error");
			}
		}
		try {
			final FileWriter iCalFile = new FileWriter(file.getAbsoluteFile());
			final BufferedWriter buf = new BufferedWriter(iCalFile);
			buf.write(CALBEGIN);
			buf.write(VERSION);
			buf.write(PRODID);
			buf.write("TZID:Europe/Warsaw\r\n");
			for (int i = 0; i < events.size(); i++) {
				buf.write(EVENTBEGIN);

				buf.write(DATESTART + dateParse(events.get(i), true));
				buf.write(DATEEND + dateParse(events.get(i), false));
				buf.write(EVENTTITLE + getTitle(events.get(i)) + "\r\n");
				buf.write(DESC + getDescription(events.get(i)) + "\r\n");
				buf.write(EVENTEND);
			}
			buf.write(CALEND);
			buf.close();

		} catch (IOException e) {
			System.out.println("Problem z plikiem");
		}

	}

	/**
	 * Gets the title.
	 *
	 * @param event
	 *            the event
	 * @return the title
	 */
	private String getTitle(final Event event) {
		return event.getTitle();
	}

	/**
	 * Gets the description.
	 *
	 * @param event
	 *            the event
	 * @return the description
	 */
	private String getDescription(final Event event) {
		return event.getDescription();
	}

	/**
	 * Date parse.
	 * @param event  the event
	 * @param startOrEnd the start or end of date
	 * @return the string
	 */
	private String dateParse(final Event event, final boolean startOrEnd) {
		final StringBuilder formated = new StringBuilder();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
		if (startOrEnd) {
			formated.append(format1.format(event.getStartDate()));
		} else {
			formated.append(format1.format(event.getEndDate()));
		}
		formated.append('T');
		format1 = new SimpleDateFormat("HHmmss", Locale.ENGLISH);
		formated.append(format1.format(event.getStartDate())).append("\r\n");
		System.out.println(formated);
		return formated.toString();
	}

	/**
	 * Info.
	 */
	public void info() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "There is nothing to save"));
	}
}