package parserUZ;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import klasy.Event;

/**
 * @author Wenaro
 *
 */
public class ParserUz {

	private Document document;
	private String nauczyciel;
	private String godzinaRozpoczaciaZajec;
	private String godzinaZakonczeniaZajec;
	private String ktoraKlasa;
	private String typZajec;
	private String nazwaZajec;
	private String grupa;
	private String data;
	private String dzien;
	private String parseDataStart;
	private String parseDataEnd;
	private String messg;
	
	private DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.ENGLISH);
	private Date start; 
	private Date end;
	
	private StringBuilder dataStart;
	private StringBuilder dataEnd;
	private StringBuilder desc;

	private ArrayList<Event> listaZajec = new ArrayList<Event>();

	public ParserUz() {
	}

	public void connection(String url) {

		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}

	public String parse() {
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Rozpoczynam parsowanie..."));
        
		Elements rows = document.select("table[border=1").select("tr");
		int a = 2;
		for (Element row : rows) {
			if (a == 2) {
				a++;
				continue;
			}
			if (ilsocElementow(row) == 1) {
				dzien = row.select("td[align=left]").text();
				dzien = dzien.toLowerCase();

			} else {
				dataStart = new StringBuilder();
				dataEnd = new StringBuilder();
				desc = new StringBuilder();
				grupa = row.select("td[align=center]").first().previousElementSibling().text();
				godzinaRozpoczaciaZajec = row.select("td[align=center]").first().text();
				godzinaZakonczeniaZajec = row.select("td[align=center]").last().text();
				nazwaZajec = row.select("td[align=center]").last().nextElementSibling().text();
				typZajec = row.select("td[align=center]").last().nextElementSibling().nextElementSibling().text();

				nauczyciel = row.select("td[align=center]").last().nextElementSibling().nextElementSibling()
						.nextElementSibling().text();
				ktoraKlasa = row.select("td[align=center]").last().nextElementSibling().nextElementSibling()
						.nextElementSibling().nextElementSibling().text();
				data = row.select("td[align=center]").last().nextElementSibling().nextElementSibling()
						.nextElementSibling().nextElementSibling().nextElementSibling().text();
				if (data.contains("D")) {
					data = "10-11-2016";
				} else {
					data = data.substring(0, 10);
				}
				parseDataStart = dataStart.append(data + " ").append(godzinaRozpoczaciaZajec).toString();
				parseDataEnd = dataEnd.append(data + " ").append(godzinaZakonczeniaZajec).toString();
				desc = desc.append(nauczyciel + "\n").append(ktoraKlasa + "\n").append(grupa + "\n").append(typZajec);
				
				try {
					start = formatter.parse(parseDataStart);
					end = formatter.parse(parseDataEnd);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				listaZajec.add(new Event(nazwaZajec, start, end, desc.toString()));
			}
		}
		messg = "aaaaaaaaaaaaaaa";
		return messg;
	}

	public final ArrayList<Event> getListaZajec() {
		return listaZajec;
	}

	private int ilsocElementow(Element elem) {
		return elem.select("td").size();
	}

	public final int getSizeArray() {
		return listaZajec.size();
	}

}