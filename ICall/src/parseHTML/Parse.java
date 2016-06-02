package parseHTML;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import klasy.Event;

public class Parse {
	public ArrayList<Event> events = new ArrayList<Event>();
	private String url;
	private Document doc;

	public Parse(String url) {
		super();
		this.url = url;

		try {
			doc = Jsoup.connect(url).get();
			System.out.println("connected with: " + url);
		} catch (IOException e) {
			System.err.println("Connection ERROR");
		}
	}

	public int getNumberOfTables() {
		int amount;
		amount = doc.select("table").size();

		return amount;
	}

	public Element getTable(int index) {
		Element table = doc.select("table").get(index);
		return table;
	}

	public void parseTableToEvents(Element table, int title, int dStart, int dEnd, int desc[]) {

		/// formatowanie daty

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(table.select("tr").get(2).select("td").get(2).text().toString(), formatter);

		// formatowanie daty

		int row = table.select("tr").size();
		int cols = table.select("tr").get(1).select("td").size();
		int i = 0;
		String th = table.select("tr").get(0).select("th").get(0).toString();
		String tmp = "";
		if (th.contains("th")) {
			i++;
		}

		try {
			for (; i < row; i++) {// System.out.println(row + " " + cols);
				for (int j = 0; j < cols; j++) {
					// System.out.println(j);
					tmp = (tmp + table.select("tr").get(i).select("td").get(j).text().toString() + " ");
				}
				//
				tmp = (tmp + "\n");
				//
				Event e = new Event();
				String dsc = "";
				for (int j = 0; j < desc.length; j++) {
					dsc = (dsc + table.select("tr").get(i).select("td").get(j).text().toString() + " ");
				}
				e.setDescription(dsc);
				e.setTitle(table.select("tr").get(i).select("td").get(title).text().toString() + " ");
				LocalDate data = LocalDate.parse(table.select("tr").get(i).select("td").get(dStart).text().toString(),
						formatter);
				e.setStartDate(new Date(data.getYear()-1900, data.getMonthValue(), data.getDayOfMonth()));
				data = LocalDate.parse(table.select("tr").get(i).select("td").get(dEnd).text().toString(), formatter);
				e.setEndDate(new Date(data.getYear()-1900, data.getMonthValue(), data.getDayOfMonth()));

				events.add(e);

			}
		} catch (Exception e) {
			System.out.print("Zly format tabeli. Musi miec tyle samo komorek w kazdym wierszu");
		}
		System.out.println(tmp);
	}

	public static void main(String[] args) {
		String string = "January 2, 2010";
		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date date;
		try {
			date = format.parse(string);
			System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Parse p = new Parse("http://localhost:8080/ICall/tables.html");

		int[] t = { 0, 1 };
		// LocalDate data = LocalDate.now();
		p.parseTableToEvents(p.getTable(2), 0, 2, 3, t);
		System.out.println("A teraz z listy");
		for (Event ev : p.events) {
			System.out.println(ev.toString());
		}
	}

}
