package parseHTML;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import klasy.Event;

/**
 * @author Aleksander Orchowski
 *
 */
public class Parse {
	/**
	 * Events list in arrayList, which contains events to add to the main app.
	 */
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
			System.exit(404);
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

	public boolean checkTable(Element table) {
		int row = table.select("tr").size();
		int cols = table.select("tr").get(0).select("td").size();
		if (cols == 0) {
			cols = table.select("tr").get(0).select("th").size();
		}
		try{
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < cols; j++) {

				}
			}
		} catch ( Exception e) {
			return false;
		}
		return true;
	}

	public void parseTableToEvents(Element table, int title, int dStart, int dEnd, int desc[]) {

		/// formatowanie daty

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		// formatowanie daty

		int row = table.select("tr").size();
		int cols = table.select("tr").get(1).select("td").size();

		String th;
		String tmp = "";

		try {
			for (int i = 0; i < row; i++) {
				th = table.select("tr").get(i).toString();
				if (th.toLowerCase().contains("th")) {
					continue;
				}
				for (int j = 0; j < cols; j++) {

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
				try {
					Date data = formatter.parse(table.select("tr").get(i).select("td").get(dStart).text().toString());
					e.setStartDate(data);
					data = formatter.parse(table.select("tr").get(i).select("td").get(dEnd).text().toString());
					e.setEndDate(data);

					events.add(e);
				} catch (Exception ex) {
					System.out.print("Nie znaleziono daty");
					continue;
				}

			}
		} catch (Exception e) {
			System.out.print("Zly format tabeli. Musi miec tyle samo komorek w kazdym wierszu");
		}
		System.out.println(tmp);
	}

	public static void main(String[] args) {

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
