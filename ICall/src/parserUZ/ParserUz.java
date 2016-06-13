package parserUZ;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserUz {

	
	
	static String url="http://plan.uz.zgora.pl/grupy_plan.php?pId_Obiekt=16669";
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
	
	public ArrayList<Zajecia> listaZajec = new ArrayList<Zajecia>();
	
	public ParserUz(String url){
		this.url=url;
		connection(url);
		parse();
		
	}
	
	
	public void connection(String url){
		
		
		try {
			document = Jsoup.connect(url).get();
			System.out.println("polaczono");
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}
	
	public void parse(){
		
		
		//***********  DO JEDNEJ ZMIENNEJ STRING PRZYPISUJE CALY WIERSZ ***********//
	
		Elements element = document.select("table[border=1").select("tr[bgcolor]");
		Elements tableRows = document.select("tr");
		for (Element tableRow : tableRows){
             String tableDatas = tableRow.getAllElements().first().text();
             String rowData = tableDatas;
             System.out.println(rowData);
        }
		
    
     
		//******************* DO KAZDEJ ZMIENNEJ PRZYPISUJE WARTOSC Z ODPOWIEDNIEJ KOLUMNY ******************//
		
		
	/*	//table[border=1 poniewaz w zrodlach strony uz jest zapis <table border="1" width="100%">
		//tr[bgcolor] poniewaz w zrodlach ejst <TR bgcolor="#ecffff"> lub <TR bgcolor="#d2deff"> jeden kolor nie moze byc
		//bo sie zmieniaja wiec trzeba wpisac bgcolor gdzie przypisiywany jest kolor
		for (Element zajecia : element) {
			
			
			//td[align=center - poniewaz w zrodle strony jest
			//do dokonczenia wczytywanie reszty elementow (bedzie sie dzialo)
			//nextElementSibling przechodzi do nastpnej tabeli
			//dzien = zajecia.getAllElements().
			grupa = zajecia.select("td[align=center]").first().previousElementSibling().text();
			godzinaRozpoczaciaZajec = zajecia.select("td[align=center]").first().text();
			godzinaZakonczeniaZajec = zajecia.select("td[align=center]").first().nextElementSibling().text();
			nazwaZajec = zajecia.select("td[align=center]").first().nextElementSibling().nextElementSibling().text();
			typZajec= zajecia.select("td[align=center]").first().nextElementSibling().nextElementSibling().nextElementSibling().text();
			nauczyciel = zajecia.select("td[align=center]").first().nextElementSibling().
					nextElementSibling().nextElementSibling().nextElementSibling().text();
			ktoraKlasa = zajecia.select("td[align=center]").first().nextElementSibling().
					nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling().text();
			
			typTygodnia = zajecia.select("td[align=center]").first().nextElementSibling().
					nextElementSibling().nextElementSibling().nextElementSibling().
					nextElementSibling().nextElementSibling().text();
			
			
			
			System.out.print(grupa+ "  ");
			System.out.print(godzinaRozpoczaciaZajec+ "   ");
			System.out.print(godzinaZakonczeniaZajec+ "   ");
			System.out.print(nazwaZajec+ "   ");
			System.out.print(typZajec+ "   ");
			System.out.print(nauczyciel+ "   ");
			System.out.print(ktoraKlasa+ "   ");
			System.out.println(typTygodnia+ "  " +"\n");
		}
		System.out.println(listaZajec.size());*/
	}
	
	
	public final ArrayList<Zajecia> getListaZajec() {
		return listaZajec;
	}


	public static void main(String[] args) {
		
		ParserUz uz = new ParserUz(url);
	}
}
