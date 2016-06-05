//package beany;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.StringReader;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.validator.ValidatorException;
//import javax.servlet.http.Part;
//
//import klasy.Event;
//import net.fortuna.ical4j.data.CalendarBuilder;
//import net.fortuna.ical4j.data.ParserException;
//import net.fortuna.ical4j.model.Component;
//import net.fortuna.ical4j.model.Property;
//import net.fortuna.ical4j.model.component.CalendarComponent;
//
//@ManagedBean
//public class OpenFile {
//	private static net.fortuna.ical4j.model.Calendar calendar;
//	private static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//	private static int puste;
//	private Part file1;
//	private String fileContent;
//	// private static FileInputStream fin = null;
//	private static CalendarBuilder builder = new CalendarBuilder();
//	private String pliczek = null;
//	private StringReader sin = null;
//	private EventBean eventBean;
//	private Event event;
//	private String line;
//
//	@PostConstruct
//	public void init() {
//		eventBean = new EventBean();
//		event = new Event();
//		eventBean.init();
//	}
//
//	public void upload() throws ParserException, ParseException {
//		try {
//			InputStream in = file1.getInputStream();
//			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
//			StringBuffer sb = new StringBuffer();
//			while ((fileContent = bf.readLine()) != null) {
//				sb.append(fileContent + "\r\n");
//			}
//			pliczek = sb.toString();
//			sin = new StringReader(pliczek);
//			open();
//		} catch (IOException e) {
//			// Error handling
//		}
//	}
//
//	public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
//		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
//		Part file = (Part) value;
//		// if (file.getSize() > 1024) {
//		// msgs.add(new FacesMessage("file too big"));
//		// }
//		if (!"text/calendar".equals(file.getContentType())) {
//			msgs.add(new FacesMessage("To nie jest plik iCal"));
//		}
//		if (!msgs.isEmpty()) {
//			throw new ValidatorException(msgs);
//		}
//	}
//
//	// u gory metody do upload pliku
//
//	public java.util.Date stringParseToDate(String date) throws ParseException {
//		java.util.Date result = dateFormat.parse(date);
//		return result;
//	}
//
//	public java.util.Date returnDate(Property property) throws ParseException {
//		String minutes = null;
//		String hour = null;
//		String dataPoczatek = property.getValue();
//		// System.out.println(dataPoczatek);
//		String year = dataPoczatek.substring(0, 4);
//		String month = dataPoczatek.substring(4, 6);
//		String day = dataPoczatek.substring(6, 8);
//		if (dataPoczatek.contains("T")) {
//			// System.out.println(dataPoczatek.substring(8,9));
//			hour = dataPoczatek.substring(9, 11);
//			minutes = dataPoczatek.substring(11, 13);
//		} else {
//			hour = "00";
//			minutes = "00";
//		}
//		String date = day + "-" + month + "-" + year + " " + hour + ":" + minutes + ":00";
//		Date startDate = stringParseToDate(date);
//		return startDate;
//	}
//
//	// g³owna metoda otwierajaca plik iCal
//	public void open() throws net.fortuna.ical4j.data.ParserException, ParseException {
//		// eventBean.clear();
//		// final FileInputStream fin = new
//		// FileInputStream("C:\\Users\\hubik_000\\Desktop\\basic.ics");
//		try {
//			calendar = builder.build(sin);
//		} catch (IOException e) {
//			System.out.println("IOException");
//		}
//
//		for (final Iterator<CalendarComponent> i = calendar.getComponents().iterator(); i.hasNext();) {
//			Component component = (Component) i.next();
//			setLine(component.getName() + " ");
//
//			for (final Iterator<Property> j = component.getProperties().iterator(); j.hasNext();) {
//				Property property = (Property) j.next();
//				setLine(getLine() + property.getName() + " " + property.getValue());
//				if (property.getName().equals("DTSTART")) {
//					if (property.getValue().isEmpty()) {
//						puste = 0;
//					} else {
//						puste = 1;
//					}
//					Date startDate = returnDate(property);
//					event.setStartDate(startDate);
//				}
//				if (property.getName().equals("DTEND")) {
//					java.util.Date endDate = returnDate(property);
//					event.setEndDate(endDate);
//				}
//				if (property.getName().equals("SUMMARY")) {
//					String title = property.getValue();
//					event.setTitle(title);
//				}
//				if (property.getName().equals("DESCRIPTION")) {
//					String description = property.getValue();
//					event.setDescription(description);
//				}
//			}
//			if (puste == 1)
//				eventBean.add(event);
//		}
//	}
//
//	public String getLine() {
//		return line;
//	}
//
//	public void setLine(String line) {
//		this.line = line;
//	}
//
//	public Part getFile1() {
//		return file1;
//	}
//
//	public void setFile1(Part file1) {
//		this.file1 = file1;
//	}
//}
