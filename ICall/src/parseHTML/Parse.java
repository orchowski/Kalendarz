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
	private ArrayList<Event> events;

    public ArrayList<Event> getEvents() {
        return events;
    }
	private String url;
	private Document doc;

	public Parse(String url) throws IOException {
		super();
		this.url = url;
		
                        events=new ArrayList<Event>();
			doc = Jsoup.connect(url).get();
			System.out.println("connected with: " + url);
	
	}

    public Parse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	public int getNumberOfTables() {
		int amount;
		amount = doc.select("table").size();

		return amount;
	}
        public int getNumberOfCols(int tab) {
		
		Element table = doc.select("table").get(tab);
               int cols = table.select("tr").get(0).select("td").size();
		if (cols == 0) {
			cols = table.select("tr").get(0).select("th").size();
		}
		return cols;
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

	public String parseTableToEvents(Element table, int title, int dStart, int dEnd, int desc[]) {

		/// formatowanie daty
                String dateFormat = "yyyy-MM-dd";
                DateFormat formatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);

		// formatowanie daty

		int row = table.select("tr").size();
		int cols = table.select("tr").get(1).select("td").size();

		String th;
		String tmp = "";
                String msg="";

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
					msg=(msg+" Nie znaleziono daty" + " format daty to:" + dateFormat);
					continue;
				}

			}
		} catch (Exception e) {
			return ("Zly format tabeli. Musi miec tyle samo komorek w kazdym wierszu");
		}
		System.out.println(tmp);
                return msg+"\nZakoñczono";
	}

	

}
