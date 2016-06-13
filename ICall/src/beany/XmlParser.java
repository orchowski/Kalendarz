package beany;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import klasy.Event;

// TODO: Auto-generated Javadoc
/**
 * The Class XmlParser.
 */
@ManagedBean
@SessionScoped
public class XmlParser {
	/** The event. */
	private transient static Event event = new Event();

	/**
	 * Parses the xml.
	 *
	 * @return the list
	 * @throws SAXException
	 *             the SAX exception
	 * @throws ParseException
	 * @throws DOMException
	 * @throws Exception
	 *             the exception
	 */
	public static final List<Event> parseXml(final File file) throws SAXException, DOMException, ParseException {
		final List<Event> events = new ArrayList<Event>();
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document doc = builder.parse(file);
			normalize(doc);

			final NodeList nList = doc.getElementsByTagName("VEVENT");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				}
				Element eElement = (Element) nNode;
					addEventsFromXml(nNode, events, eElement);
				
			}
		} catch (ParserConfigurationException e) {
			info();
		} catch (IOException e) {
			info();
		}
		return events;
	}

	private static void normalize(Document doc) {
		doc.normalize();
	}

	/**
	 * Date parse.
	 *
	 * @param string
	 *            the string
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	private static Date dateParse(final String string) throws ParseException {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		return dateFormat.parse(string);
	}

	public static void addEventsFromXml(Node nNode, List<Event> events, Element eElement)
			throws DOMException, ParseException {
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			if (eElement.getElementsByTagName("DTEND").item(0).getTextContent() != null)
				event.setEndDate(dateParse(eElement.getElementsByTagName("DTEND").item(0).getTextContent()));
			else event.setEndDate(null);
			event.setDescription(eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent());
			event.setTitle(eElement.getElementsByTagName("SUMMARY").item(0).getTextContent());
			event.setEndDate(dateParse(eElement.getElementsByTagName("DTEND").item(0).getTextContent()));
			event.setStartDate(dateParse(eElement.getElementsByTagName("DTSTART").item(0).getTextContent()));
			events.add(event);
		}

	}

	/**
	 * Info.
	 */
	public static void info() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Problem z plikiem"));
	}

}
