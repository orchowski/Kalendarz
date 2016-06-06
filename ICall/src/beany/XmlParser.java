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
	private transient Event event = new Event();

	/**
	 * Parses the xml.
	 *
	 * @return the list
	 * @throws SAXException
	 *             the SAX exception
	 * @throws Exception
	 *             the exception
	 */
	public List<Event> parseXml() throws SAXException, Exception {
		final List<Event> events = new ArrayList<Event>();

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final File file = new File("D:/ICal/test/Ical/test2.xml");
			final Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();
			final NodeList nList = doc.getElementsByTagName("event");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					event.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
					event.setTitle(eElement.getElementsByTagName("summary").item(0).getTextContent());
					event.setEndDate(dateParse(eElement.getElementsByTagName("dateEnd").item(0).getTextContent()));
					event.setStartDate(dateParse(eElement.getElementsByTagName("dateStart").item(0).getTextContent()));
					events.add(event);
				}
			}
		} catch (ParserConfigurationException e) {
			info();
		} catch (IOException e) {
			info();
		}
		return events;
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
	private Date dateParse(final String string) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
		Date parsedDate = dateFormat.parse(string);
		return parsedDate;
	}

	/**
	 * Info.
	 */
	public void info() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Problem z plikiem"));
	}
}
