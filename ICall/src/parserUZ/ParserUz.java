package parserUZ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import klasy.Event;

public class ParserUz {

	private String url = "http://plan.uz.zgora.pl/grupy_plan.php?pId_Obiekt=16669";
	private Document document;
	private String nauczyciel;
	private String godzinaRozpoczaciaZajec;
	private String godzinaZakonczeniaZajec;
	private String ktoraKlasa;
	private String typZajec;
	private String nazwaZajec;
	private String grupa;
	private String typTygodnia;
	private String dzien;
	private Event event;
	private List<Event> events;

	public ArrayList<Zajecia> listaZajec = new ArrayList<Zajecia>();

	public ParserUz(String url) {
		this.url = url;
		connection(url);
		parse();

	}

	public void connection(String url) {

		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}

	public void parse() {

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
				System.out.println(dzien);
			} else {
				grupa = row.select("td[align=center]").first().previousElementSibling().text();
				godzinaRozpoczaciaZajec = row.select("td[align=center]").first().text();
				godzinaZakonczeniaZajec = row.select("td[align=center]").last().text();
				nazwaZajec = row.select("td[align=center]").last().nextElementSibling().text();
				typZajec = row.select("td[align=center]").last().nextElementSibling().nextElementSibling().text();
				
				nauczyciel = row.select("td[align=center]").last().nextElementSibling().nextElementSibling()
						.nextElementSibling().text();
				ktoraKlasa = row.select("td[align=center]").last().nextElementSibling().nextElementSibling()
						.nextElementSibling().nextElementSibling().text();
				typTygodnia = row.select("td[align=center]").last().nextElementSibling().nextElementSibling()
						.nextElementSibling().nextElementSibling().nextElementSibling().text();
				if(typTygodnia.length()>10){
					typTygodnia = typTygodnia.substring(0, 10);
				}
			
				listaZajec.add(new Zajecia(nauczyciel, godzinaRozpoczaciaZajec, godzinaZakonczeniaZajec, nazwaZajec,
						ktoraKlasa, typZajec, grupa, typTygodnia, dzien));
			}
		}
	}

	public final ArrayList<Zajecia> getListaZajec() {
		return listaZajec;
	}
	private int ilsocElementow(Element elem) {
		return elem.select("td").size();
	}
	public final int getSizeArray(){
		return listaZajec.size();
	}


}