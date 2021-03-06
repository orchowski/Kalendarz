﻿package beany;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part; //do Parta

import org.xml.sax.SAXException;

import klasy.Event;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.CalendarComponent;
import parserUZ.ParserUz;

/**
 * The Class EventBean.
 *
 * @author Wenaro
 */
@ManagedBean
@SessionScoped
public class EventBean implements Serializable {
	private static net.fortuna.ical4j.model.Calendar calendar;
	private static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private static int puste;
	private Part file1;
	private String fileContent;
	// private static FileInputStream fin = null;
	private static CalendarBuilder builder = new CalendarBuilder();
	String pliczek = null;
	StringReader sin = null;
	String url;
	ParserUz uz = new ParserUz();
	public final String getUrl() {
		return url;
	}

	public final void setUrl(String url) {
		this.url = url;
	}

	// private static final String ValidationPattern =
	// "([^\\s]+(\\.(?i)(ics))$)";
	// private Pattern pattern;
	// private Matcher matcher;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The event. */
	private Event event;

	// private boolean isOnList;

	/** The events. */
	private List<Event> events;

	/** The deleted. */
	private boolean deleted;

	/**
	 * Inits the event and events.
	 */
	@PostConstruct
	public void init() {

		event = new Event();
		events = new ArrayList<Event>();
	}

	public void addUZ() {
		FacesContext context = FacesContext.getCurrentInstance();
       
		uz.connection(url);
		 context.addMessage(null, new FacesMessage("Łączenie..."));
		uz.parse();
		 context.addMessage(null, new FacesMessage("Parsowanie..."));
		for(int i=0; i<uz.getListaZajec().size(); i++){
			events.add(uz.getListaZajec().get(i));
		}
		 context.addMessage(null, new FacesMessage("Zakończono pomyślnie!"));
	
		//clearEvent();
	}

	
	/**
	 * Adds the event.
	 */
	public void add() {

		events.add(event);
		clearEvent();
	}

	/**
	 * Clear event.
	 */
	public void clearEvent() {

		event = new Event();
	}

	/**
	 * Clear list of events.
	 */
	public void clear() {

		events.removeAll(events);
	}

	/**
	 * Removes the.
	 *
	 * @param event
	 *            the event
	 */
	public void remove(Event event) {

		events.remove(event);
	}

	/**
	 * Save action.
	 */
	public void saveAction() {

		for (Event event : events) {
			event.setEditable(false);
		}
	}

	/**
	 * Set editable for event.
	 *
	 * @param event
	 *            the event
	 */
	public void edit(Event event) {

		event.setEditable(true);
	}

	// Hubi metody
	public Part getFile1() {
		return file1;
	}

	public void setFile1(Part file1) {
		this.file1 = file1;
	}

	public void upload() throws SAXException, Exception {
		try {

			InputStream in = file1.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			StringBuffer sb = new StringBuffer();
			while ((fileContent = bf.readLine()) != null) {
				sb.append(fileContent + "\r\n");
			}
			pliczek = sb.toString();
		if ("text/xml".equals(file1.getContentType())) {
				// metoda Przemka
				// addEvents(xmlParser.parseXml()
				// powinno to zadziałac, ale nie jestem pewien w 100%
				File tempFile = new File("/var/lib/openshift/57337cba0c1e66d8d9000088/wildfly/ics/tempfile.xml");
				//File tempFile = new File("D:/tempfile.xml");
				try (PrintWriter out = new PrintWriter(tempFile)) {
					out.println(pliczek.toUpperCase());
				}
				addEvents(XmlParser.parseXml(tempFile));
			//	 to co masz u gory powinno załatwic sprawe 
			} else {
					sin = new StringReader(pliczek);
					open();
			}
		} catch (IOException e) {
			// Error handling
		}
	}

	public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part) value;
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
	}

	// u gory metody do upload pliku

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
		// clear();
		// final FileInputStream fin = new
		// FileInputStream("C:\\Users\\hubik_000\\Desktop\\basic.ics");
		try {
			calendar = builder.build(sin);
		} catch (IOException e) {
			System.out.println("IOException");
		}

		String line;
		for (final Iterator<CalendarComponent> i = calendar.getComponents().iterator(); i.hasNext();) {
			Component component = (Component) i.next();
			line = component.getName() + " ";

			for (final Iterator<Property> j = component.getProperties().iterator(); j.hasNext();) {
				Property property = (Property) j.next();
				line += property.getName() + " " + property.getValue();
				if (property.getName().equals("DTSTART")) {
					if (property.getValue().isEmpty()) {
						puste = 0;
					} else {
						puste = 1;
					}
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
			}
			if (puste == 1)
				add();
		}
	}

	// koniec hubi metody

	/**
	 * Copy event.
	 *
	 * @param event
	 *            the event
	 */
	public void copy(Event event) {

		events.add(new Event(event));
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public Event getEvent() {

		return event;
	}

	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	public List<Event> getEvents() {

		return events;
	}

	/**
	 * Sets the event.
	 *
	 * @param event
	 *            the new event
	 */
	public void setEvent(Event event) {

		this.event = event;
	}

	/**
	 * Sets the events.
	 *
	 * @param events
	 *            the new events
	 */
	public void setEvents(List<Event> events) {

		this.events = events;
	}

	/**
	 * Checks if is deleted.
	 *
	 * @return true, if is deleted
	 */
	public boolean isDeleted() {

		return deleted;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param deleted
	 *            the new deleted
	 */
	public void setDeleted(boolean deleted) {

		this.deleted = deleted;
	}
	public void addEvents(List<Event> events) {
		this.events.addAll(events);
	}

}